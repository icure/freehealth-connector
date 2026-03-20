package org.taktik.freehealth.middleware.domain.sts

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
data class CardConnectionRelay(val action: String, val data: String, val digestType: String? = null)
