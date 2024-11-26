package org.taktik.freehealth.middleware.dto.recipe

import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.dto.HealthcareParty
import java.util.*

data class StructuredPrescription(
    var creationDate: Date,
    var rid: String,
    var isFeedbackAllowed: Boolean? = null,
    var patientId: String? = null,
    var notificationWasSent: Boolean? = null,
    var requestXml: String? = null,
    var prescriberId: String? = null,
    var visionByOthers: String? = null,
    var status: String? = null,
    var validUntil: Date? = null,
    val prescribers: List<HealthcareParty>,
    val medications: List<Medication>
)
