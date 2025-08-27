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

package org.taktik.freehealth.middleware.dto.efact.segments

import org.apache.commons.lang3.StringUtils
import java.lang.IllegalArgumentException

class ZoneDescription(var label: String? = null,
                                          var property: String? = null,
                                          var position: Int? = null,
                                          var length: Int? = null,
                                          var type: ZoneType? = null,
                                          var zones: List<String>? = null,
                                          var value: String? = null,
                                          var cs: Boolean = false) {
    enum class ZoneType constructor(val symbol: String) {
        ALPHANUMERICAL("A"),
        NUMERICAL("N");
    }
}
