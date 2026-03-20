package org.taktik.freehealth.middleware.dto.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class MemberDataAckDto (
    var major: String?,
    var minor: String?,
    var message: String?,
    var date: Date?
    )
