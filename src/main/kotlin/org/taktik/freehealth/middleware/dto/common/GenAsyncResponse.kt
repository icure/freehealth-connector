package org.taktik.freehealth.middleware.dto.common

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.cin.nip.async.generic.TAck
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

@JsonIgnoreProperties(ignoreUnknown = true)
class GenAsyncResponse(var result: Boolean? = null, var commonOutput: CommonOutput? = null, var tack: TAck? = null, var mycarenetConversation: MycarenetConversation? = null)
