package org.taktik.freehealth.middleware.dto.efact

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.cin.nip.async.generic.TAck
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class EfactSendResponse(var success: Boolean? = null, var inputReference: String? = null, var tack: TAck? = null, var detail: String? = null, var records: List<Record>, var commonOutput: CommonOutput? = null, var mycarenetConversation: MycarenetConversation? = null) : Serializable
