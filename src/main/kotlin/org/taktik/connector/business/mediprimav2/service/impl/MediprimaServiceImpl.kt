package org.taktik.connector.business.mediprimav2.service.impl

import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequestType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
import org.taktik.connector.business.mediprimav2.service.MediprimaService
import org.taktik.connector.business.mediprimav2.service.ServiceFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import javax.xml.soap.SOAPException

class MediprimaServiceImpl: MediprimaService {
    @Throws(TechnicalConnectorException::class)
    override fun consultMediprima(
        token: SAMLToken,
        request: ConsultCarmedInterventionRequestType,
        soapAction: String
    ): ConsultCarmedInterventionResponseType{
        return try {
            val service = ServiceFactory.getMediprimaConsultationService(token, soapAction)
            service.setPayload(request as Any?)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val mediprimaResponse = xmlResponse.asObject(ConsultCarmedInterventionResponseType::class.java)
            return mediprimaResponse
        }catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, *arrayOf<Any?>(soapException.message))
        }
    }
}
