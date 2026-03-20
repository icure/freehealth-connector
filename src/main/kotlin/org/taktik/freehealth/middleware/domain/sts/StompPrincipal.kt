package org.taktik.freehealth.middleware.domain.sts

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.security.Principal

@JsonIgnoreProperties(ignoreUnknown = true)
data class StompPrincipal(private val sessionId: String) : Principal {
    override fun getName() = sessionId
}
