package org.taktik.freehealth.middleware.dto.daas

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class DaasResponse(var destinations: List<Map<String, String?>>?, var context: Map<String, String?>?, var status: String?)
