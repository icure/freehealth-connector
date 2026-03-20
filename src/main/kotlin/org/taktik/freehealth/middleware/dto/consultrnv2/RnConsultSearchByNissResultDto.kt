package org.taktik.freehealth.middleware.dto.consultrnv2

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class RnConsultSearchByNissResultDto(
    var person: RnConsultPersonDto? = null
): Serializable
