package org.taktik.freehealth.middleware.web.controllers

import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
import ma.glasnost.orika.MapperFacade
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID

internal val timezone: String = "Europe/Brussels"

@RestController
@RequestMapping("/mediprima")
class MediprimaController(val mediprimaService: org.taktik.freehealth.middleware.service.MediprimaService, val mapper: MapperFacade) {
    @PostMapping("/consultMediprima/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultMediprima(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpName: String,
        @PathVariable patientSsin: String,
        @RequestParam (required = false) startDate: Long?,
        @RequestParam (required = false) endDate: Long?,
        @RequestParam (required = false) referenceDate: Long?
    ): ConsultCarmedInterventionResponseType? {
        val instantStartDate: Instant = toInstantFromLongWithFormatter(startDate)!!
        val instantEndDate: Instant = toInstantFromLongWithFormatter(endDate)!!
        val instantReferenceDate: Instant = toInstantFromLongWithFormatter(referenceDate)!!

        return mediprimaService.consultCaremedData(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpQuality = hcpQuality,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpName = hcpName,
            passPhrase = passPhrase,
            patientSsin = patientSsin,
            startDate = instantStartDate,
            endDate = instantEndDate,
            referenceDate = instantReferenceDate
        )
    }

    fun toInstantFromLongWithFormatter(dateLong: Long?): Instant? {
        return dateLong?.let { value ->
            val text = value.toString()
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val localDate = LocalDate.parse(text, formatter)
            localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        }
    }
}
