package org.taktik.freehealth.middleware.dto.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

@JsonIgnoreProperties(ignoreUnknown = true)
class MemberDataResponseDto(var assertions: List<AssertionDto> = ArrayList(), var mycarenetConversation: MycarenetConversation? = null)
