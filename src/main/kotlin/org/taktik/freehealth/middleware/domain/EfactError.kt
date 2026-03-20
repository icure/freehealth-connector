package org.taktik.freehealth.middleware.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class EfactError(var code:String? = null, var type:String? = null, var label : Map<String,String> = HashMap())
