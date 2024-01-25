package org.taktik.connector.business.agreement.exception

import org.taktik.connector.technical.exception.ConnectorException
import java.text.MessageFormat

class AgreementBusinessConnectorException(
    errorCodeValue: AgreementBusinessConnectorExceptionValues,
    vararg params: Any?
) : ConnectorException(MessageFormat.format(errorCodeValue.message, *params), errorCodeValue.errorCode) {
    companion object {
        private const val serialVersionUID = 1L
    }
}