package org.taktik.freehealth.middleware.dto.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class MemberDataBatchRequestDto(var members: List<MemberInfoDto> = listOf(),
    var facets: List<FacetDto>? = null)
