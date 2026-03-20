package org.taktik.freehealth.middleware.domain.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class MdaStatus(var code1:String? = null, var code2: String? = null)
