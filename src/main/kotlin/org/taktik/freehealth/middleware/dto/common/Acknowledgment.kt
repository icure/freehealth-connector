package org.taktik.freehealth.middleware.dto.common

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class Acknowledgment(var messageId:String? = null, var recipient: Addressee? = null, var ackType: String? = null, var dateTime: Long? = null)
