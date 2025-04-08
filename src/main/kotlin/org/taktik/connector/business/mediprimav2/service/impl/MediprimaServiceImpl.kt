package org.taktik.connector.business.mediprimav2.service.impl

import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequestType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse
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
    ): ConsultCarmedInterventionResponse{
        return try {
            val service = ServiceFactory.getMediprimaConsultationService(token, soapAction)
            service.setPayload(request as Any?)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val mediprimaResponse = xmlResponse.asObject(ConsultCarmedInterventionResponseType::class.java) as ConsultCarmedInterventionResponse

            mediprimaResponse.upstreamTiming = (stop - start).toInt();
            mediprimaResponse.soapRequest = xmlResponse.request
            mediprimaResponse.soapResponse = xmlResponse.soapMessage

            mediprimaResponse
        }catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, *arrayOf<Any?>(soapException.message))
        }
    }

}
