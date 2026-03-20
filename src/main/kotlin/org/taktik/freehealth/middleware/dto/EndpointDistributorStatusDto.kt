package org.taktik.freehealth.middleware.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class EndpointDistributorStatusDto(val mustPoll: Boolean, val isBcpMode: Boolean, val default: Map<String, String>, val active: Map<String, String>)
