package org.taktik.freehealth.middleware

import io.sentry.Sentry
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class SentryConfig {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${sentry.dsn:}")
    private val sentryDsn: String? = null

    @Value("\${sentry.logs.enabled:false}")
    private val sentryEnabled: Boolean = false

    @Value("\${spring.profiles.active:development}")
    private val activeProfile: String? = null

    @Value("\${git.commit.id.full:n/a}")
    private val release: String? = null

    @EventListener(ApplicationReadyEvent::class)
    fun init() {

        if(!sentryEnabled){
            log.warn("Sentry is disabled.")
            return
        }

        if(sentryDsn.isNullOrBlank()) {
            log.warn("Sentry DSN is not configured.")
            return
        }

        Sentry.init(sentryDsn)
        val client = Sentry.getStoredClient()
        
        if(client != null) {
            client.release = release
            client.environment = activeProfile
            client.addTag("OS", System.getenv("os.name"))
            log.info("Sentry initialized for environment: {} with release: {}", activeProfile, release)
        } else {
            log.error("Failed to initialize Sentry client")
        }
    }
}
