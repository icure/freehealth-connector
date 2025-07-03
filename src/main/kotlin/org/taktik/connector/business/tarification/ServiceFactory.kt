package org.taktik.connector.business.tarification

import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType

object ServiceFactory {
    private val PROP_ENDPOINT_TARIFICATION_MYCARENET_V1 = "endpoint.mcn.tarification"
    private val PROP_ENDPOINT_TARIFICATION__MEDIPRIMA_MYCARENET = "endpoint.mcn.tarification.mediprima"
    private val PROP_VALIDATION_INCOMING_CONS_TARIFICATION_MYCARENET = "validation.incoming.message.mcn.tarification"
    private val PROP_ENDPOINT_TARIFICATION_MEDIPRIMA_V1 = "endpoint.mcn.tarificationmediprima"
    private val PROP_VALIDATION_INCOMING_CONS_TARIFICATION_MEDIPRIMA =
        "validation.incoming.message.mcn.tarificationmediprima"
    internal val TARIFICATION_PROT = "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd"
    private val config = ConfigFactory.getConfigValidator()
    private val SOAP_ACTION_MEDIPRIMA = "urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1:TarificationConsult"

    @Throws(TechnicalConnectorException::class)
    fun getTarificationSessionForMediPrima(token: SAMLToken): GenericRequest {
        val genReq = GenericRequest()
        genReq.setEndpoint(config.getProperty(PROP_ENDPOINT_TARIFICATION__MEDIPRIMA_MYCARENET))
        genReq.setSoapAction(SOAP_ACTION_MEDIPRIMA)
        genReq.setCredential(token, TokenType.SAML)
        genReq.setDefaultHandlerChain()
        genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator(PROP_VALIDATION_INCOMING_CONS_TARIFICATION_MEDIPRIMA, TARIFICATION_PROT))
        return genReq
    }

    @Throws(TechnicalConnectorException::class)
    fun getTarificationSessionForMycarenet(token: SAMLToken): GenericRequest {
        val genReq = GenericRequest()
        genReq.setEndpoint(config.getProperty("endpoint.mcn.tarification", "\$uddi{uddi:ehealth-fgov-be:business:mycarenettarification:v1}"))
        genReq.setSoapAction("urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1:TarificationConsult")
        genReq.setCredential(token, TokenType.SAML)
        genReq.setDefaultHandlerChain()
        genReq.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mcn.tarification", "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd"))
        return genReq
    }
}
