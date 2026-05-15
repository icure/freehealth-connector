package org.taktik.freehealth.middleware.dao

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("org.taktik.connector.postgres")
class PostgresProperties {
    var cachettl = "120000"
    var url = "jdbc:postgresql://localhost:5432/fhc"
    var username: String? = null
    var password: String? = null
}
