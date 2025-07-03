package org.taktik.freehealth.middleware.web.controllers

import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
import com.google.gson.Gson
import ma.glasnost.orika.MapperFacade
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.connector.business.domain.etarif.TarificationMediprimaConsultationResult
import org.taktik.freehealth.middleware.domain.mediprima.MediprimaMdaResponse
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.service.MediprimaService
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID

internal val timezone: String = "Europe/Brussels"

@RestController
@RequestMapping("/mediprima")
class MediprimaController(
    val mediprimaService: MediprimaService,
    val mapper: MapperFacade
) {
    internal val mcnTimezone: String = "Europe/Brussels"
    private val ConsultTarifErrors =
        Gson().fromJson(
            this.javaClass.getResourceAsStream("/be/errors/ConsultTarifErrors.json").reader(Charsets.UTF_8),
            arrayOf<MycarenetError>().javaClass
        ).associateBy({ it.uid }, { it })

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
    ): MediprimaMdaResponse? {
        val instantStartDate: Instant = startDate?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        val instantEndDate: Instant = endDate?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        val instantReferenceDate: Instant = referenceDate?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()

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


    @PostMapping("/consultTarificationMediprima/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultMediprimaTarification(
        @PathVariable patientSsin: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam date: Long,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?,
        @RequestBody codes: List<String>
    ): TarificationMediprimaConsultationResult? {
        return mediprimaService.consultTarif(
            keystoreId = keystoreId,
            tokenId = tokenId,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            passPhrase = passPhrase,
            patientSsin = patientSsin,
            consultationDate = date?.let { LocalDateTime.of((date / 10000).toInt(), ((date / 100).toInt() % 100), (date % 100).toInt(), 0, 0)} ?: LocalDateTime.now(),
            traineeSupervisorSsin = traineeSupervisorSsin,
            traineeSupervisorNihii = traineeSupervisorNihii,
            traineeSupervisorFirstName = traineeSupervisorFirstName,
            traineeSupervisorLastName = traineeSupervisorLastName,
            codes = codes
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
