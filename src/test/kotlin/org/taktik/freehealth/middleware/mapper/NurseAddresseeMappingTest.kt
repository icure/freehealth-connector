package org.taktik.freehealth.middleware.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import org.taktik.connector.business.ehbox.api.domain.Addressee
import org.taktik.connector.business.ehbox.api.utils.QualityType
import org.taktik.connector.technical.utils.IdentifierType

class NurseAddresseeMappingTest {
    @Test
    fun nurseQualityIsPreservedAcrossAddresseeMapping() {
        val source = Addressee("12345678", QualityType.NURSE_NIHII)

        val dto = source.toAddresseeDto()

        assertEquals("NURSE", dto.quality)
        assertEquals("NIHII", dto.identifierType?.type)

        val restored = dto.toAddressee()

        assertEquals("12345678", restored.id)
        assertEquals("NURSE", restored.quality)
        assertEquals(IdentifierType.NIHII, restored.identifierTypeHelper)
    }
}