package org.taktik.freehealth.middleware.dto.consultrn

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.fgov.ehealth.consultrn._1_0.core.ErrorType
import org.taktik.freehealth.middleware.dto.common.ResponseTypeDto
import org.taktik.freehealth.middleware.dto.common.StatusDto
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
open class ConsultRnReplyDto(status: StatusDto? = null, id: String? = null, var errorInformations: List<ErrorType>? = null) : ResponseTypeDto(status, id), Serializable
