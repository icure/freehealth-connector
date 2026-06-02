package org.taktik.freehealth.middleware

import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.web.servlet.HandlerExceptionResolver


@Configuration
class MiddlewareConfiguration {
    @Bean
    fun threadPoolTaskScheduler() = ThreadPoolTaskScheduler().apply {
        poolSize = 5
        threadNamePrefix = "ThreadPoolTaskScheduler"
    }

    @Bean
    fun sentryExceptionResolver(): HandlerExceptionResolver {
        return io.sentry.spring.SentryExceptionResolver()
    }

    @Bean
    fun sentryServletContextInitializer(): ServletContextInitializer {
        return io.sentry.spring.SentryServletContextInitializer()
    }
}
