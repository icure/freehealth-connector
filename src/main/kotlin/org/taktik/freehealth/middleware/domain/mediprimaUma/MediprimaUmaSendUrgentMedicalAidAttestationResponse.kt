package org.taktik.freehealth.middleware.domain.mediprimaUma

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.fgov.ehealth.mediprimaUma.protocol.v1.SendUrgentMedicalAidAttestationResponseType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

@JsonIgnoreProperties(ignoreUnknown = true)
class MediprimaUmaSendUrgentMedicalAidAttestationResponse(
    var mycarenetConversation: MycarenetConversation? = null,
    var response: SendUrgentMedicalAidAttestationResponseType? = null,
    var status: String? = null
)
