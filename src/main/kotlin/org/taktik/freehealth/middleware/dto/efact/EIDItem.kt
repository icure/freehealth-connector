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

package org.taktik.freehealth.middleware.dto.efact

import java.util.Calendar

class EIDItem {
    var deviceType: String = "1" // Zone 10: 0=none (only when Z3=7), 1=belgian eID/Kids-ID, 2=foreigner card, 4=ISI+, 7=vignette, 8=insured certificate, 9=loss/theft certificate, A=electronic (itsme)
    var readDate: Long? = null
    var readHour: Int = 0
    var readType: String = "1" // Zone 9: 1=chip, 2=barcode, 3=datamatrix, 4=manual, A=electronic (itsme)
    var readValue: String? = null
    var vignetteReason: Int = 0  // Zone 11: Only when Z10 =7
    var manualEntryReason: Int? = null  // Zone 3: Only when readType=4 (manual entry). 1-2,7=direct (date/time mandatory), 3-6,8=deferred (date/time forbidden)
    var justificationDocumentNumber : Int =0  // Zone 17 : Always present,

    /**
     * Default constructor initializes readDate and readHour to current time.
     * Note: for deferred manual entry cases (readType=4, manualEntryReason in 3-6,8),
     * readDate must be set to null and readHour to 0 before writing.
     */
    constructor() {
        readDate = System.currentTimeMillis()

        val cal = Calendar.getInstance()

        readHour = cal.get(Calendar.HOUR_OF_DAY) * 100 + cal.get(Calendar.MINUTE)
    }

    constructor(readDate: Long?, readHour: Int, readValue: String, vignetteReason: Int, justificationDocumentNumber: Int) {
        this.readValue = readValue
        this.readDate = readDate
        this.readHour = readHour
        this.vignetteReason = vignetteReason
        this.justificationDocumentNumber = justificationDocumentNumber
    }

    @Deprecated("Use constructor with non-nullable readHour and vignetteReason instead", ReplaceWith("EIDItem(readDate, readHour, readValue, vignetteReason)"))
    constructor(readDate: Long?, readHour: Int, readValue: String) : this(readDate, readHour, readValue, 0, 0)

    @Deprecated("Use constructor with non-nullable readHour and vignetteReason instead", ReplaceWith("EIDItem(readDate, readHour ?: 0, readValue, vignetteReason ?: 0)"))
    constructor(readDate: Long?, readHour: Int?, readValue: String, vignetteReason: Int?) : this(readDate, readHour ?: 0, readValue, vignetteReason ?: 0, 0)

    @Deprecated("Use constructor with non-nullable readHour instead", ReplaceWith("EIDItem(readDate, readHour ?: 0, readValue, 0)"))
    constructor(readDate: Long?, readHour: Int?, readValue: String) : this(readDate, readHour ?: 0, readValue, 0, 0)

    companion object {
        const val READ_TYPE_CHIP = "1"
        const val READ_TYPE_BARCODE = "2"
        const val READ_TYPE_DATAMATRIX = "3"
        const val READ_TYPE_MANUAL = "4"
        const val READ_TYPE_ELECTRONIC = "A"

        // Zone 10: type de support du document d'identite
        const val DEVICE_TYPE_NONE = "0"  // Pas de document d'identite (AR 18/09/2015 art. 3), only when Z3 = 7
        const val DEVICE_TYPE_EID = "1"  // Carte d'identite electronique belge (ou Kids-id)
        const val DEVICE_TYPE_FOREIGNER_CARD = "2"  // Carte d'identite electronique etranger ou document de sejour electronique
        const val DEVICE_TYPE_ISI_PLUS = "4"
        const val DEVICE_TYPE_VIGNETTE = "7"  // Reason mandatory in Z11
        const val DEVICE_TYPE_INSURED_CERTIFICATE = "8"  // Attestation d'assure social
        const val DEVICE_TYPE_LOSS_OR_THEFT_CERTIFICATE = "9"  // Attestation de perte ou de vol (eID, carte etranger, document de sejour)
        const val DEVICE_TYPE_ELECTRONIC = "A"  // Saisie electronique (itsme)

        val DEFERRED_REASONS = setOf(3, 4, 5, 6, 8)
        val VALID_READ_TYPES = setOf(READ_TYPE_CHIP, READ_TYPE_BARCODE, READ_TYPE_DATAMATRIX, READ_TYPE_MANUAL, READ_TYPE_ELECTRONIC)
        val VALID_DEVICE_TYPES = setOf(DEVICE_TYPE_NONE, DEVICE_TYPE_EID, DEVICE_TYPE_FOREIGNER_CARD, DEVICE_TYPE_ISI_PLUS, DEVICE_TYPE_VIGNETTE, DEVICE_TYPE_INSURED_CERTIFICATE, DEVICE_TYPE_LOSS_OR_THEFT_CERTIFICATE, DEVICE_TYPE_ELECTRONIC)
        const val MANUAL_ENTRY_REASON_NO_ID_DOCUMENT = 7
        val MANUAL_ENTRY_REASON_RANGE = 1..8
        val VIGNETTE_REASON_RANGE = 0..9

        /**
         * Validates that readHour is a valid HHMM time (HH in 0-23, MM in 0-59).
         */
        fun isValidReadHour(readHour: Int): Boolean {
            val hh = readHour / 100
            val mm = readHour % 100
            return hh in 0..23 && mm in 0..59
        }
    }
}
