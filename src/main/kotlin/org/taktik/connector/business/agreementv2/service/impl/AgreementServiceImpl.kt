package org.taktik.connector.business.agreementv2.service.impl

import be.fgov.ehealth.mycarenet.agreement.protocol.v2.AskAgreementRequest
import be.fgov.ehealth.mycarenet.agreement.protocol.v2.AskAgreementResponse
import be.fgov.ehealth.mycarenet.agreement.protocol.v2.ConsultAgreementRequest
import be.fgov.ehealth.mycarenet.agreement.protocol.v2.ConsultAgreementResponse
import org.slf4j.LoggerFactory
import org.taktik.connector.business.agreementv2.service.AgreementService
import org.taktik.connector.business.agreementv2.service.ServiceFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap.ModuleBootstrapHook
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import javax.xml.soap.SOAPException

/**
 * eAgreement v2 binding. Same two operations as v1, but the SOAP actions carry `:v2:` and the
 * payload types come from `mycarenet.commons.protocol.v4` instead of `v3`. The endpoint is
 * configured separately through `endpoint.agreement2`.
 */
class AgreementServiceImpl : AgreementService, ModuleBootstrapHook {
    init {
        LOG.debug("creating AgreementServiceImpl (v2) for bootstrapping purposes")
    }

    @Throws(TechnicalConnectorException::class)
    override fun askAgreement(samlToken: SAMLToken, askAgreementRequest: AskAgreementRequest?): AskAgreementResponse? =
        call(samlToken, askAgreementRequest, SOAP_ACTION_ASK_AGREEMENT, AskAgreementResponse::class.java)

    @Throws(TechnicalConnectorException::class)
    override fun consultAgreement(samlToken: SAMLToken, consultAgreementRequest: ConsultAgreementRequest?): ConsultAgreementResponse? =
        call(samlToken, consultAgreementRequest, SOAP_ACTION_CONSULT_AGREEMENT, ConsultAgreementResponse::class.java)

    @Throws(TechnicalConnectorException::class)
    private fun <R, T> call(samlToken: SAMLToken, request: R, soapAction: String, responseClass: Class<T>): T {
        try {
            val service = ServiceFactory.getAgreementPort(samlToken)
            service.setPayload(request as Any)
            service.setSoapAction(soapAction)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val response = xmlResponse.asObject(responseClass)

            when (response) {
                is AskAgreementResponse -> {
                    response.upstreamTiming = (stop - start).toInt()
                    response.soapRequest = xmlResponse.request
                    response.soapResponse = xmlResponse.soapMessage
                }
                is ConsultAgreementResponse -> {
                    response.upstreamTiming = (stop - start).toInt()
                    response.soapRequest = xmlResponse.request
                    response.soapResponse = xmlResponse.soapMessage
                }
            }
            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(AskAgreementRequest::class.java)
        JaxbContextFactory.initJaxbContext(AskAgreementResponse::class.java)
        JaxbContextFactory.initJaxbContext(ConsultAgreementRequest::class.java)
        JaxbContextFactory.initJaxbContext(ConsultAgreementResponse::class.java)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AgreementServiceImpl::class.java)
        const val SOAP_ACTION_ASK_AGREEMENT = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v2:AskAgreement"
        const val SOAP_ACTION_CONSULT_AGREEMENT = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v2:ConsultAgreement"
    }
}
