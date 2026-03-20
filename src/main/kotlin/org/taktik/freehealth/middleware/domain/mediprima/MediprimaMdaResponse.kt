package org.taktik.freehealth.middleware.domain.mediprima

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.fgov.ehealth.mediprima.core.v2.CbssStatusType
import be.fgov.ehealth.mediprima.core.v2.MedicalCardRegistryStatusType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation

@JsonIgnoreProperties(ignoreUnknown = true)
class MediprimaMdaResponse(
    var mycarenetConversation: MycarenetConversation? = null,
    var response: ConsultCarmedInterventionResponseType? = null,
    var status: String? = null,
    var cbssStatus: CbssStatusType? = null,
    var medicalStatus: MedicalCardRegistryStatusType? = null
)

