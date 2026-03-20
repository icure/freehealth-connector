package org.taktik.freehealth.middleware.dto.mycarenet

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class CommonOutput(var inputReference: String? = null, var nipReference: String? = null, var outputReference: String? = null)
