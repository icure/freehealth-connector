/*
 *
 * Copyright (C) 2018 iCure SA
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

package org.taktik.freehealth.middleware

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("fhc.ratelimit")
class RateLimitProperties {
    var enabled: Boolean = false
    var keyHeader: String = "X-FHC-tokenId"
    var windowSeconds: Long = 60
    var maxRequests: Int = 100
    var anonymousLimit: Int = 20
    var excludedPaths: List<String> = listOf("/sts/keystore", "/actuator/**")
    var groups: Map<String, EndpointGroupLimit> = emptyMap()
}

class EndpointGroupLimit {
    var pathPatterns: List<String> = emptyList()
    var maxRequests: Int = 100
    var windowSeconds: Long? = null
}
