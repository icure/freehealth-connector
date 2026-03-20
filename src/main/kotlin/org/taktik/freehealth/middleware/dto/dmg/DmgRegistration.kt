package org.taktik.freehealth.middleware.dto.dmg

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
class DmgRegistration : DmgMessage() {
    var isSuccess: Boolean = false
    var date: Date? = null
}
