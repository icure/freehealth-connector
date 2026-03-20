package org.taktik.freehealth.middleware.domain.sts

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class StompMessage(var content:String)
