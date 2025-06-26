package org.taktik.freehealth.middleware.format.efact.segments

object Record25Description: RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTION_BY_ZONE = LinkedHashMap<String, ZoneDescription>(50)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTION_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTION_BY_ZONE, "1", "EnregistrementDeType25", "recordType", "N", pos, 2, "25")
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "2", "NumeroDordreDeLenregistrement", "recordOrderNumber", "N", pos, 6)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "7", "NumeroMutuelleAffiliation", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "8", "NumeroIdentificationBeneficiaireMediprima", null, "A", pos, 13)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "9", "SexeBeneficiaire", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "12","CodeSexeBeneficiaire", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "14", "NumeroEtablissementQuiFacture", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "15", "EtablissementDeSejour", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "18", "NumeroMutuelleDestination", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "24,25", "NumeroDordreDeLaFactureIndividuelle", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "27", "CodeCouverture", null, "N", pos, 10)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "28", "ReferenceDeLetablissement", "reference", "A", pos, 25)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "32", "FlagIdentificationBeneficiaire", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "38", "BceCpas", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "56,57,58", "NumeroCarte", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "59", "VersionCarte", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "60", "ChiffreControleRecord", null, "N", pos, 2)

    }
}
