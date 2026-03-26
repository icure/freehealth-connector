/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.web

import com.hazelcast.map.IMap
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Timer
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.logging.LogFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.taktik.freehealth.middleware.EndpointGroupLimit
import org.taktik.freehealth.middleware.RateLimitProperties
import org.taktik.freehealth.middleware.domain.RateLimitEntry
import org.taktik.freehealth.middleware.hazelcast.RateLimitEntryProcessor

@Component
@Order(1)
class RateLimitFilter(
    private val rateLimitProperties: RateLimitProperties,
    private val rateLimitsMap: IMap<String, RateLimitEntry>,
    private val meterRegistry: MeterRegistry
) : Filter {
    private val log = LogFactory.getLog(this::class.java)
    private val pathMatcher = AntPathMatcher()

    private val allowedCounter: Counter by lazy {
        Counter.builder("fhc.ratelimit.requests")
            .tag("result", "allowed")
            .register(meterRegistry)
    }
    private val deniedCounter: Counter by lazy {
        Counter.builder("fhc.ratelimit.requests")
            .tag("result", "denied")
            .register(meterRegistry)
    }
    private val checkTimer: Timer by lazy {
        Timer.builder("fhc.ratelimit.check.duration")
            .register(meterRegistry)
    }

    override fun init(filterConfig: FilterConfig?) {}

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        if (!rateLimitProperties.enabled) {
            chain.doFilter(request, response)
            return
        }

        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse
        val path = httpRequest.requestURI

        if (isExcluded(path)) {
            chain.doFilter(request, response)
            return
        }

        // Resolve endpoint group
        val matchedGroup = resolveGroup(path)
        val groupName = matchedGroup?.first ?: "default"
        val groupLimit = matchedGroup?.second

        val limit: Int
        val window: Long
        val primaryKey = httpRequest.getHeader(rateLimitProperties.keyHeader)
        val fallbackKey = httpRequest.getHeader(rateLimitProperties.fallbackKeyHeader)
        val keyType: String
        val keyValue: String

        if (primaryKey != null) {
            keyType = rateLimitProperties.keyHeader
            keyValue = primaryKey
            limit = groupLimit?.maxRequests ?: rateLimitProperties.maxRequests
        } else if (fallbackKey != null) {
            keyType = rateLimitProperties.fallbackKeyHeader
            keyValue = fallbackKey
            limit = groupLimit?.maxRequests ?: rateLimitProperties.maxRequests
        } else {
            keyType = "ip"
            keyValue = httpRequest.remoteAddr
            limit = rateLimitProperties.anonymousLimit
        }
        window = groupLimit?.windowSeconds ?: rateLimitProperties.windowSeconds

        val rateLimitKey = "$groupName:$keyType:$keyValue"

        val result = checkTimer.recordCallable {
            rateLimitsMap.executeOnKey(
                rateLimitKey,
                RateLimitEntryProcessor(System.currentTimeMillis(), window, limit)
            )
        }!!

        httpResponse.setHeader("X-RateLimit-Limit", limit.toString())
        httpResponse.setHeader("X-RateLimit-Remaining", result.remaining.toString())
        httpResponse.setHeader("X-RateLimit-Reset", result.resetEpochSeconds.toString())

        if (result.allowed) {
            allowedCounter.increment()
            chain.doFilter(request, response)
        } else {
            deniedCounter.increment()
            log.warn("Rate limit exceeded for $rateLimitKey on $path")
            httpResponse.status = 429
            httpResponse.setHeader("Retry-After", result.retryAfterSeconds.toString())
            httpResponse.contentType = "application/json"
            httpResponse.writer.write(
                """{"error":"rate_limit_exceeded","message":"Too many requests","retryAfter":${result.retryAfterSeconds}}"""
            )
        }
    }

    private fun resolveGroup(path: String): Pair<String, EndpointGroupLimit>? {
        for ((name, group) in rateLimitProperties.groups) {
            if (group.pathPatterns.any { pattern -> pathMatcher.match(pattern, path) }) {
                return name to group
            }
        }
        return null
    }

    private fun isExcluded(path: String): Boolean {
        return rateLimitProperties.excludedPaths.any { pattern -> pathMatcher.match(pattern, path) }
    }

    override fun destroy() {}
}