package org.taktik.freehealth.middleware.domain.eAgreement

import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import java.util.Date

data class EAgreementDataList(
    val mycarenetConversation: MycarenetConversation?,
    val eAgreementMessageList: List<EAgreementMessageList>?,
    val date: Date?,
    val genericErrors: List<FaultType>?
)
