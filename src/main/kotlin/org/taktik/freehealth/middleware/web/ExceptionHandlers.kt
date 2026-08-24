package org.taktik.freehealth.middleware.web

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.exception.UnauthorizedException
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType
import org.taktik.freehealth.middleware.dto.ExceptionDto
import java.io.EOFException
import java.util.Date
import javax.servlet.http.HttpServletRequest
import javax.xml.ws.soap.SOAPFaultException

/**
 * Defines custom exception handlers.
 */
@ControllerAdvice
class ExceptionHandlers {
    @ExceptionHandler(TechnicalConnectorException::class)
    fun handleTechnicalConnectorException(request: HttpServletRequest, exception: TechnicalConnectorException) =
            ExceptionDto(exception.category.httpStatus, exception, request.servletPath).toResponseEntity()

    /**
     * SoaErrorException only carries the eHealth status code in its message, while the reason why the platform
     * refused the call sits in the response's statusMessage ("This combination of search criteria is not supported.",
     * "The maxElements paging attribute is too high.", …). Append it so callers get something actionable.
     */
    @ExceptionHandler(SoaErrorException::class)
    fun handleSoaErrorException(request: HttpServletRequest, exception: SoaErrorException) =
            ExceptionDto(
                Date(),
                exception.category.httpStatus.value(),
                exception.category.httpStatus.reasonPhrase,
                listOfNotNull(exception.message, statusMessageOf(exception)).joinToString(" - "),
                request.servletPath
            ).toResponseEntity()

    @ExceptionHandler(MissingKeystoreException::class, MissingTokenException::class, UnauthorizedException::class)
    fun handleUnauthorizedException(request: HttpServletRequest, exception: Exception) =
            ExceptionDto(HttpStatus.UNAUTHORIZED, exception, request.servletPath).toResponseEntity()

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(request: HttpServletRequest, exception: IllegalArgumentException) =
            ExceptionDto(HttpStatus.BAD_REQUEST, exception, request.servletPath).toResponseEntity()

    @ExceptionHandler(SOAPFaultException::class)
    fun handleSoapFaultException(request: HttpServletRequest, exception: SOAPFaultException) =
            ExceptionDto(HttpStatus.BAD_GATEWAY, exception, request.servletPath).toResponseEntity()

    @ExceptionHandler(EOFException::class)
    fun handleEOFException(request: HttpServletRequest, exception: EOFException) = null //Nothing more to do... Connection closed

    @ExceptionHandler(Exception::class)
    fun handleException(request: HttpServletRequest, exception: Exception) =
            ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, exception, request.servletPath).toResponseEntity()
                .also { log.error("Unhandled exception on ${request.servletPath}", exception) }

    private fun statusMessageOf(exception: SoaErrorException): String? = try {
        (exception.responseTypeV2 as? StatusResponseType)?.status?.statusMessage
            ?: exception.responseType?.status?.messages?.firstOrNull()?.value
            ?: exception.errorType?.messages?.firstOrNull()?.value
    } catch (e: Exception) {
        null
    }

    companion object {
        private val log = LoggerFactory.getLogger(ExceptionHandlers::class.java)
    }
}
