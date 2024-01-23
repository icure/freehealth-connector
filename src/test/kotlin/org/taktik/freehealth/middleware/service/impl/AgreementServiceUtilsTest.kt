package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.schematron.utils.XmlUtils
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.jupiter.api.Assertions.*
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.icure.fhir.entities.r4.practitionerrole.PractitionerRole
import org.taktik.icure.fhir.entities.r4.reference.Reference

class AgreementServiceUtilsTest {

    val agreementServiceUtils = AgreementServiceUtils()
    @Test
    fun getPractitionerRole() {

        val practitionerRole: PractitionerRole = agreementServiceUtils.getPractitionerRole("1", "123", "Mennechet", "Maxime")

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(practitionerRole))

        assertThat(practitionerRole).isNotNull
        assertThat(practitionerRole.id).isEqualTo("PractitionerRole1")

        assertThat(practitionerRole.meta).isNotNull
        assertThat(practitionerRole.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-practitionerrole")

        assertThat(practitionerRole.code).isNotNull
        assertThat(practitionerRole.code).hasSize(1)
    }

    @Test
    fun getPractitioner() {
        val practitionerId = "1"
        val hcpNihii = "123456789"
        val hcpFirstName = "John"
        val hcpLastName = "Doe"

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
        val agreementServiceUtils = AgreementServiceUtils()

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
        val agreementServiceUtils = AgreementServiceUtils()

        // Appeler la fonction que vous souhaitez tester
        val claim = agreementServiceUtils.getClaim(
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
        val agreementServiceUtils = AgreementServiceUtils()

        val supportingInfo1 = agreementServiceUtils.getSupportingInfo(1, "attachment", "functional-report", null, null, "QW5uZXhlIGlubGluZSwgYmFzZTY0ZWQ=", "nom/description de l'annexe", "application/pdf")
        val supportingInfo2 = agreementServiceUtils.getSupportingInfo(2, "info", null, null, "additional Information", null, null, null)
        val supportingInfo3 = agreementServiceUtils.getSupportingInfo(3, "info", null, "ServiceRequest/ServiceRequest2", null, null, null, null)

        println("Result 1: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(supportingInfo1))
        println("Result 2: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(supportingInfo2))
        println("Result 3: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(supportingInfo3))

    }

    @Test
    fun getOrganization() {
        val agreementServiceUtils = AgreementServiceUtils()

        val organization = agreementServiceUtils.getOrganization("1", "71000436000", "orghospital")

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(organization))

        assertThat(organization).isNotNull
        assertThat(organization.id).isEqualTo("Organization1")
        assertThat(organization.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-organization")

    }

    @Test
    fun getPatient() {
        val agreementServiceUtils = AgreementServiceUtils()

        val patientWithSsin = agreementServiceUtils.getPatient("Jean", "Dupont", "male", "73031805784", null, null)
        val patientWithoutSsin = agreementServiceUtils.getPatient("Jean", "Dupont", "male", null, "109", "45613414615SDE")

        println("Result with ssin: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(patientWithSsin))
        println("Result without ssin: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(patientWithoutSsin))

        assertThat(patientWithSsin).isNotNull
        assertThat(patientWithSsin.id).isEqualTo("Patient1")
        assertThat(patientWithSsin.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-patient")

        assertThat(patientWithoutSsin).isNotNull
        assertThat(patientWithoutSsin.id).isEqualTo("Patient1")
        assertThat(patientWithoutSsin.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-patient")
    }

    @Test
    fun getServiceRequest() {
        val agreementServiceUtils = AgreementServiceUtils()

        val serviceRequest = agreementServiceUtils.getServiceRequest(
            "1",
            "data",
            "annexId",
            2.5f,
            "John",
            "Doe",
            "Male",
            "123456789",
            "John",
            "Doe",
            "PatientSsin123",
            "IO123",
            "IOMembership123"
        )

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(serviceRequest))

        assertThat(serviceRequest).isNotNull
        assertThat(serviceRequest.id).isEqualTo("ServiceRequest1")
        assertThat(serviceRequest.meta?.profile).containsExactly("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementservicerequest")
    }

    @Test
    fun getContained() {
        val agreementServiceUtils = AgreementServiceUtils()

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
        val agreementServiceUtils = AgreementServiceUtils()

        val parameterId = "123"
        val parameterNames = arrayOf("resourceType", "patient", "use", "subType", "preAuthPeriod")
        val agreementTypes = "someAgreementType"
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
            parameterNames,
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
        val agreementServiceUtils = AgreementServiceUtils()

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
        val agreementServiceUtils = AgreementServiceUtils()

        val insuranceRef = "InsuranceRef123"
        val display = "Insurance Display"

        val insuranceList = agreementServiceUtils.getInsurance(insuranceRef, display)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(agreementServiceUtils))

        assertThat(insuranceList).isNotNull
        assertThat(insuranceList).hasSize(1)

        val insurance = insuranceList[0]
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
        assertThat(period.start).isEqualTo(startDate.toString())

    }

    @Test
    fun getServicedDateItem() {
        val pathologyDate = DateTime.now()
        val pathologyCode = "somePathologyCode"
        val claimItem = agreementServiceUtils.getServicedDateItem(pathologyDate, pathologyCode)

        println("Result: "+ObjectMapper().registerModule(KotlinModule()).writeValueAsString(claimItem))

        assertThat(claimItem).isNotNull
        assertThat(claimItem.productOrService).isNotNull
        assertThat(claimItem.productOrService.coding).hasSize(1)
        assertThat(claimItem.productOrService.coding[0].system).isEqualTo("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/nihdi-physiotherapy-pathologysituationcode")
        assertThat(claimItem.productOrService.coding[0].code).isEqualTo(pathologyCode)
        assertThat(claimItem.servicedDate).isEqualTo(pathologyDate.toString())
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

    }

    @Test
    fun getBundle() {

    }
}
