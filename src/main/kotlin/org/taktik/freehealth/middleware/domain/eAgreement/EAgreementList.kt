package org.taktik.freehealth.middleware.domain.eAgreement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class EAgreementList(
    val mycarenetConversation: MycarenetConversation?,
    val eAgreementMessageList: List<EAgreementMessage>?,
    val date: Date?,
    val genericErrors: List<FaultType>?
)
