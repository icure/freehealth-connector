package org.taktik.freehealth.middleware.dto.efact

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EIDItemJsonTest {
    private val objectMapper = ObjectMapper().registerKotlinModule()

    @Test
    fun readValueIsDeserialized() {
        assertThat(objectMapper.readValue("""{"readValue":"591234567890"}""", EIDItem::class.java).readValue).isEqualTo("591234567890")
    }

    @Test
    fun legacyReadvalueSpellingIsAccepted() {
        assertThat(objectMapper.readValue("""{"readvalue":"591234567890"}""", EIDItem::class.java).readValue).isEqualTo("591234567890")
    }

    @Test
    fun readValueIsSerializedWithCurrentSpelling() {
        val json = objectMapper.writeValueAsString(EIDItem().apply { readValue = "591234567890" })
        assertThat(json).contains("\"readValue\":\"591234567890\"").doesNotContain("readvalue")
    }
}
