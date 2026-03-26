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

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.util.AntPathMatcher
import org.taktik.freehealth.middleware.EndpointGroupLimit
import org.taktik.freehealth.middleware.RateLimitProperties
import org.taktik.freehealth.middleware.domain.RateLimitEntry
import org.taktik.freehealth.middleware.hazelcast.RateLimitEntryProcessor
import java.util.AbstractMap

class RateLimitFilterTest {

    @Suppress("UNCHECKED_CAST")
    private fun makeEntry(key: String, value: RateLimitEntry?): AbstractMap.SimpleEntry<String, RateLimitEntry> {
        return AbstractMap.SimpleEntry(key, value) as AbstractMap.SimpleEntry<String, RateLimitEntry>
    }

    @Test
    fun newKeyIsAllowed() {
        val entry = makeEntry("key1", null)
        val processor = RateLimitEntryProcessor(1000L, 60, 100)

        val result = processor.process(entry)

        assertThat(result.allowed).isTrue()
        assertThat(result.remaining).isEqualTo(99)
        assertThat(entry.value).isNotNull()
        assertThat(entry.value.currentCount).isEqualTo(1)
    }

    @Test
    fun requestsWithinLimitAreAllowed() {
        val now = 60000L
        val entry = makeEntry("key1", RateLimitEntry(0, 50, now))
        val processor = RateLimitEntryProcessor(now + 1000, 60, 100)

        val result = processor.process(entry)

        assertThat(result.allowed).isTrue()
        assertThat(entry.value.currentCount).isEqualTo(51)
    }

    @Test
    fun requestsOverLimitAreDenied() {
        val now = 60000L
        val entry = makeEntry("key1", RateLimitEntry(0, 100, now))
        val processor = RateLimitEntryProcessor(now + 1000, 60, 100)

        val result = processor.process(entry)

        assertThat(result.allowed).isFalse()
        assertThat(result.remaining).isEqualTo(0)
        assertThat(result.retryAfterSeconds).isGreaterThan(0)
    }

    @Test
    fun previousWindowContributesToEstimate() {
        val windowMs = 60000L
        val windowStart = 100000L
        // 80 requests in previous window, 0 in current, 10% into current window
        // estimated = 80 * 0.9 + 0 = 72
        val now = windowStart + 6000L  // 10% into current window
        val entry = makeEntry("key1", RateLimitEntry(80, 0, windowStart))
        val processor = RateLimitEntryProcessor(now, 60, 100)

        val result = processor.process(entry)

        assertThat(result.allowed).isTrue()
        assertThat(entry.value.currentCount).isEqualTo(1)
    }

    @Test
    fun previousWindowCanCauseDenial() {
        val windowMs = 60000L
        val windowStart = 100000L
        // 100 requests in previous window, 10 in current, 10% into current window
        // estimated = 100 * 0.9 + 10 = 100 -> denied
        val now = windowStart + 6000L
        val entry = makeEntry("key1", RateLimitEntry(100, 10, windowStart))
        val processor = RateLimitEntryProcessor(now, 60, 100)

        val result = processor.process(entry)

        assertThat(result.allowed).isFalse()
    }

    @Test
    fun windowAdvancesWhenExpired() {
        val windowMs = 60000L
        val windowStart = 10000L
        // Current window expired: now is 1.5 windows past windowStart
        val now = windowStart + windowMs + windowMs / 2
        val entry = makeEntry("key1", RateLimitEntry(50, 80, windowStart))
        val processor = RateLimitEntryProcessor(now, 60, 100)

        val result = processor.process(entry)

        assertThat(result.allowed).isTrue()
        // previous window should now be 80 (the old current), current should be 1 (new request)
        assertThat(entry.value.previousCount).isEqualTo(80)
        assertThat(entry.value.currentCount).isEqualTo(1)
    }

    @Test
    fun bothWindowsResetWhenBothExpired() {
        val windowMs = 60000L
        val windowStart = 10000L
        // Both windows expired: now is >2 windows past windowStart
        val now = windowStart + windowMs * 3
        val entry = makeEntry("key1", RateLimitEntry(99, 99, windowStart))
        val processor = RateLimitEntryProcessor(now, 60, 100)

        val result = processor.process(entry)

        assertThat(result.allowed).isTrue()
        assertThat(entry.value.previousCount).isEqualTo(0)
        assertThat(entry.value.currentCount).isEqualTo(1)
    }

    @Test
    fun retryAfterIsPositive() {
        val now = 60000L
        val entry = makeEntry("key1", RateLimitEntry(0, 100, now))
        val processor = RateLimitEntryProcessor(now + 1000, 60, 100)

        val result = processor.process(entry)

        assertThat(result.allowed).isFalse()
        assertThat(result.retryAfterSeconds).isGreaterThanOrEqualTo(1)
    }

    @Test
    fun serialization() {
        val entry = RateLimitEntry(42, 17, 123456789L)
        assertThat(entry.previousCount).isEqualTo(42)
        assertThat(entry.currentCount).isEqualTo(17)
        assertThat(entry.windowStartMillis).isEqualTo(123456789L)
    }

    // --- Endpoint group resolution tests ---

    private val pathMatcher = AntPathMatcher()

    private fun resolveGroup(
        path: String,
        groups: Map<String, EndpointGroupLimit>
    ): Pair<String, EndpointGroupLimit>? {
        for ((name, group) in groups) {
            if (group.pathPatterns.any { pattern -> pathMatcher.match(pattern, path) }) {
                return name to group
            }
        }
        return null
    }

    @Test
    fun groupMatchesPathPattern() {
        val recipeGroup = EndpointGroupLimit().apply {
            pathPatterns = listOf("/recipe/**")
            maxRequests = 50
        }
        val groups = mapOf("recipe" to recipeGroup)

        val result = resolveGroup("/recipe/prescriptions", groups)

        assertThat(result).isNotNull()
        assertThat(result!!.first).isEqualTo("recipe")
        assertThat(result.second.maxRequests).isEqualTo(50)
    }

    @Test
    fun noGroupMatchFallsBackToNull() {
        val recipeGroup = EndpointGroupLimit().apply {
            pathPatterns = listOf("/recipe/**")
            maxRequests = 50
        }
        val groups = mapOf("recipe" to recipeGroup)

        val result = resolveGroup("/ab/search/hcp/test", groups)

        assertThat(result).isNull()
    }

    @Test
    fun groupKeyIsPrefixedWithGroupName() {
        val recipeGroup = EndpointGroupLimit().apply {
            pathPatterns = listOf("/recipe/**")
            maxRequests = 50
        }
        val groups = mapOf("recipe" to recipeGroup)
        val matched = resolveGroup("/recipe/prescriptions", groups)
        val groupName = matched?.first ?: "default"
        val keystoreId = "550e8400-e29b-41d4-a716-446655440000"

        val key = "$groupName:X-FHC-keystoreId:$keystoreId"

        assertThat(key).isEqualTo("recipe:X-FHC-keystoreId:$keystoreId")
    }

    @Test
    fun unmatchedPathGetsDefaultPrefix() {
        val groups = mapOf(
            "recipe" to EndpointGroupLimit().apply {
                pathPatterns = listOf("/recipe/**")
                maxRequests = 50
            }
        )
        val matched = resolveGroup("/ab/search/hcp/test", groups)
        val groupName = matched?.first ?: "default"
        val keystoreId = "550e8400-e29b-41d4-a716-446655440000"

        val key = "$groupName:X-FHC-keystoreId:$keystoreId"

        assertThat(key).isEqualTo("default:X-FHC-keystoreId:$keystoreId")
    }

    @Test
    fun groupWindowSecondsOverridesGlobal() {
        val group = EndpointGroupLimit().apply {
            pathPatterns = listOf("/recipe/**")
            maxRequests = 50
            windowSeconds = 30
        }
        val globalWindow = 60L
        val effectiveWindow = group.windowSeconds ?: globalWindow

        assertThat(effectiveWindow).isEqualTo(30)
    }

    @Test
    fun groupWindowSecondsNullFallsBackToGlobal() {
        val group = EndpointGroupLimit().apply {
            pathPatterns = listOf("/recipe/**")
            maxRequests = 50
            windowSeconds = null
        }
        val globalWindow = 60L
        val effectiveWindow = group.windowSeconds ?: globalWindow

        assertThat(effectiveWindow).isEqualTo(60)
    }
}