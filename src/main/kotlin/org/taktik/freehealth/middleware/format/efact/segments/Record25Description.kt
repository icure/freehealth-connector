package org.taktik.freehealth.middleware.format.efact.segments



object Record25Description: RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTION_BY_ZONE = LinkedHashMap<String, ZoneDescription>(50)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTION_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTION_BY_ZONE, "1", "EnregistrementDeType25", "recordType", "N", pos, 2, "25")
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "2", "NumeroDordreDeLenregistrement", "recordOrderNumber", "N", pos, 6)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "3", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "4", "Reserve", null, "N", pos, 7)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "5", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "6a,6b", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "7", "NumeroMutuelleAffiliation", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "8", "NumeroIdentificationBeneficiaireMediprima", null, "N", pos, 13)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "9", "SexeBeneficiaire", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "10", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "11", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "12","CodeAideMedicalUrgente", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "13", "Reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "14", "NumeroEtablissementQuiFacture", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "15", "EtablissementDeSejour", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "16", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "17", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "18", "NumeroMutuelleDestination", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "19", "Reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "20,21", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "22", "Reserve", null, "N", pos, 5)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "23", "Reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "24,25", "NumeroDordreDeLaFactureIndividuelle", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "26", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "27", "CodeCouverture", null, "N", pos, 10)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "28", "ReferenceDeLetablissement", "reference", "A", pos, 25)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "29,30,31", "Reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "32", "FlagIdentificationBeneficiaire", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "33", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "34,35,36", "Reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "37", "Reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "38", "BceCpas", null, "A", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "39", "Reserve", null, "N", pos, 10)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "40", "Reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "41", "Reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "42,43,44,45", "Reserve", null, "A", pos, 48)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "46", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "47,48,49", "ReferenceAMUElectronique", null, "A", pos, 21)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "50", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "51", "Reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "52", "Reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "53", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "54a,54b,54c", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "55", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "56,57,58", "NumeroCarte", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "59", "VersionCarte", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTION_BY_ZONE, "98", "Reserve", null, "N", pos, 2)
              register(ZONE_DESCRIPTION_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", null, "N", pos, 2, null, true)
    }
}
