package be.ehealth.businessconnector.mediprimauma.service.impl

import be.ehealth.businessconnector.mediprimauma.service.MediprimaUmaService
import be.ehealth.businessconnector.mediprimauma.service.ServiceFactory
import be.fgov.ehealth.mediprimaUma.protocol.DeleteUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.DeleteUrgentMedicalAidAttestationResponseType
import be.fgov.ehealth.mediprimaUma.protocol.SearchUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.SearchUrgentMedicalAidAttestationResponseType
import be.fgov.ehealth.mediprimaUma.protocol.SendUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.SendUrgentMedicalAidAttestationResponseType
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import javax.xml.soap.SOAPException


class MediprimaUmaServiceImpl : MediprimaUmaService{
    override fun deleteUrgentMedicalAidAttestation(
        token: SAMLToken?,
        request: DeleteUrgentMedicalAidAttestationRequestType,
        soapAction: String?
    ): DeleteUrgentMedicalAidAttestationResponseType {
        return try {
            val service = ServiceFactory.getMediPrimaUmaService(token, soapAction);
            service.setPayload(request as Any?)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val deleteUrgentMedicalAidAttestationResponse = xmlResponse.asObject(DeleteUrgentMedicalAidAttestationResponseType::class.java)
            return deleteUrgentMedicalAidAttestationResponse
        }catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, *arrayOf<Any?>(soapException.message))
        }
    }

    override fun searchUrgentMedicalAidAttestation(
        token: SAMLToken?,
        request: SearchUrgentMedicalAidAttestationRequestType,
        soapAction: String?
    ): SearchUrgentMedicalAidAttestationResponseType {
        return try {
            val service = ServiceFactory.getMediPrimaUmaService(token, soapAction);
            service.setPayload(request as Any?)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val searchUrgentMedicalAidAttestationResponse = xmlResponse.asObject(SearchUrgentMedicalAidAttestationResponseType::class.java)
            return searchUrgentMedicalAidAttestationResponse
        }catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, *arrayOf<Any?>(soapException.message))
        }
    }

    override fun sendUrgentMedicalAidAttestation(
        token: SAMLToken?,
        request: SendUrgentMedicalAidAttestationRequestType,
        soapAction: String?
    ): SendUrgentMedicalAidAttestationResponseType {
        return try {
            val service = ServiceFactory.getMediPrimaUmaService(token, soapAction);
            service.setPayload(request as Any?)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val sendUrgentMedicalAidAttestationResponse = xmlResponse.asObject(SendUrgentMedicalAidAttestationResponseType::class.java)
            return sendUrgentMedicalAidAttestationResponse
        }catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, *arrayOf<Any?>(soapException.message))
        }
    }
}
