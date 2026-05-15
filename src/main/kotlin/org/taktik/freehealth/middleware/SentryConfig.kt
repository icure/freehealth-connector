package org.taktik.freehealth.middleware

import io.sentry.Sentry
import org.apache.log4j.MDC
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

        Sentry.init(sentryDsn)
        Sentry.getStoredClient()?.release = release
        Sentry.getStoredClient()?.environment = activeProfile
        Sentry.getStoredClient()?.addTag("OS", System.getenv("os.name"))

        log.info("Sentry initialized for environment: {}", activeProfile)
    }
}
