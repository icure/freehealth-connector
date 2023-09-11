package org.taktik.icure.fhir.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.boot.jackson.JsonObjectDeserializer
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.Resource

class FhirQuantityDeserializer : JsonObjectDeserializer<Quantity>() {
    override fun deserializeObject(jsonParser: JsonParser,
        context: DeserializationContext,
        codec: ObjectCodec,
        tree: JsonNode): Quantity {
        return when(tree["unit"]?.asText()?.toLowerCase()) {
            "year" -> codec.treeToValue(tree, org.taktik.icure.fhir.entities.r4.age.Age::class.java)
            else -> codec.treeToValue(tree, org.taktik.icure.fhir.entities.r4.count.Count::class.java)
        }
    }
}
