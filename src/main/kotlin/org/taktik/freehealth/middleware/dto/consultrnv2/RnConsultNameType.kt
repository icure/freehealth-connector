package org.taktik.freehealth.middleware.dto.consultrnv2

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class RnConsultNameType(
    var lastName: String? = null,
    var firstName: String? = null,
    var inceptionDate: String? = null
) : Serializable
