package org.taktik.freehealth.middleware.dto.common

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
open class ResponseTypeDto(var status: StatusDto? = null, var id: String? = null) : Serializable
