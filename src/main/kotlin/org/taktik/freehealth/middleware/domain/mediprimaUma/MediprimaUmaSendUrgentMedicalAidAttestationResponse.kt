package org.taktik.freehealth.middleware.domain.mediprimaUma

import be.fgov.ehealth.mediprimaUma.protocol.SendUrgentMedicalAidAttestationResponseType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

class MediprimaUmaSendUrgentMedicalAidAttestationResponse(
    var mycarenetConversation: MycarenetConversation? = null,
    var response: SendUrgentMedicalAidAttestationResponseType? = null,
    var status: String? = null
)
