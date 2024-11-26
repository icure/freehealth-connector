package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.domain.etarif.TarificationConsultationResult
import java.time.LocalDateTime
import java.util.UUID

interface TarificationService {
    fun consultTarif(keystoreId: UUID,
                     tokenId: UUID,
                     hcpFirstName: String,
                     hcpLastName: String,
                     hcpNihii: String,
                     hcpSsin: String,
                     passPhrase: String,
                     patientSsin: String?,
                     consultationDate: LocalDateTime,
                     justification: String?,
                     gmdNihii: String?,
                     traineeSupervisorSsin: String?,
                     traineeSupervisorNihii: String?,
                     traineeSupervisorFirstName: String?,
                     traineeSupervisorLastName: String?,
                     guardPostNihii: String?,
                     guardPostSsin: String?,
                     anatomies: List<String?>,
                     relatedServices: List<String?>,
                     codes: List<String>): TarificationConsultationResult
}
