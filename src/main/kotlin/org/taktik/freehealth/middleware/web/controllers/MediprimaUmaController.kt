package org.taktik.freehealth.middleware.web.controllers

import ma.glasnost.orika.MapperFacade
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaDeleteUrgentMedicalAidAttestationResponse
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaSearchUrgentMedicalAidAttestationResponse
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaSendUrgentMedicalAidAttestationResponse
import org.taktik.freehealth.middleware.service.MediprimaUmaService
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID

@RestController
@RequestMapping("/mediprimaUma")
class MediprimaUmaController(
    val mediprimaUmaService: MediprimaUmaService,
    val mapper: MapperFacade
){
    internal val mcnTimezone: String = "Europe/Brussels"
    @PostMapping("/sendUrgentMedicalAidAttestation/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendUrgentMedicalAidAttestation(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpName: String,
        @RequestParam hcpFirstName: String,
        @PathVariable patientSsin: String,
        @RequestParam medicalCover: String,
        @RequestParam startDate: Long,
        @RequestParam endDate: Long
    ): MediprimaUmaSendUrgentMedicalAidAttestationResponse? {
        return mediprimaUmaService.sendUrgentMedicalAidAttestation(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpName,
            patientSsin = patientSsin,
            medicalCover = medicalCover,
            startDate = longDateToInstant(startDate),
            endDate = longDateToInstant(endDate)
        )
    }
    @PostMapping("/searchUrgentMedicalAidAttestation/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun searchUrgentMedicalAidAttestation(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpName: String,
        @RequestParam hcpFirstName: String,
        @PathVariable patientSsin: String,
        @RequestParam (required = false) medicalCover: String?,
        @RequestParam (required = false) startDate: Long?,
        @RequestParam (required = false) endDate: Long?,
        @RequestParam (required = false) attestationNumber: String?
    ): MediprimaUmaSearchUrgentMedicalAidAttestationResponse? {
        return mediprimaUmaService.searchUrgentMedicalAidAttestation(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpName,
            patientSsin = patientSsin,
            attestationNumber = attestationNumber,
            startDate = longDateToInstant(startDate),
            endDate = longDateToInstant(endDate),
            medicalCover = medicalCover
        )
    }
    @PostMapping("/deleteUrgentMedicalAidAttestation/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun deleteUrgentMedicalAidAttestation(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpName: String,
        @RequestParam hcpFirstName: String,
        @PathVariable patientSsin: String,
        @RequestParam attestationNumber: String
    ): MediprimaUmaDeleteUrgentMedicalAidAttestationResponse? {
        return mediprimaUmaService.deleteUrgentMedicalAidAttestation(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpName,
            patientSsin = patientSsin,
            attestationNumber = attestationNumber
        )
    }

    private fun longDateToInstant(dateLong: Long?, timezone: String = "Europe/Brussels"): Instant {
        if (dateLong == null) {
            return LocalDate.now().atStartOfDay(ZoneId.of(timezone)).toInstant()
        }

        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val localDate = LocalDate.parse(dateLong.toString(), formatter)
            localDate.atStartOfDay(ZoneId.of(timezone)).toInstant()
        } catch (e: Exception) {
            // fallback en cas d’erreur de parsing
            LocalDate.now().atStartOfDay(ZoneId.of(timezone)).toInstant()
        }
    }
}
