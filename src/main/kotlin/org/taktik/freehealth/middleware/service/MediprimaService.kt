package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
import java.time.Instant
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
}
