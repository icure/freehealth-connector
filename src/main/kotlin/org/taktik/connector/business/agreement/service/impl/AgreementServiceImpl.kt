package org.taktik.connector.business.agreement.service.impl

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementRequest
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType
import org.slf4j.LoggerFactory
import org.taktik.connector.business.agreement.service.AgreementService
import org.taktik.connector.business.agreement.service.ServiceFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap.ModuleBootstrapHook
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import javax.xml.soap.SOAPException

class AgreementServiceImpl : AgreementService, ModuleBootstrapHook {
    init {
        LOG.debug("creating AgreementServiceImpl for bootstrapping purposes")
    }

    @Throws(TechnicalConnectorException::class)
    override fun askAgreement(samlToken: SAMLToken?, askAgreementRequest: AskAgreementRequest?): AskAgreementResponse? {
        return callAgreementService(
            samlToken,
            askAgreementRequest,
            "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:AskAgreement",
            AskAgreementResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    override fun consultAgreement(
        samlToken: SAMLToken?,
        consultAgreementRequest: ConsultAgreementRequest?
    ): ConsultAgreementResponse? {
        return callAgreementService(
            samlToken,
            consultAgreementRequest,
            "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:ConsultAgreement",
            ConsultAgreementResponse::class.java
        )
    }

    @Throws(TechnicalConnectorException::class)
    private fun <T : SendRequestType?, K : SendResponseType?> callAgreementService(
        token: SAMLToken?,
        request: T,
        soapAction: String,
        responseClass: Class<K>
    ): K {
        return try {
            val service = ServiceFactory.getAgreementPort(token)
            service.setSoapAction(soapAction)
            service.setPayload(request)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            xmlResponse.asObject(responseClass)
        } catch (e: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e, e.message)
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
        const val SOAP_ACTION_ASK_AGREEMENT = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:AskAgreement"
        const val SOAP_ACTION_CONSULT_AGREEMENT = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:ConsultAgreement"
    }
}