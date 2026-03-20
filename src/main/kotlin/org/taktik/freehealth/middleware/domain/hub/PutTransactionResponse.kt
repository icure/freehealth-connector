package org.taktik.freehealth.middleware.domain.hub

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.freehealth.middleware.domain.common.Error

@JsonIgnoreProperties(ignoreUnknown = true)
class PutTransactionResponse(var id:List<IDKMEHR>? = null,var errors:List<Error>? = null)
