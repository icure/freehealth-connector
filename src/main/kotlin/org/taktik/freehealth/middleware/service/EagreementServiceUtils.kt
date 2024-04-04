package org.taktik.freehealth.middleware.service

import com.google.gson.JsonObject
import org.joda.time.DateTime
import org.taktik.freehealth.middleware.service.impl.EagreementServiceImpl
import org.taktik.icure.fhir.entities.r4.binary.Binary
import org.taktik.icure.fhir.entities.r4.claim.Claim
import org.taktik.icure.fhir.entities.r4.claim.ClaimInsurance
import org.taktik.icure.fhir.entities.r4.claim.ClaimItem
import org.taktik.icure.fhir.entities.r4.claim.ClaimSupportingInfo
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.messageheader.MessageHeader
import org.taktik.icure.fhir.entities.r4.organization.Organization
import org.taktik.icure.fhir.entities.r4.parameters.Parameters
import org.taktik.icure.fhir.entities.r4.parameters.ParametersParameter
import org.taktik.icure.fhir.entities.r4.patient.Patient
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.practitioner.Practitioner
import org.taktik.icure.fhir.entities.r4.practitionerrole.PractitionerRole
import org.taktik.icure.fhir.entities.r4.servicerequest.ServiceRequest

interface EagreementServiceUtils {
    fun getPractitionerRole(practitionerRoleId: String, practitionerRole: String): PractitionerRole?

    fun getPractitioner(practitionerId: String, hcpNihii: String, hcpFirstName: String, hcpLastName: String): Practitioner?

    fun getCodableConcept(system: String, code: String): CodeableConcept?
    fun getClaim(
        claimId: String,
        claimStatus: String,
        subTypeCode: String,
        agreementStartDate: DateTime,
        insuranceRef: String,
        pathologyCode: String,
        pathologyStartDate: DateTime?,
        providerType: String
    ): Claim?

    fun getSupportingInfo(sequenceNumber: Int, claimInformationCategory: String, annexTypeCode: String?, valueReference: String?, valueString: String?, valueAttachmentData: String?, valueAttachmentTitle: String?, valueAttachmentContentType: String?): ClaimSupportingInfo?
    fun getOrganization(organizationId: String, orgNihii: String, organizationType: String): Organization?

    fun getPatient(patientFirstName: String, patientLastName: String, gender: String, patientSsin: String?, io: String ?, ioMembership: String?): Patient?

    fun getServiceRequest(serviceRequestId: String, prescriptionId: String, data: String, annexId: String, quantity: Float, patientFirstName: String, patientLastName: String, gender: String, patientSsin: String?, io: String?, ioMembership: String?): ServiceRequest?

    fun getContained(data: String, containedId: String): List<Binary>?

    fun getParameters(parameterId: String,
                      agreementTypes: String,
                      startDate: DateTime?,
                      endDate: DateTime?,
                      patientFirstName: String?,
                      patientLastName: String?,
                      patientGender: String?,
                      patientSsin: String?,
                      io: String?,
                      ioMembership: String?): Parameters?

    fun getParameter(parameterName: String,
                     agreementTypes: String?,
                     startDate: DateTime?,
                     endDate: DateTime?,
                     patientFirstName: String?,
                     patientLastName: String?,
                     patientGender: String?,
                     patientSsin: String?,
                     io: String?,
                     ioMembership: String?
    ): ParametersParameter?

    fun getInsurance(insuranceRef: String, display: String): ClaimInsurance?

    fun getBillablePeriod(startDate: DateTime): Period?

    fun getServicedDateItem(pathologyDate: DateTime, pathologyCode: String, sequenceNumber: Int): ClaimItem?

    fun getCodeItem(code: String): ClaimItem?

    fun getAdditionalNotes(additionalNotes: String): ClaimSupportingInfo?

    fun getPrescriptionInfos(quantity: Int, document: String, documentId: String): ServiceRequest?

    fun getMessageHeader(messageFocusReference: String, messageEventSystem: String, messageEventsCode: String, practitionerRole1UUID: String): MessageHeader?


    fun getBundleJSON(
        requestType: EagreementServiceImpl.RequestTypeEnum,
        messageFocusReference: String,
        messageEventSystem: String,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        hcpNihii: String,
        hcpFirstName: String,
        hcpLastName: String,
        hcp2Nihii: String?,
        hcp2FirstName: String?,
        hcp2LastName: String?,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?,
        insuranceRef: String?,
        pathologyCode: String,
        pathologyStartDate: DateTime?
    ): JsonObject?

}
