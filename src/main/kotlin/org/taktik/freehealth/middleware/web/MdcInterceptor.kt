package org.taktik.freehealth.middleware.web

import io.sentry.Sentry
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.UUID
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class MdcInterceptor : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {


        val correlationId  : String = request.getHeader("X-Correlation-Id")
            ?.takeIf { it.isNotBlank() }
            ?: UUID.randomUUID().toString()

        MDC.put("correlationId", correlationId)
        Sentry.getContext().addTag("trace_id", correlationId)
        Sentry.getContext().addExtra("correlationId", correlationId)

        val company : String = request.getHeader("X-Company")
            ?.takeIf { it.isNotBlank() }
            ?: "NA"

        MDC.put("company", company)
        Sentry.getContext().addExtra("company", company)

        val keystoreID : String? = request.getHeader("X-FHC-keystoreId")
            ?.takeIf { it.isNotBlank() }

        MDC.put("keystoreId", keystoreID)
        Sentry.getContext().addExtra("keystoreId", keystoreID)

        val debug: Boolean = request.getHeader("X-FHC-debug")
            ?.takeIf { it.isNotBlank() }
            ?.equals("true", ignoreCase = true)
            ?: false

        MDC.put("debug", debug.toString())
        Sentry.getContext().addExtra("debug", debug.toString())

        val userAgent : String? = request.getHeader("X-User-Agent")
            ?.takeIf { it.isNotBlank() }

        MDC.put("userAgent", userAgent)
        Sentry.getContext().addExtra("userAgent", userAgent)

        val startTime: String? = request.getAttribute("startTime")
            ?.takeIf { it.toString().isNotBlank() }
            ?.toString()

        MDC.put("startTime", startTime)
        Sentry.getContext().addExtra("startTime", startTime)

        MDC.put("requestPath", request.requestURI)
        Sentry.getContext().addExtra("requestPath", request.requestURI)

        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        MDC.clear()
    }
}
