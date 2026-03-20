package org.taktik.freehealth.middleware.dto.genins

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class TransferDto {
    var direction: String? = null
    var io: String? = null
    var date: Long? = null
}
