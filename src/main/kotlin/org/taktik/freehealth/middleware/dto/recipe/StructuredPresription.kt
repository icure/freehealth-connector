package org.taktik.freehealth.middleware.dto.recipe

import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.dto.HealthcareParty

data class StructuredPrescription(
    val prescribers: List<HealthcareParty>,
    val patientId: String,
    val medications: List<Medication>
)
