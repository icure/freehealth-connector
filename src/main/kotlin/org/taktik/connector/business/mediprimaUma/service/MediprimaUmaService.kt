package be.ehealth.businessconnector.mediprimauma.service

import be.fgov.ehealth.mediprimaUma.protocol.v1.DeleteUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.v1.DeleteUrgentMedicalAidAttestationResponseType
import be.fgov.ehealth.mediprimaUma.protocol.v1.SearchUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.v1.SearchUrgentMedicalAidAttestationResponseType
import be.fgov.ehealth.mediprimaUma.protocol.v1.SendUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.v1.SendUrgentMedicalAidAttestationResponseType
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken


interface MediprimaUmaService {
   @Throws(TechnicalConnectorException::class)
   fun deleteUrgentMedicalAidAttestation(token: SAMLToken?, request: DeleteUrgentMedicalAidAttestationRequestType, soapAction: String?): DeleteUrgentMedicalAidAttestationResponseType

   @Throws(TechnicalConnectorException::class)
   fun searchUrgentMedicalAidAttestation(token: SAMLToken?, request: SearchUrgentMedicalAidAttestationRequestType, soapAction: String?): SearchUrgentMedicalAidAttestationResponseType

   @Throws(TechnicalConnectorException::class)
   fun sendUrgentMedicalAidAttestation(token: SAMLToken?, request: SendUrgentMedicalAidAttestationRequestType, soapAction: String?): SendUrgentMedicalAidAttestationResponseType
}
