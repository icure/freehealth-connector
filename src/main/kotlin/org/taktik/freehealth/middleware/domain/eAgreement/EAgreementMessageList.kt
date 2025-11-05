package org.taktik.freehealth.middleware.domain.eAgreement

import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataBatchResponse
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError

data class EAgreementMessageList(
    var commonOutput: CommonOutput? = null,
    var complete: Boolean? = false,
    var errors: List<MycarenetError>? = null,
    var genericErrors: List<FaultType>? = null,
    var eAgreementResponse: List<EAgreementBatchResponse>? = null,
    var io: String? = null,
    var appliesTo: String? = null,
    var reference: String? = null,
    var valueHash: String? = null
)
