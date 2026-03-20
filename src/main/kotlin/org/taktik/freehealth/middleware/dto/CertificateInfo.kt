package org.taktik.freehealth.middleware.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class CertificateInfo(var validity: Long? = null, var type: String? = null, var id: String? = null, var application: String? = null, var owner: String? = null)
