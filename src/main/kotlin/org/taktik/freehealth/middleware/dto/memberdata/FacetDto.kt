package org.taktik.freehealth.middleware.dto.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class FacetDto(var id:String? = null, var dimensions:List<DimensionDto>? = null)
