package org.taktik.freehealth.middleware.mapper

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.taktik.freehealth.middleware.MapperConfiguration
import org.taktik.freehealth.middleware.dto.common.AuthorDto
import org.taktik.freehealth.middleware.dto.common.HcPartyDto
import org.taktik.freehealth.middleware.dto.common.KmehrCd
import org.taktik.freehealth.middleware.dto.common.KmehrId
import org.taktik.freehealth.middleware.dto.hub.TransactionSummaryDto

/**
 * Nested KMEHR (JAXB) enums must be mapped with their XML value (ID-HCPARTY, CD-HCPARTY), as the Orika based
 * mapper used to do, and not with the Java constant name (ID_HCPARTY, CD_HCPARTY).
 */
class MapperFacadeJaxbEnumTest {
    private val objectMapper = ObjectMapper().registerKotlinModule()
    private val mapper = MapperConfiguration().mapper(objectMapper)

    private fun author() = AuthorType().apply {
        hcparties.add(HcpartyType().apply {
            ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = "10000000001" })
            cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.1"; value = "persphysician" })
            firstname = "Jane"
            familyname = "Doe"
        })
    }

    @Test
    fun nestedJaxbEnumsAreMappedToTheirXmlValue() {
        val hcp = mapper.map(author(), AuthorDto::class.java).hcparties.single()

        assertThat(hcp.ids.single().s).isEqualTo("ID-HCPARTY")
        assertThat(hcp.ids.single().value).isEqualTo("10000000001")
        assertThat(hcp.cds.single().s).isEqualTo("CD-HCPARTY")
        assertThat(hcp.cds.single().value).isEqualTo("persphysician")
        assertThat(hcp.firstname).isEqualTo("Jane")
    }

    @Test
    fun transactionSummaryJsonKeepsXmlValues() {
        val json = objectMapper.writeValueAsString(TransactionSummaryDto().apply { author = mapper.map(author(), AuthorDto::class.java) })

        assertThat(json).contains("\"s\":\"ID-HCPARTY\"").contains("\"s\":\"CD-HCPARTY\"")
        assertThat(json).doesNotContain("ID_HCPARTY").doesNotContain("CD_HCPARTY")
    }

    private fun authorDto(idScheme: String, cdScheme: String) = AuthorDto().apply {
        hcparties = listOf(HcPartyDto().apply {
            ids.add(KmehrId().apply { s = idScheme; sv = "1.0"; value = "10000000001" })
            cds.add(KmehrCd().apply { s = cdScheme; sv = "1.1"; value = "persphysician" })
        })
    }

    @Test
    fun xmlValuesAreMappedBackToJaxbEnums() {
        val hcp = mapper.map(authorDto("ID-HCPARTY", "CD-HCPARTY"), AuthorType::class.java).hcparties.single()

        assertThat(hcp.ids.single().s).isEqualTo(IDHCPARTYschemes.ID_HCPARTY)
        assertThat(hcp.cds.single().s).isEqualTo(CDHCPARTYschemes.CD_HCPARTY)
    }

    @Test
    fun constantNamesAreStillAcceptedWhenMappingBack() {
        val hcp = mapper.map(authorDto("ID_HCPARTY", "CD_HCPARTY"), AuthorType::class.java).hcparties.single()

        assertThat(hcp.ids.single().s).isEqualTo(IDHCPARTYschemes.ID_HCPARTY)
        assertThat(hcp.cds.single().s).isEqualTo(CDHCPARTYschemes.CD_HCPARTY)
    }

    enum class Scheme { ID_HCPARTY, INSS }
    class SchemeHolder { var s: Scheme? = null }
    class JaxbSchemeHolder { var s: IDHCPARTYschemes? = null }

    @Test
    fun jaxbEnumsCanBeMappedToPlainEnumsWithTheSameConstantNames() {
        val holder = mapper.map(JaxbSchemeHolder().apply { s = IDHCPARTYschemes.ID_HCPARTY }, SchemeHolder::class.java)

        assertThat(holder.s).isEqualTo(Scheme.ID_HCPARTY)
    }
}
