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

package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Record52Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(19)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "Enregistrement de type 52", "recordType", "N", pos, 2, "52")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "Numero d'ordre de l'enregistrement", "recordOrderNumber", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "Raison saisie manuelle", "manualEntryReason", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "Code nomenclature", "nomenCode", "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "Date de prestation", "prestationDate", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a,6b", "Date de lecture document identite electronique (1 et 2)", "readDate", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "Numero NISS du patient sauf en cas de convention internationale ou nouveaux-nes (1 et 2)", "patientINSS", "N", pos, 13)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "Type de saisie document identite electronique", "readType", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "Type de support document identite electronique", "deviceType", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "Raison utilisation vignette", "vignetteReason", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12,13", "Heure de lecture document identite electronique (1 et 2)", "readHour", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "Numero INAMI", "nihii", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "Valeur lue document identite electronique", "readvalue", "A", pos, 269)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", null, "N", pos, 2, null, true)
    }
}
