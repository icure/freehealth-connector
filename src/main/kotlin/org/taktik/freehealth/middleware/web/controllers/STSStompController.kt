package org.taktik.freehealth.middleware.web.controllers

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.taktik.freehealth.middleware.domain.sts.StompMessage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.service.RemoteKeystoreService
import java.security.Principal
import java.util.*

@Controller
@Tag(name = "STSStomp", description = "WebSocket STOMP endpoints for STS (Security Token Service) operations and remote keystore management.")
class STSStompController(val remoteKeystoreService: RemoteKeystoreService) {
    @Operation(
        summary = "Register STS connection",
        description = "Registers a WebSocket STOMP connection for STS operations using the principal's identity."
    )
    @MessageMapping("/sts")
    fun connectSts(principal: Principal) {
        remoteKeystoreService.registerConnection(UUID.fromString(principal.name))
    }

    @Operation(
        summary = "Publish STS response",
        description = "Publishes a response message over the WebSocket STOMP connection for remote keystore operations."
    )
    @MessageMapping("/msg")
    fun connectSts(principal: Principal, message: StompMessage) {
        remoteKeystoreService.publishResponse(UUID.fromString(principal.name), message.content)
    }
}
