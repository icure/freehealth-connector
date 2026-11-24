package org.taktik.freehealth.middleware.service.impl

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

/**
 * The expiration date computed here is used both in the Kmehr and in the Recip-e request: the two
 * must be identical or the prescription is rejected as badly formed (ERR200019).
 */
class RecipeExpirationDateTest {
    private val now = LocalDateTime.of(2026, 11, 23, 10, 17, 26)

    @Test
    fun requestedDateWithinOneYearIsKept() {
        val requested = LocalDateTime.of(2027, 2, 22, 0, 0)
        assertEquals(requested, effectiveExpirationDate(requested, null, now))
    }

    @Test
    fun requestedDateExactlyOneYearAheadIsCappedToOneYearMinusOneDay() {
        val requested = LocalDateTime.of(2027, 11, 23, 0, 0)
        assertEquals(LocalDateTime.of(2027, 11, 22, 10, 17, 26), effectiveExpirationDate(requested, now, now))
    }

    @Test
    fun missingDateDefaultsToThreeMonthsMinusOneDayAfterDelivery() {
        val delivery = LocalDateTime.of(2026, 12, 1, 0, 0)
        assertEquals(LocalDateTime.of(2027, 2, 28, 0, 0), effectiveExpirationDate(null, delivery, now))
    }

    @Test
    fun missingDatesDefaultToThreeMonthsMinusOneDayFromNow() {
        assertEquals(LocalDateTime.of(2027, 2, 22, 10, 17, 26), effectiveExpirationDate(null, null, now))
    }
}
