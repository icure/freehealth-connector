package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.mediprimaUma.protocol.DeleteUrgentMedicalAidAttestationResponseType
import be.fgov.ehealth.mediprimaUma.protocol.SearchUrgentMedicalAidAttestationResponseType
import be.fgov.ehealth.mediprimaUma.protocol.SendUrgentMedicalAidAttestationResponseType
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaDeleteUrgentMedicalAidAttestationResponse
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaSearchUrgentMedicalAidAttestationResponse
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaSendUrgentMedicalAidAttestationResponse
import java.time.Instant
import java.util.UUID

interface MediprimaUmaService {
    fun sendUrgentMedicalAidAttestation(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        startDate: Instant,
        endDate: Instant,
        medicalCover: String
    ): MediprimaUmaSendUrgentMedicalAidAttestationResponse?

    fun searchUrgentMedicalAidAttestation(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        attestationNumber: String?,
        startDate: Instant?,
        endDate: Instant?,
        medicalCover: String?
    ): MediprimaUmaSearchUrgentMedicalAidAttestationResponse?

    fun deleteUrgentMedicalAidAttestation(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        attestationNumber: String
    ): MediprimaUmaDeleteUrgentMedicalAidAttestationResponse?
}
