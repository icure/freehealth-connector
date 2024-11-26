package org.taktik.freehealth.middleware.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.Test

import org.junit.jupiter.api.Assertions.*
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.taktik.icure.fhir.entities.r4.practitionerrole.PractitionerRole

class EagreementServiceUtilsTest {

    val agreementServiceUtils = EagreementServiceUtilsImpl()
    @Test
    fun getPractitionerRole() {
        val role: String = "persphysiotherapist"
        val practitionerId: String = "1"
        val practitionerRole: PractitionerRole = agreementServiceUtils.getPractitionerRole(practitionerId, role);

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(practitionerRole))

        assertThat(practitionerRole).isNotNull
        assertThat(practitionerRole.id).isEqualTo("PractitionerRole$practitionerId")

        assertThat(practitionerRole.meta).isNotNull
        assertThat(practitionerRole.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-practitionerrole")

        assertThat(practitionerRole.code).isNotNull
        assertThat(practitionerRole.code).hasSize(1)
        assertThat(practitionerRole.code.first().coding).hasSize(1)
        assertThat(practitionerRole.code.first().coding.first().system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/core/CodeSystem/cd-hcparty")
        assertThat(practitionerRole.code.first().coding.first().code).isEqualTo(role)

    }

    @Test
    fun getPractitioner() {
        val practitionerId = "1"
        val hcpNihii = "54263481527"
        val hcpFirstName = "John"
        val hcpLastName = "Smith"

        val practitioner = agreementServiceUtils.getPractitioner(practitionerId, hcpNihii, hcpFirstName, hcpLastName)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(practitioner))

        assertNotNull(practitioner)
        assertEquals("Practitioner$practitionerId", practitioner.id)

        assertEquals(
            listOf("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-practitioner"),
            practitioner.meta?.profile
        )

        assertEquals(1, practitioner.identifier?.size)
        val identifier = practitioner.identifier?.first()
        assertEquals("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/nihdi", identifier?.system)
        assertEquals(hcpNihii, identifier?.value)

        assertEquals(1, practitioner.name?.size)
        val name = practitioner.name?.first()
        assertEquals(hcpLastName, name?.family)
        assertEquals(listOf(hcpFirstName), name?.given)
    }

    @Test
    fun getCodableConcept() {

        val codeableConcept = agreementServiceUtils.getCodableConcept("https://example.com/system", "exampleCode")

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(codeableConcept))

        assertThat(codeableConcept).isNotNull

        assertThat(codeableConcept.coding).isNotNull
        assertThat(codeableConcept.coding).hasSize(1)

        val coding = codeableConcept.coding?.get(0)
        assertThat(coding).isNotNull
        assertThat(coding).isInstanceOf(Coding::class.java)

        assertThat(coding?.system).isEqualTo("https://example.com/system")
        assertThat(coding?.code).isEqualTo("exampleCode")
    }

    @Test
    fun getClaim() {
        // Créer une instance de la classe AgreementServiceUtils

        // Appeler la fonction que vous souhaitez tester
        val claim = agreementServiceUtils.getClaim(
            EagreementServiceImpl.RequestTypeEnum.ASK,
            "1",
            "active",
            "physiotherapy-fb",
            DateTime.now(),
            "InsuranceRef123",
            "PathologyCode123",
            DateTime.now().plusDays(1),
            "PractitionerRole/PractitionerRole1"
        )

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(claim))

        assertThat(claim).isNotNull
        assertThat(claim.id).isEqualTo("Claim1")
        assertThat(claim.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementclaim-kine")
        assertThat(claim.status).isEqualTo("active")
        assertThat(claim.subType?.coding?.get(0)?.system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/agreement-types")
        assertThat(claim.subType?.coding?.get(0)?.code).isEqualTo("physiotherapy-fb")
        assertThat(claim.use).isEqualTo("preauthorization")
    }

    @Test
    fun getSupportingInfo() {

        val supportingInfo1 = agreementServiceUtils.getSupportingInfo(1, "attachment", "functional-report", null, null, "QW5uZXhlIGlubGluZSwgYmFzZTY0ZWQ=", "nom/description de l'annexe", "application/pdf")
        val supportingInfo2 = agreementServiceUtils.getSupportingInfo(2, "info", null, null, "additional Information", null, null, null)
        val supportingInfo3 = agreementServiceUtils.getSupportingInfo(3, "info", null, "ServiceRequest/ServiceRequest2", null, null, null, null)

        println("Result 1: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(supportingInfo1))
        println("Result 2: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(supportingInfo2))
        println("Result 3: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(supportingInfo3))

        assertThat(supportingInfo1).isNotNull
        assertThat(supportingInfo1.sequence).isEqualTo(1)
        assertThat(supportingInfo1.code?.coding).hasSize(1)
        assertThat(supportingInfo1.code?.coding?.first()?.code).isEqualTo("functional-report")
        assertThat(supportingInfo1.code?.coding?.first()?.system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/annex-types")
        assertThat(supportingInfo1.category?.coding).hasSize(1)
        assertThat(supportingInfo1.category?.coding?.first()?.code).isEqualTo("attachment")
        assertThat(supportingInfo1.category?.coding?.first()?.system).isEqualTo("http://terminology.hl7.org/CodeSystem/claiminformationcategory")

        assertThat(supportingInfo2).isNotNull
        assertThat(supportingInfo2.sequence).isEqualTo(2)
        assertThat(supportingInfo2.category?.coding).hasSize(1)
        assertThat(supportingInfo2.category?.coding?.first()?.code).isEqualTo("info")
        assertThat(supportingInfo2.category?.coding?.first()?.system).isEqualTo("http://terminology.hl7.org/CodeSystem/claiminformationcategory")
        assertThat(supportingInfo2.valueString).isEqualTo("additional Information")

        assertThat(supportingInfo3).isNotNull
        assertThat(supportingInfo3.sequence).isEqualTo(3)
        assertThat(supportingInfo3.category?.coding).hasSize(1)
        assertThat(supportingInfo3.category?.coding?.first()?.code).isEqualTo("info")
        assertThat(supportingInfo3.category?.coding?.first()?.system).isEqualTo("http://terminology.hl7.org/CodeSystem/claiminformationcategory")
        assertThat(supportingInfo3.valueReference?.reference).isEqualTo("ServiceRequest/ServiceRequest2")
    }

    @Test
    fun getOrganization() {
        val organizationId = "1";
        val orgNihii = "71000436000"
        val orgType = "orghospital"

        val organization = agreementServiceUtils.getOrganization(organizationId, orgNihii, orgType)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(organization))

        assertThat(organization).isNotNull
        assertThat(organization.id).isEqualTo("Organization$organizationId")
        assertThat(organization.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-organization")
        assertThat(organization.identifier).hasSize(1)
        assertThat(organization.identifier.first().system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/nihdi")
        assertThat(organization.identifier.first().value).isEqualTo(orgNihii)

        assertThat(organization.type).isNotNull
        assertThat(organization.type.first().coding).hasSize(1)
        assertThat(organization.type.first().coding.first().system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/core/CodeSystem/cd-hcparty")
        assertThat(organization.type.first().coding.first().code).isEqualTo(orgType)

    }

    @Test
    fun getPatient() {

        val patientWithSsin = agreementServiceUtils.getPatient("Jean", "Dupont", "male", "73031805784", null, null)
        val patientWithoutSsin = agreementServiceUtils.getPatient("Jean", "Dupont", "male", null, "109", "45613414615SDE")

        println("Result with ssin: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(patientWithSsin))
        println("Result without ssin: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(patientWithoutSsin))

        assertThat(patientWithSsin).isNotNull
        assertThat(patientWithSsin.id).isEqualTo("Patient1")
        assertThat(patientWithSsin.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-patient")
        assertThat(patientWithSsin.identifier).hasSize(1)
        assertThat(patientWithSsin.identifier.first().system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/ssin")
        assertThat(patientWithSsin.identifier.first().value).isEqualTo("73031805784")
        assertThat(patientWithSsin.name).hasSize(1)
        assertThat(patientWithSsin.name.first().family).isEqualTo("Dupont")
        assertThat(patientWithSsin.name.first().given).hasSize(1)
        assertThat(patientWithSsin.name.first().given.first()).isEqualTo("Jean")
        assertThat(patientWithSsin.gender).isEqualTo("male")


        assertThat(patientWithoutSsin).isNotNull
        assertThat(patientWithoutSsin.id).isEqualTo("Patient1")
        assertThat(patientWithoutSsin.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-patient")
        assertThat(patientWithoutSsin.identifier).hasSize(1)
        assertThat(patientWithoutSsin.identifier.first().system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancymembership")
        assertThat(patientWithoutSsin.identifier.first().value).isEqualTo("45613414615SDE")
        assertThat(patientWithoutSsin.identifier.first().assigner?.identifier?.system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancenumber")
        assertThat(patientWithoutSsin.identifier.first().assigner?.identifier?.value).isEqualTo("109")
        assertThat(patientWithSsin.name.first().family).isEqualTo("Dupont")
        assertThat(patientWithSsin.name.first().given).hasSize(1)
        assertThat(patientWithSsin.name.first().given.first()).isEqualTo("Jean")
        assertThat(patientWithSsin.gender).isEqualTo("male")
    }

    @Test
    fun getServiceRequest() {

        val serviceRequest = agreementServiceUtils.getServiceRequest(
            "1",
            "BE789468461564",
            "QW5uZXhlIGlubGluZSwgYmFzZTY0ZWQ=",
            "1",
            15f,
            "17845784004",
            "John",
            "male",
            "78457845896",
            "109",
            "45464116491BE"
        )

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(serviceRequest))

        assertThat(serviceRequest).isNotNull
        assertThat(serviceRequest.id).isEqualTo("ServiceRequest1")
        assertThat(serviceRequest.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementservicerequest")
        assertThat(serviceRequest.category).isNotNull
        assertThat(serviceRequest.category).hasSize(1)
        assertThat(serviceRequest.category.first().coding).hasSize(1)
        assertThat(serviceRequest.category.first().coding.first().system).isEqualTo("http://snomed.info/sct")
        assertThat(serviceRequest.category.first().coding.first().code).isEqualTo("91251008")
        assertThat(serviceRequest.code).isNotNull
        assertThat(serviceRequest.code?.coding).hasSize(1)
        assertThat(serviceRequest.code?.coding?.first()?.system).isEqualTo("http://snomed.info/sct")
        assertThat(serviceRequest.code?.coding?.first()?.code).isEqualTo("91251008")
        assertThat(serviceRequest.contained).hasSize(1)
        assertThat(serviceRequest.contained?.first()?.id).isEqualTo("annexSR1")
        assertThat(serviceRequest.identifier).hasSize(1)
        assertThat(serviceRequest.identifier?.first()?.system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/uhmep")
        assertThat(serviceRequest.identifier?.first()?.value).isEqualTo("BE789468461564")
        assertThat(serviceRequest.requester?.reference).isEqualTo("PractitionerRole/PractitionerRole2")
        assertThat(serviceRequest.subject?.reference).isEqualTo("Patient/Patient1")
        assertThat(serviceRequest.supportingInfo).hasSize(1)
        assertThat(serviceRequest.supportingInfo?.first()?.reference).isEqualTo("#annexSR1")

    }

    @Test
    fun getContained() {

        val containedList = agreementServiceUtils.getContained("pdfData", "123")

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(containedList))

        assertThat(containedList).isNotNull
        assertThat(containedList).hasSize(1)

        val binary = containedList[0]
        assertThat(binary).isNotNull
        assertThat(binary.contentType).isEqualTo("application/pdf")
        assertThat(binary.data).isEqualTo("pdfData")
        assertThat(binary.id).isEqualTo("annexSR123")
    }

    @Test
    fun getParameters() {

        val parameterId = "1"
        val agreementTypes = "physiotherapy"
        val startDate = DateTime.now()
        val endDate = startDate.plusDays(7)
        val patientFirstName = "John"
        val patientLastName = "Doe"
        val patientGender = "male"
        val patientSsin = "123456789"
        val io = "IO123"
        val ioMembership = "IOMembership123"

        val parameters = agreementServiceUtils.getParameters(
            parameterId,
            agreementTypes,
            startDate,
            endDate,
            patientFirstName,
            patientLastName,
            patientGender,
            patientSsin,
            io,
            ioMembership
        )

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(parameters))

        assertThat(parameters).isNotNull
        assertThat(parameters.id).isEqualTo("Parameters$parameterId")

    }

    @Test
    fun getParameter() {

        val parameterName = "subType"
        val agreementTypes = "someAgreementType"
        val startDate = DateTime.now()
        val endDate = startDate.plusDays(7)
        val patientFirstName = "John"
        val patientLastName = "Doe"
        val patientGender = "male"
        val patientSsin = "123456789"
        val io = "IO123"
        val ioMembership = "IOMembership123"

        val parameter = agreementServiceUtils.getParameter(
            parameterName,
            startDate,
            endDate,
            patientFirstName,
            patientLastName,
            patientGender,
            patientSsin,
            io,
            ioMembership
        )

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(parameter))

        assertThat(parameter).isNotNull
        assertThat(parameter.name).isEqualTo(parameterName)

        when (parameterName) {
            "resourceType" -> assertThat(parameter.valueString).isEqualTo("Claim")
            "patient" -> {
                val reference = parameter.valueReference
                assertThat(reference).isNotNull
                // Add assertions for reference properties if needed
            }
            "use" -> assertThat(parameter.valueCode).isEqualTo("preauthorization")
            "subType" -> {
                val coding = parameter.valueCoding
                assertThat(coding).isNotNull
                assertThat(coding?.system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/agreement-types")
                assertThat(coding?.code).isEqualTo(agreementTypes)
            }
            "preAuthPeriod" -> {
                val period = parameter.valuePeriod
                assertThat(period).isNotNull
                assertThat(period?.start).isEqualTo(startDate.toString())
                assertThat(period?.end).isEqualTo(endDate.toString())
            }
            else -> throw IllegalArgumentException("Unexpected parameter name: $parameterName")
        }
    }

    @Test
    fun getInsurance() {
        val insuranceRef = "InsuranceRef123"
        val display = "Insurance Display"

        val insurance = agreementServiceUtils.getInsurance(EagreementServiceImpl.RequestTypeEnum.ASK, insuranceRef, display)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(insurance))

        assertThat(insurance).isNotNull
        assertThat(insurance.sequence).isEqualTo(1)
        assertThat(insurance.focal).isTrue()
        assertThat(insurance.coverage.display).isEqualTo(display)
        assertThat(insurance.preAuthRef).containsExactly(insuranceRef)
    }

    @Test
    fun getBillablePeriod() {
        val startDate = DateTime.now()
        val period = agreementServiceUtils.getBillablePeriod(startDate)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(period))

        assertThat(period).isNotNull
    }

    @Test
    fun getServicedDateItem() {
        val pathologyDate = DateTime.now()
        val pathologyCode = "fb-51"
        val claimItem = agreementServiceUtils.getServicedDateItem(EagreementServiceImpl.RequestTypeEnum.ASK, pathologyDate, pathologyCode, 1)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(claimItem))

        assertThat(claimItem).isNotNull
        assertThat(claimItem.productOrService).isNotNull
        assertThat(claimItem.productOrService.coding).hasSize(1)
        assertThat(claimItem.productOrService.coding[0].system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/nihdi-physiotherapy-pathologysituationcode")
        assertThat(claimItem.productOrService.coding[0].code).isEqualTo(pathologyCode)
    }

    @Test
    fun getCodeItem() {
        val code = "someCode"
        val claimItem = agreementServiceUtils.getCodeItem(code)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(claimItem))

        assertThat(claimItem).isNotNull
        assertThat(claimItem.productOrService).isNotNull
        assertThat(claimItem.productOrService.coding).hasSize(1)
        assertThat(claimItem.productOrService.coding[0].system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/nihdi-physiotherapy-pathologysituationcode")
        assertThat(claimItem.productOrService.coding[0].code).isEqualTo(code)
    }

    @Test
    fun getAdditionalNotes() {
        val additionalNotes = "Some additional notes"
        val claimSupportingInfo = agreementServiceUtils.getAdditionalNotes(additionalNotes)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(claimSupportingInfo))

        assertThat(claimSupportingInfo).isNotNull
        assertThat(claimSupportingInfo.sequence).isEqualTo(2)
        assertThat(claimSupportingInfo.category).isNotNull
        assertThat(claimSupportingInfo.category.coding).hasSize(1)
        assertThat(claimSupportingInfo.category.coding[0].system).isEqualTo("http://terminology.hl7.org/CodeSystem/claiminformationcategory")
        assertThat(claimSupportingInfo.category.coding[0].code).isEqualTo("info")
        assertThat(claimSupportingInfo.valueString).isEqualTo(additionalNotes)

    }

    @Test
    fun getPrescriptionInfos() {

    }

    @Test
    fun getMessageHeader() {
        val claim = agreementServiceUtils.getClaim(

            EagreementServiceImpl.RequestTypeEnum.ASK,
            "1",
            "active",
            "physiotherapy-fb",
            DateTime.now(),
            "InsuranceRef123",
            "PathologyCode123",
            DateTime.now().plusDays(1),
            "PractitionerRole/PractitionerRole1"
        )

        val messageHeader = agreementServiceUtils.getMessageHeader(
            "Claim/Claim1",
            "https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/message-events",
            "claim-ask",
            "PractitionerRole/PractitionerRole1"
        )

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(messageHeader));

    }

    @Test
    fun getBundle() {
        val claim = agreementServiceUtils.getClaim(
            EagreementServiceImpl.RequestTypeEnum.ASK,
            "1",
            "active",
            "physiotherapy-fb",
            DateTime.now(),
            "InsuranceRef123",
            "PathologyCode123",
            DateTime.now().plusDays(1),
            "PractitionerRole/PractitionerRole1"
        )
        val bundle = agreementServiceUtils.getBundleJSON(
            EagreementServiceImpl.RequestTypeEnum.ASK,
            "1",
            EagreementServiceImpl.MessageEventSystemEnum.MESSAGE_EVENTS,
            "Claim/Claim1",
            "Wathelet",
            "Julien",
            "male",
            "97124587451",
            null,
            null,
            "12341524124",
            "Mennechet",
            "Maxime",
            "484644645489",
            "hospital",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "",
            null
            );
        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(bundle))
    }
}
