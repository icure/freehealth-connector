package org.taktik.freehealth.middleware.domain.mediprimaUma

import be.fgov.ehealth.mediprimaUma.protocol.v1.DeleteUrgentMedicalAidAttestationResponseType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

class MediprimaUmaDeleteUrgentMedicalAidAttestationResponse(
    var mycarenetConversation: MycarenetConversation? = null,
    var response: DeleteUrgentMedicalAidAttestationResponseType? = null,
    var status: String? = null
)
