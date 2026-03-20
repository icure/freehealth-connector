package org.taktik.freehealth.middleware.dto.ehbox

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.UUID

@JsonIgnoreProperties(ignoreUnknown = true)
class AltKeystoresList {
    var keystores : List<AltKeystore> = listOf()
}

@JsonIgnoreProperties(ignoreUnknown = true)
class AltKeystore {
    var uuid : UUID? = null
    var passPhrase: String? = null
}
