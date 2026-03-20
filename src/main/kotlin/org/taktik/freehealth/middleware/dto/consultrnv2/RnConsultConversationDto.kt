package org.taktik.freehealth.middleware.dto.consultrnv2

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class RnConsultConversationDto(
    var request: String? = null,
    var response: String? = null
): Serializable
