package org.taktik.connector.business.mediprimav2.service

import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequestType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface MediprimaService {
    @Throws(TechnicalConnectorException::class)
    fun consultMediprima(token: SAMLToken, request: ConsultCarmedInterventionRequestType, soapAction: String): ConsultCarmedInterventionResponse
}
