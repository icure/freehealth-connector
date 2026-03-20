package org.taktik.freehealth.middleware.domain.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class MemberDataBatchRequest(
    var members: List<MemberInfo> = listOf(),
    var facets: List<Facet>? = null)
