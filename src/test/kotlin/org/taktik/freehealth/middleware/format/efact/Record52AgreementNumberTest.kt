/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.format.efact

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.dto.efact.EIDItem
import org.taktik.freehealth.middleware.dto.efact.InvoiceItem
import org.taktik.freehealth.middleware.dto.efact.InvoiceSender
import org.taktik.freehealth.middleware.format.efact.segments.Record51Description
import org.taktik.freehealth.middleware.format.efact.segments.Record52Description
import java.io.StringWriter
import java.math.BigInteger

/**
 * ET 52 Z 19, the agreement number — offline, no eHealth call.
 *
 * Normative source: INAMI *instructions de facturation electronique*, annexe 6.8 (EDITION 2021), which places
 * `19  20 A  132-151  Numero d'accord` in the type 52 record, and annexe 26.4 (kinesitherapeutes, MISE A JOUR
 * 2021/33, publication 23-06-2026), titled "Enregistrement de type 52 (facultatif, sauf zone 19)", which marks
 * Z 19 "obligation de completer". The zone detail sheet adds: "le numero d'accord, recu via eAgreement, doit etre
 * complete", structure XXXYYYYYYYYYYYYYYYDD, and footnote (4) "a partir du 01/05/22, cette zone doit
 * obligatoirement etre completee par les kinesitherapeutes". Annexe 7 point f lists ET 52 Z 19 among the
 * alphanumerical zones that must be filled with zeroes when unused.
 *
 * The same annexe 26.4 also marks "Z 9 Type de saisie document identite" and "Z 10 Type de support document
 * identite" as "Obligation de completer" with no exception clause — where Z 6a/6b and Z 12/13 carry "sauf lorsque
 * Z 9 = 4 et Z 3 = 3" and Z 16 carries "sauf lorsque Z 10 = 7, 8 ou 9". A physiotherapist therefore cannot send
 * Z 19 without the identity document capture, and the writer refuses that combination.
 */
class Record52AgreementNumberTest {
    private val agreementNumber = "30600000000000000103"

    /** ET 10 Z 18 = 50: physiotherapist. Any other value leaves the sector's own annexe in charge. */
    private fun sender(professionCode: Int? = BelgianInsuranceInvoicingFormatWriter.KINE_PROFESSION_CODE) =
        InvoiceSender().apply {
            nihii = 54123456789L
            bce = 999999922L
            ssin = "12345678901"
            firstName = "Jean"
            lastName = "Kine"
            phoneNumber = 32470000000L
            conventionCode = 0
            this.professionCode = professionCode
        }

    private fun patient() = Patient().apply { ssin = "86103130262"; firstName = "Test"; lastName = "Patient" }

    private fun eidItem() = EIDItem(20260729000000L, 1030, "5910212346", 0, 1)

    /** A 567011 session on 29/07/2026 — the nominal accepted scenario of the CIN physiotherapist test manual. */
    private fun item() = InvoiceItem().apply {
        codeNomenclature = 567011L
        dateCode = 20260729L
        reimbursedAmount = 1000L
    }

    private fun write(icd: InvoiceItem, sender: InvoiceSender = sender()): String {
        val sw = StringWriter()
        val recordNumber = BelgianInsuranceInvoicingFormatWriter(sw).writeEid(3, icd, patient(), sender)
        return sw.toString().also { assertThat(recordNumber).isEqualTo(if (it.isEmpty()) 3 else 4) }
    }

    /** Independent re-implementation of the eFact check digit, so the assertion does not lean on the writer. */
    private fun expectedCheckDigits(recordWithoutCheckDigits: String): String {
        var sum = BigInteger.ZERO
        for (c in recordWithoutCheckDigits) {
            val v = when (c) {
                in '0'..'9' -> (c - '0').toLong()
                ' ' -> 10L
                in 'A'..'Z' -> (c - 'A' + 11).toLong()
                in 'a'..'z' -> (c - 'a' + 11).toLong()
                else -> 37L
            }
            sum = sum.add(BigInteger.valueOf(v))
        }
        val modulo = sum.mod(BigInteger.valueOf(97)).toInt()
        return String.format("%02d", if (modulo == 0) 97 else modulo)
    }

    private fun assertWellFormed(record: String) {
        assertThat(record).hasSize(350)
        assertThat(record.take(2)).isEqualTo("52")
        assertThat(record.takeLast(2)).isEqualTo(expectedCheckDigits(record.dropLast(2)))
    }

    private fun zone19(record: String): String {
        val zd = Record52Description.zoneDescriptionsByZone["19"]!!
        assertThat(zd.position).isEqualTo(132)
        assertThat(zd.length).isEqualTo(20)
        return record.substring(zd.position - 1, zd.position - 1 + zd.length)
    }

    // 1 + 3 + 4 — a physiotherapy 567011 with an agreement number produces a 350 character record 52 with valid
    // check digits. Annexe 26.4 requires the identity document capture in the very same record, hence the eidItem.
    @Test
    fun physiotherapyItemWithAgreementNumberProducesRecord52() {
        val record = write(item().apply {
            eidItem = eidItem()
            agreementNumber = this@Record52AgreementNumberTest.agreementNumber
        })

        assertWellFormed(record)
        assertThat(record.substring(9, 16)).isEqualTo("0567011")   // Z 4, = ET 50 Z 4
        assertThat(record.substring(16, 24)).isEqualTo("20260729") // Z 5, = ET 50 Z 5
    }

    // 2 — ET 52 Z 19 holds exactly the number supplied, at positions 132-151
    @Test
    fun zone19HoldsExactlyTheSuppliedNumber() {
        val record = write(item().apply {
            eidItem = eidItem()
            agreementNumber = this@Record52AgreementNumberTest.agreementNumber
        })

        assertThat(zone19(record)).isEqualTo(agreementNumber)
    }

    // annexe 26.4 makes Z 9 and Z 10 unconditional, so a physiotherapist cannot send Z 19 without an eID capture
    @Test
    fun agreementNumberWithoutEidIsRefusedForAPhysiotherapist() {
        assertThatThrownBy { write(item().apply { agreementNumber = this@Record52AgreementNumberTest.agreementNumber }) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("annexe 26.4")
            .hasMessageContaining("Z 9")
            .hasMessageContaining("Z 10")
    }

    // ... and no other sector is affected: the refusal is keyed on ET 10 Z 18 alone
    @Test
    fun agreementNumberWithoutEidStillWritesForOtherProfessions() {
        listOf(null, 30).forEach { professionCode ->
            val record = write(
                item().apply { agreementNumber = this@Record52AgreementNumberTest.agreementNumber },
                sender(professionCode))

            assertWellFormed(record)
            assertThat(zone19(record)).isEqualTo(agreementNumber)
        }
    }

    // 5 — with no agreement number and no eID, nothing at all is written, as before
    @Test
    fun withoutAgreementNumberAndWithoutEidNothingIsWritten() {
        assertThat(write(item())).isEmpty()
    }

    // 5 + 7 — an eID item alone still produces the exact record it produced before ET 52 Z 19 existed
    @Test
    fun eidAloneIsByteForByteUnchanged() {
        val record = write(item().apply { eidItem = eidItem() })

        assertWellFormed(record)
        assertThat(record).isEqualTo(
            "52" + "000003" + "0" + "0567011" + "20260729" + "20260729" + "000" + "0086103130262" +
                "1" + "1" + "0" + "1030" + "000000000000" + "054123456789" + "5910212346     " +
                "0000000000000000000000001" + "0".repeat(229) + "68"
        )
        // the zone that did not exist before is where the reserve zeroes used to be
        assertThat(zone19(record)).isEqualTo(Record52Description.EMPTY_AGREEMENT_NUMBER)
    }

    // 8 — eID data and agreement number coexist in a single conformant record 52
    @Test
    fun eidAndAgreementNumberCoexistInOneRecord() {
        val record = write(item().apply {
            eidItem = eidItem()
            agreementNumber = this@Record52AgreementNumberTest.agreementNumber
        })

        assertWellFormed(record)
        assertThat(zone19(record)).isEqualTo(agreementNumber)
        // every eID zone is untouched: only positions 132-151 differ from the eID-only record
        val eidOnly = write(item().apply { eidItem = eidItem() })
        assertThat(record.take(131)).isEqualTo(eidOnly.take(131))
        assertThat(record.substring(151, 348)).isEqualTo(eidOnly.substring(151, 348))
    }

    // 6 — insuranceRef keeps feeding ET 51 Z 42 and never reaches ET 52 Z 19
    @Test
    fun insuranceRefStillGoesToRecord51Zone42() {
        val sw = StringWriter()
        val icd = item().apply {
            insuranceRef = "1234567890"
            insuranceRefDate = 20260729L
            doctorIdentificationNumber = "54123456789"
        }
        BelgianInsuranceInvoicingFormatWriter(sw)
            .writeInvolvementRecordContent(3, sender(), 2026, 7, patient(), false, icd)
        val record = sw.toString()

        assertThat(record).hasSize(350)
        assertThat(record.take(2)).isEqualTo("51")
        val zd = Record51Description.zoneDescriptionsByZone["42"]!!
        assertThat(record.substring(zd.position - 1, zd.position - 1 + 10)).isEqualTo("1234567890")

        // and the same item, written as a record 52, leaves Z 19 empty
        assertThat(zone19(write(icd.apply { eidItem = eidItem() }))).isEqualTo(Record52Description.EMPTY_AGREEMENT_NUMBER)
    }

    /**
     * Nothing at all reaches the Writer when a validation fails, so a refused record cannot corrupt the flat file.
     * This is the invariant PR #100 was reviewed against ("move the requires before the first ws.write"): the
     * agreement number zones are now computed before the eID requires, which is safe only because WriterSession
     * buffers every write into a map and emits on writeFieldsWithCheckSum() alone. Asserted rather than assumed.
     */
    @Test
    fun aRefusedRecordEmitsNothingAtAll() {
        val refused = listOf<InvoiceItem.() -> Unit>(
            { agreementNumber = "306" },                                           // malformed Z 19
            { agreementNumber = this@Record52AgreementNumberTest.agreementNumber }, // Z 19 without eID, kine
            { eidItem = eidItem().apply { readType = "Z" } },                       // invalid Z 9
            { eidItem = eidItem().apply { deviceType = "Z" } }                      // invalid Z 10
        )

        refused.forEach { spoil ->
            val sw = StringWriter()
            val writer = BelgianInsuranceInvoicingFormatWriter(sw)
            val icd = item().apply(spoil)

            assertThatThrownBy { writer.writeEid(3, icd, patient(), sender()) }
                .isInstanceOf(IllegalArgumentException::class.java)
            assertThat(sw.toString()).isEmpty()
        }
    }

    // the layout stays a valid 350 position paving
    @Test
    fun record52LayoutPavesExactly350Positions() {
        val zones = Record52Description.zoneDescriptions
        var expected = 1
        zones.forEach {
            assertThat(it.position).`as`("zone ${it.zone} starts at ${it.position}").isEqualTo(expected)
            expected += it.length
        }
        assertThat(expected - 1).isEqualTo(350)
    }

    @Test
    fun malformedAgreementNumbersAreRefusedRatherThanSilentlyPadded() {
        listOf("306", "3060000000000000010A", "30600000000000000103 ").forEach { malformed ->
            assertThatThrownBy { write(item().apply { eidItem = eidItem(); agreementNumber = malformed }) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageContaining("ET 52 Z 19")
                .hasMessageContaining("expected exactly 20 digits")
        }
    }

    // the number reaches a 400 response body and the logs through the exception message: it must not be in there
    @Test
    fun theRefusalMessageNeverEchoesTheAgreementNumber() {
        listOf("306", "3060000000000000010A", "30600000000000000103 ", agreementNumber.dropLast(1)).forEach { value ->
            assertThatThrownBy { write(item().apply { eidItem = eidItem(); agreementNumber = value }) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .matches({ it.message?.contains(value.trim()) == false }, "message must not echo the value")
        }
    }
}
