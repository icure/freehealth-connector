package org.taktik.freehealth.middleware.domain.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class Dimension(var id:String? = null, var value:String? = null)
