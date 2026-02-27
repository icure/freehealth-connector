package org.taktik.freehealth.middleware.web.controllers

import org.taktik.freehealth.middleware.mapper.MapperFacade
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
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.service.MediprimaUmaService
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID

@RestController
@RequestMapping("/mediprimaUma")
@Tag(name = "MediprimaUma", description = "Mediprima UMA (User-Managed Access) endpoints for urgent medical aid attestations.")
class MediprimaUmaController(
    val mediprimaUmaService: MediprimaUmaService,
    val mapper: MapperFacade
){
    internal val mcnTimezone: String = "Europe/Brussels"
    @Operation(
        summary = "Send urgent medical aid attestation",
        description = "Sends an urgent medical aid (UMA) attestation for a patient identified by their SSIN."
    )
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
    @Operation(
        summary = "Search urgent medical aid attestations",
        description = "Searches for existing urgent medical aid (UMA) attestations for a patient, with optional filters for date range, medical cover, and attestation number."
    )
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
    @Operation(
        summary = "Delete urgent medical aid attestation",
        description = "Deletes an existing urgent medical aid (UMA) attestation identified by its attestation number for a given patient."
    )
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
