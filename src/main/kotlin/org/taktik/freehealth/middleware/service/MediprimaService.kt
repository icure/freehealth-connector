package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
import org.taktik.connector.business.domain.etarif.TarificationMediprimaConsultationResult
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

interface MediprimaService {
    fun consultCaremedData(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String?,
        hcpName: String,
        passPhrase: String,
        patientSsin: String?,
        startDate: Instant,
        endDate: Instant,
        referenceDate: Instant
    ): ConsultCarmedInterventionResponseType?

    fun consultTarif(
        keystoreId: UUID,
        tokenId: UUID,
        hcpFirstName: String,
        hcpLastName: String,
        hcpNihii: String,
        hcpSsin: String,
        passPhrase: String,
        patientSsin: String?,
        consultationDate: LocalDateTime,
        traineeSupervisorSsin: String?,
        traineeSupervisorNihii: String?,
        traineeSupervisorFirstName: String?,
        traineeSupervisorLastName: String?,
        codes: List<String>): TarificationMediprimaConsultationResult?
}
