package org.taktik.freehealth.middleware.domain.rsw

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
data class Jwt(
    var accessToken: String? = null,
    var refreshToken: String? = null,
    var tokenType: String? = null,
    var expiresIn: Int? = null,
    var error: Int? = null,
    var errorDescription: String? = null)
