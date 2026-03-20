package org.taktik.freehealth.middleware.dto.common

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class GenericResult(val success:Boolean)
