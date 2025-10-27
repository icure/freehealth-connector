package org.taktik.freehealth.middleware.domain.mediprimaUma

import be.fgov.ehealth.mediprimaUma.protocol.SearchUrgentMedicalAidAttestationResponseType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

class MediprimaUmaSearchUrgentMedicalAidAttestationResponse(
    var mycarenetConversation: MycarenetConversation? = null,
    var response: SearchUrgentMedicalAidAttestationResponseType? = null,
    var status: String? = null
)
