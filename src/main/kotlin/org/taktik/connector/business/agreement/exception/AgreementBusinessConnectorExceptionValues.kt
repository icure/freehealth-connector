package org.taktik.connector.business.agreement.exception

enum class AgreementBusinessConnectorExceptionValues(val errorCode: String, val message: String) {
    PARAMETER_NULL("parameters.null", "This parameter is null : {0}")

}