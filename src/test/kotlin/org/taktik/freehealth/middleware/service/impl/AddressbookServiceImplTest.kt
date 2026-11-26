package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.aa.complextype.v1.TypeCodeType
import be.fgov.ehealth.addressbook.protocol.v1.HealthCareOrganization
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.mock
import org.taktik.connector.business.ehbox.api.utils.QualityType
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.freehealth.middleware.service.STSService

class AddressbookServiceImplTest {
    private val service = AddressbookServiceImpl(mock(STSService::class.java))

    @Test
    fun `maps treatment center identifier to cbe`() {
        val organization = HealthCareOrganization().apply {
            id = TypeCodeType().apply { value = "0123456789" }
            organizationTypeCodes.add(TypeCodeType().apply {
                type = "code"
                value = "TREATMENT_CENTER"
            })
        }

        val healthcareParty = service.makeHealthcareParty(organization)

        assertThat(healthcareParty.cbe).isEqualTo("0123456789")
        assertThat(healthcareParty.nihii).isNull()
    }

    @Test
    fun `maps organization identifiers according to AddressBook quality`() {
        val expectedNihiiQualities = listOf(
            "AMBU_SERVICE", "END_CAREER", "GROUPOFDOCTORS", "GROUPOFNURSES", "GUARD_POST",
            "HOME_SERVICES", "HOSPITAL", "ICP", "LABO", "LEGAL_PSY", "MEDICAL_HOUSE",
            "OFFICE_DOCTORS", "OTD_PHARMACY", "PALLIATIVE_CARE", "PHARMACY", "PROT_ACC",
            "PSYCH_HOUSE", "REEDUCATION", "RETIREMENT", "SORTING_CENTER"
        )
        val expectedEhpQualities = listOf("EHP", "CTRL_ORGANISM")
        val expectedCbeQualities = listOf("ENTERPRISE", "CONSORTIUM", "TREATMENT_CENTER")

        (expectedNihiiQualities.map { it to "nihii" } +
            expectedEhpQualities.map { it to "ehp" } +
            expectedCbeQualities.map { it to "cbe" }).forEach { (quality, expectedIdentifier) ->
            assertThat(QualityType.forAddressBookOrganization(quality)?.identifierType?.getType(IdentifierType.EHBOX))
                .describedAs("identifier type for %s", quality)
                .isEqualTo(expectedIdentifier.toUpperCase())

            val organization = HealthCareOrganization().apply {
                id = TypeCodeType().apply { value = "0123456789" }
                organizationTypeCodes.add(TypeCodeType().apply {
                    type = "code"
                    value = quality
                })
            }

            val healthcareParty = service.makeHealthcareParty(organization)

            assertThat(healthcareParty.nihii).isEqualTo(if (expectedIdentifier == "nihii") "0123456789" else null)
            assertThat(healthcareParty.ehp).isEqualTo(if (expectedIdentifier == "ehp") "0123456789" else null)
            assertThat(healthcareParty.cbe).isEqualTo(if (expectedIdentifier == "cbe") "0123456789" else null)
        }
    }

    @Test
    fun `keeps eHealthBox group qualities distinct from AddressBook qualities`() {
        assertThat(QualityType.valueOf("GROUP_NIHII").quality).isEqualTo("GROUP")
        assertThat(QualityType.valueOf("GROUP_DOCTORS_NIHII").quality).isEqualTo("GROUP_DOCTORS")
        assertThat(QualityType.forAddressBookOrganization("GROUPOFNURSES")?.quality).isEqualTo("GROUPOFNURSES")
        assertThat(QualityType.forAddressBookOrganization("GROUPOFDOCTORS")?.quality).isEqualTo("GROUPOFDOCTORS")
    }

    @Test
    fun `preserves nihii fallback for unknown organization qualities`() {
        val organization = HealthCareOrganization().apply {
            id = TypeCodeType().apply { value = "0123456789" }
            organizationTypeCodes.add(TypeCodeType().apply {
                type = "code"
                value = "UNKNOWN"
            })
        }

        assertThat(QualityType.forAddressBookOrganization("UNKNOWN")).isNull()
        val healthcareParty = service.makeHealthcareParty(organization)
        assertThat(healthcareParty.nihii).isEqualTo("0123456789")
        assertThat(healthcareParty.cbe).isNull()
        assertThat(healthcareParty.ehp).isNull()
    }
}
