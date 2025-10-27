package be.ehealth.businessconnector.mediprimauma.service

import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType

object ServiceFactory {
    private const val PROP_ENDPOINT_MEDIPRIMAUMA = "endpoint.mediprimauma"
    private const val PROP_VALIDATION_INCOMING_MEDIPRIMAUMA = "validation.incoming.message.mediprimauma"
    const val MEDIPRIMAUMA_XSD: String = "/ehealth-mediprima-uma/XSD/ehealth-mediprima-uma-protocol-1_0.xsd"
    private val config: Configuration = ConfigFactory.getConfigValidator()

    @Throws(TechnicalConnectorException::class)
    fun getMediPrimaUmaService(token: SAMLToken?, soapAction: String?): GenericRequest {
        return (GenericRequest()).setEndpoint(
            config.getProperty(
                "endpoint.mediprimauma",
                "\$uddi{uddi:ehealth-fgov-be:business:mediprimauma:v1}"
            )
        ).setSoapAction(soapAction).setCredential(token, TokenType.SAML).addDefaulHandlerChain().addHandlerChain(
            HandlerChainUtil.buildChainWithValidator(
                "validation.incoming.message.mediprimauma",
                "/ehealth-mediprima-uma/XSD/ehealth-mediprima-uma-protocol-1_0.xsd"
            )
        )
    }
}
