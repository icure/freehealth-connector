package org.taktik.freehealth.middleware.domain.eAgreement

import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.domain.memberdata.MdaStatus
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import javax.xml.datatype.XMLGregorianCalendar

class EAgreementResponse(
    var status: MdaStatus? = null,
    var errors: List<FaultType>? = null,
    var myCarenetErrors: List<MycarenetError> = ArrayList(),
    var issuer: String? = null,
    var issueInstant: XMLGregorianCalendar? = null,
    var inResponseTo: String? = null,
    var responseId: String? = null
)
