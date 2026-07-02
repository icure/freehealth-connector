package org.taktik.freehealth.middleware

import io.sentry.Sentry
import io.sentry.event.EventBuilder
import io.sentry.event.interfaces.ExceptionInterface
import io.sentry.event.interfaces.HttpInterface
import io.sentry.event.interfaces.SentryException
import io.sentry.event.interfaces.SentryStackTraceElement
import io.sentry.event.interfaces.StackTraceInterface
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import java.io.File
import java.util.ArrayDeque

@Configuration
class SentryConfig {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val scrubbedHeaderNames = setOf("X-FHC-PassPhrase")
    private val scrubbedHeaderValue = "[Filtered]"

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
            val resolvedRelease = normalizeRelease(release) ?: resolveJarVersion()
            resolvedRelease?.let { client.release = it }
            client.environment = activeProfile
            client.addTag("OS", System.getenv("os.name"))
            client.addBuilderHelper { eventBuilder -> scrubData(eventBuilder) }
            log.info("Sentry initialized for environment: {} with release: {}", activeProfile, resolvedRelease)
        } else {
            log.error("Failed to initialize Sentry client")
        }
    }

    private fun scrubData(eventBuilder: EventBuilder) {
        val sentryInterfaces = eventBuilder.event.sentryInterfaces
        val httpInterface = sentryInterfaces[HttpInterface.HTTP_INTERFACE] as? HttpInterface
        if (httpInterface != null) {
            eventBuilder.withSentryInterface(
                HttpInterface(
                    httpInterface.requestUrl,
                    httpInterface.method,
                    httpInterface.parameters,
                    httpInterface.queryString,
                    httpInterface.cookies,
                    httpInterface.remoteAddr,
                    httpInterface.serverName,
                    httpInterface.serverPort,
                    httpInterface.localAddr,
                    httpInterface.localName,
                    httpInterface.localPort,
                    httpInterface.protocol,
                    httpInterface.isSecure,
                    httpInterface.isAsyncStarted,
                    httpInterface.authType,
                    httpInterface.remoteUser,
                    scrubHeaders(httpInterface.headers),
                    httpInterface.body
                ),
                true
            )
        }

        val stackTraceInterface = sentryInterfaces[StackTraceInterface.STACKTRACE_INTERFACE] as? StackTraceInterface
        if (stackTraceInterface != null) {
            eventBuilder.withSentryInterface(sanitizeStackTraceInterface(stackTraceInterface), true)
        }

        val exceptionInterface = sentryInterfaces[ExceptionInterface.EXCEPTION_INTERFACE] as? ExceptionInterface
        if (exceptionInterface != null) {
            eventBuilder.withSentryInterface(sanitizeExceptionInterface(exceptionInterface), true)
        }
    }

    private fun scrubHeaders(headers: Map<String, Collection<String>>): Map<String, Collection<String>> {
        return headers.mapValues { (headerName, values) ->
            if (shouldScrubHeader(headerName)) {
                listOf(scrubbedHeaderValue)
            } else {
                values.toList()
            }
        }
    }

    private fun shouldScrubHeader(headerName: String): Boolean {
        return scrubbedHeaderNames.any { it.equals(headerName, ignoreCase = true) }
    }

    private fun sanitizeExceptionInterface(exceptionInterface: ExceptionInterface): ExceptionInterface {
        var hasChanges = false
        val sanitizedExceptions = ArrayDeque<SentryException>(exceptionInterface.exceptions.size)
        exceptionInterface.exceptions.forEach { sentryException ->
            val sanitizedStackTrace = sanitizeStackTraceInterface(sentryException.stackTraceInterface)
            if (sanitizedStackTrace !== sentryException.stackTraceInterface) {
                hasChanges = true
            }
            sanitizedExceptions.add(
                SentryException(
                    sentryException.exceptionMessage,
                    sentryException.exceptionClassName,
                    sentryException.exceptionPackageName,
                    sanitizedStackTrace,
                    sentryException.exceptionMechanism
                )
            )
        }
        return if (hasChanges) ExceptionInterface(sanitizedExceptions) else exceptionInterface
    }

    private fun sanitizeStackTraceInterface(stackTraceInterface: StackTraceInterface): StackTraceInterface {
        if (stackTraceInterface.stackTrace.none { it.lineno < 0 }) {
            return stackTraceInterface
        }
        val sanitizedFrames = stackTraceInterface.stackTrace.map { frame ->
            val safeLineNo = frame.lineno.coerceAtLeast(0)
            if (safeLineNo == frame.lineno) {
                frame
            } else {
                SentryStackTraceElement(
                    frame.module,
                    frame.function,
                    frame.fileName,
                    safeLineNo,
                    frame.colno,
                    frame.absPath,
                    frame.platform,
                    frame.locals
                )
            }
        }.toTypedArray()
        return StackTraceInterface(sanitizedFrames)
    }

    private fun normalizeRelease(rawRelease: String?): String? {
        val trimmedRelease = rawRelease?.trim()
        if (trimmedRelease.isNullOrEmpty()) {
            return null
        }
        return if (trimmedRelease.equals("n/a", ignoreCase = true)) null else trimmedRelease
    }

    private fun resolveJarVersion(): String? {
        val implementationVersion = this.javaClass.`package`?.implementationVersion?.trim()
        if (!implementationVersion.isNullOrEmpty()) {
            return implementationVersion
        }

        val jarName = runCatching {
            File(this.javaClass.protectionDomain.codeSource.location.toURI()).name
        }.getOrNull() ?: return null

        return Regex("-(\\d[^-]*)\\.jar$").find(jarName)?.groupValues?.get(1)
    }
}
