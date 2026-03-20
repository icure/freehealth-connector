package org.taktik.freehealth.middleware.dto.hub

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.freehealth.middleware.dto.common.ErrorDto

@JsonIgnoreProperties(ignoreUnknown = true)
class PutTransactionResponseDto(var id:List<IDKMEHR>? = null,var errors:List<ErrorDto>? = null)
