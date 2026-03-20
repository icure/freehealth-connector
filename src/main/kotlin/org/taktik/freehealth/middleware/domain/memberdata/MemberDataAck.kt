package org.taktik.freehealth.middleware.domain.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class MemberDataAck (
    var major: String?,
    var minor: String?,
    var message: String?,
    var date: Date?
)
