package org.taktik.freehealth.middleware.web

import io.sentry.Sentry
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.dto.ExceptionDto
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.exception.UnauthorizedException
import java.io.EOFException
import javax.servlet.http.HttpServletRequest
import javax.xml.ws.soap.SOAPFaultException

/**
 * Defines custom exception handlers.
 */
@ControllerAdvice
class ExceptionHandlers {
    private val logger : Log = LogFactory.getLog(ExceptionHandlers::class.java)

    @ExceptionHandler(TechnicalConnectorException::class)
    fun handleTechnicalConnectorException(request: HttpServletRequest, exception: TechnicalConnectorException): ResponseEntity<ExceptionDto>{
        logger.error("Unexpected error: " + exception.message, exception);
        Sentry.capture(exception)
        return ExceptionDto(exception.category.httpStatus, exception, request.servletPath).toResponseEntity()
    }


    @ExceptionHandler(MissingKeystoreException::class, MissingTokenException::class, UnauthorizedException::class)
    fun handleUnauthorizedException(request: HttpServletRequest, exception: Exception): ResponseEntity<ExceptionDto> {
        logger.error("Unexpected error: " + exception.message, exception);
        Sentry.capture(exception)
        return ExceptionDto(HttpStatus.UNAUTHORIZED, exception, request.servletPath).toResponseEntity()
    }


    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(request: HttpServletRequest, exception: IllegalArgumentException): ResponseEntity<ExceptionDto>{
        logger.error("Unexpected error: " + exception.message, exception);
        Sentry.capture(exception)
        return ExceptionDto(HttpStatus.BAD_REQUEST, exception, request.servletPath).toResponseEntity()
    }


    @ExceptionHandler(SOAPFaultException::class)
    fun handleSoapFaultException(request: HttpServletRequest, exception: SOAPFaultException): ResponseEntity<ExceptionDto>{
        logger.error("Unexpected error: " + exception.message, exception);
        Sentry.capture(exception)
        return ExceptionDto(HttpStatus.BAD_GATEWAY, exception, request.servletPath).toResponseEntity()
    }


    @ExceptionHandler(EOFException::class)
    fun handleEOFException(request: HttpServletRequest, exception: EOFException) : ResponseEntity<ExceptionDto>? {
        logger.error("Unexpected error: " + exception.message, exception);
        Sentry.capture(exception)
        return null//Nothing more to do... Connection closed
    }

    @ExceptionHandler(Exception::class)
    fun handleException(request: HttpServletRequest, exception: Exception) : ResponseEntity<ExceptionDto> {
        logger.error("Unexpected error: " + exception.message, exception);
        Sentry.capture(exception)
        return ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, exception, request.servletPath).toResponseEntity()
    }

}
