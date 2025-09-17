package org.taktik.connector.business.agreement.service

import org.apache.commons.lang.Validate
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType

object ServiceFactory {
    private const val PROP_ENDPOINT_AGREEMENT = "endpoint.eagreement"
    private val expectedProps: List<String?> = listOf();
    private var config: Configuration? = null
    @Throws(TechnicalConnectorException::class)
    fun getAgreementPort(token: SAMLToken?): GenericRequest {
        Validate.notNull(token, "Required parameter SAMLToken is null.")
        return GenericRequest().setEndpoint(
            config!!.getProperty(
                PROP_ENDPOINT_AGREEMENT,
                "\$uddi{uddi:ehealth-fgov-be:business:mycareneteagreement:v1}"
            )
        ).setCredential(token, TokenType.SAML).addDefaulHandlerChain()
    }

    init {
        config = ConfigFactory.getConfigValidator(expectedProps)
    }
}
