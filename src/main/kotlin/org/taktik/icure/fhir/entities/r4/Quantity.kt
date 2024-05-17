//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.taktik.icure.fhir.deserializer.FhirQuantityDeserializer
import org.taktik.icure.fhir.deserializer.FhirResourceDeserializer
import kotlin.Float
import kotlin.String

/**
 * A measured or measurable amount
 *
 * A measured amount (or an amount that can potentially be measured). Note that measured amounts
 * include amounts that are not precisely quantified, including amounts involving arbitrary units and
 * floating currencies.
 */
@JsonDeserialize(using = FhirQuantityDeserializer::class)
interface Quantity : Element {
  /**
   * Coded form of the unit
   */
  var code: String?

  /**
   * < | <= | >= | > - how to understand the value
   */
  var comparator: String?

  /**
   * System that defines coded unit form
   */
  var system: String?

  /**
   * Unit representation
   */
  var unit: String?

  /**
   * Numerical value (with implicit precision)
   */
  var value: Float?
}
