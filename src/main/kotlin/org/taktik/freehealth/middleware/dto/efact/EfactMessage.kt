package org.taktik.freehealth.middleware.dto.efact

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.cin.nip.async.generic.TAck
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput

@JsonIgnoreProperties(ignoreUnknown = true)
class EfactMessage {
    var detail: String? = null
    var messageReference: String? = null
    var id: String? = null
    var name: String? = null

    var commonOutput: CommonOutput? = null

    var message: List<Record>? = null
    var xades : String? = null
    var tAck: TAck? = null

    var hashValue: String? = null
}
