package org.taktik.freehealth.middleware.dto.efact

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.cin.nip.async.generic.TAck

@JsonIgnoreProperties(ignoreUnknown = true)
class EfactErrorMessage {
    var detail: String? = null
    var id: String? = null
    var name: String? = null

    var xades : String? = null
    var tAck: TAck? = null

    var hashValue: String? = null
}
