package org.taktik.freehealth.middleware.domain.mediprimaUma

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.fgov.ehealth.mediprimaUma.protocol.v1.SearchUrgentMedicalAidAttestationResponseType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

@JsonIgnoreProperties(ignoreUnknown = true)
class MediprimaUmaSearchUrgentMedicalAidAttestationResponse(
    var mycarenetConversation: MycarenetConversation? = null,
    var response: SearchUrgentMedicalAidAttestationResponseType? = null,
    var status: String? = null
)
