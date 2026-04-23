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
import java.util.Date

class EIDItem {
    var deviceType: String? = null
    var readDate: Long? = null
    var readHour: Int = 0
    var readType: String? = null // Zone 9: 1=chip, 2=barcode, 3=datamatrix, 4=manual, A=electronic (itsme)
    var readvalue: String? = null
    var vignetteReason: Int? = 0  // Zone 11: Only when Z10 =7
    var manualEntryReason: Int? = null  // Zone 3: Only when inputType=4. 1-2,7=direct (date/time mandatory), 3-6,8=deferred (date/time forbidden)

    constructor() {
        deviceType = "1"
        readType = "1"
        readDate = Date().time

        var cal = Calendar.getInstance()

        readHour = cal.get(Calendar.HOUR_OF_DAY) * 100 + cal.get(Calendar.MINUTE)
    }

    constructor(readDate: Long?, readHour: Int?, readvalue: String, vignetteReason:Int?) {
        deviceType = "1"
        readType = "1"

        this.readvalue = readvalue
        this.readDate = readDate
        this.readHour = readHour!!
        this.vignetteReason = vignetteReason
    }
}
