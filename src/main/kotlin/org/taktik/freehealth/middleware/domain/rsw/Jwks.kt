package org.taktik.freehealth.middleware.domain.rsw

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
data class Jwks(var keys:List<Jwk>? = null)
