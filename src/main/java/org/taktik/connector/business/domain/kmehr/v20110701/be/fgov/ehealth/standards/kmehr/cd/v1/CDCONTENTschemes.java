//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:47:59 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20110701.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-CONTENTschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-CONTENTschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-LAB"/>
 *     &lt;enumeration value="CD-TECHNICAL"/>
 *     &lt;enumeration value="CD-HCPARTY"/>
 *     &lt;enumeration value="CD-DRUG-CNK"/>
 *     &lt;enumeration value="CD-ENCOUNTER"/>
 *     &lt;enumeration value="CD-MKG-ADMISSION"/>
 *     &lt;enumeration value="CD-MKG-DISCHARGE"/>
 *     &lt;enumeration value="CD-MKG-ORIGIN"/>
 *     &lt;enumeration value="CD-MKG-REFERRER"/>
 *     &lt;enumeration value="CD-MKG-DESTINATION"/>
 *     &lt;enumeration value="CD-LEGAL-SERVICE"/>
 *     &lt;enumeration value="CD-CONTACT-PERSON"/>
 *     &lt;enumeration value="CD-ENCOUNTER-TYPE"/>
 *     &lt;enumeration value="CD-CARENET-ACCIDENT-TYPE"/>
 *     &lt;enumeration value="CD-CARENET-AGREEMENT-TYPE"/>
 *     &lt;enumeration value="CD-CARENET-SISCARD-ERROR"/>
 *     &lt;enumeration value="CD-CARENET-SOCIAL-CODE"/>
 *     &lt;enumeration value="CD-CARENET-REFUSAL-REASON"/>
 *     &lt;enumeration value="CD-CARENET-NATIONAL-INSURANCE"/>
 *     &lt;enumeration value="CD-CARENET-MESSAGE"/>
 *     &lt;enumeration value="CD-CARENET-MUTATION-INDICATOR"/>
 *     &lt;enumeration value="CD-CARENET-DOCUMENT"/>
 *     &lt;enumeration value="CD-CARENET-INSURANCY-STATUS"/>
 *     &lt;enumeration value="CD-CARENET-TYPE"/>
 *     &lt;enumeration value="CD-MAA-TYPE"/>
 *     &lt;enumeration value="CD-MAA-REQUESTTYPE"/>
 *     &lt;enumeration value="CD-MAA-RESPONSETYPE"/>
 *     &lt;enumeration value="CD-MAA-REFUSALJUSTIFICATION"/>
 *     &lt;enumeration value="CD-MAA-COVERAGE-TYPE"/>
 *     &lt;enumeration value="CD-SITE"/>
 *     &lt;enumeration value="CD-INCAPACITY"/>
 *     &lt;enumeration value="CD-ENCOUNTERSAFETYISSUE"/>
 *     &lt;enumeration value="CD-EMERGENCYEVALUATION"/>
 *     &lt;enumeration value="ICD"/>
 *     &lt;enumeration value="ICPC"/>
 *     &lt;enumeration value="CD-CLINICAL"/>
 *     &lt;enumeration value="LOCAL"/>
 *     &lt;enumeration value="CD-ECARE-HAQVALUE"/>
 *     &lt;enumeration value="CD-EBIRTH-PREGNANCYORIGIN"/>
 *     &lt;enumeration value="CD-EBIRTH-SPECIALVALUES"/>
 *     &lt;enumeration value="CD-EBIRTH-CHILDPOSITION"/>
 *     &lt;enumeration value="CD-EBIRTH-FOETALMONITORING"/>
 *     &lt;enumeration value="CD-EBIRTH-DELIVERYWAY"/>
 *     &lt;enumeration value="CD-EBIRTH-CAESEREANINDICATION"/>
 *     &lt;enumeration value="CD-EBIRTH-ARTIFICIALRESPIRATIONTYPE"/>
 *     &lt;enumeration value="CD-EBIRTH-NEONATALDEPTTYPE"/>
 *     &lt;enumeration value="CD-EBIRTH-CONGENITALMALFORMATION"/>
 *     &lt;enumeration value="CD-VACCINEINDICATION"/>
 *     &lt;enumeration value="CD-ATC"/>
 *     &lt;enumeration value="CD-CAREPATH"/>
 *     &lt;enumeration value="CD-CLINICALPLAN"/>
 *     &lt;enumeration value="CD-PATIENTWILL"/>
 *     &lt;enumeration value="CD-CONSENT"/>
 *     &lt;enumeration value="CD-CHAPTER4PARAGRAPH"/>
 *     &lt;enumeration value="CD-CHAPTER4CATEGORY"/>
 *     &lt;enumeration value="CD-CHAPTER4CRITERIA"/>
 *     &lt;enumeration value="CD-CHAPTER4VERSESEQAPPENDIX"/>
 *     &lt;enumeration value="CD-CHAPTER4DOCUMENTSEQAPPENDIX"/>
 *     &lt;enumeration value="CD-CHAPTER4VERSE"/>
 *     &lt;enumeration value="CD-BVT-CONSERVATIONMODE"/>
 *     &lt;enumeration value="CD-BVT-CONSERVATIONDELAY"/>
 *     &lt;enumeration value="CD-BVT-AVAILABLEMATERIALS"/>
 *     &lt;enumeration value="CD-BCR-DIFFERENTATIONDEGREE"/>
 *     &lt;enumeration value="CD-BVT-SAMPLETYPE"/>
 *     &lt;enumeration value="CD-BVT-LATERALITY"/>
 *     &lt;enumeration value="CD-BVT-PATIENTOPPOSITION"/>
 *     &lt;enumeration value="CD-BVT-STATUS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-CONTENTschemes")
@XmlEnum
public enum CDCONTENTschemes {

    @XmlEnumValue("CD-LAB")
    CD_LAB("CD-LAB", "1.1"),
    @XmlEnumValue("CD-TECHNICAL")
    CD_TECHNICAL("CD-TECHNICAL", "1.0"),
    @XmlEnumValue("CD-HCPARTY")
    CD_HCPARTY("CD-HCPARTY", "1.1"),
    @XmlEnumValue("CD-DRUG-CNK")
    CD_DRUG_CNK("CD-DRUG-CNK", "1.0"),
    @XmlEnumValue("CD-ENCOUNTER")
    CD_ENCOUNTER("CD-ENCOUNTER", "1.0"),
    @XmlEnumValue("CD-MKG-ADMISSION")
    CD_MKG_ADMISSION("CD-MKG-ADMISSION", "1.0"),
    @XmlEnumValue("CD-MKG-DISCHARGE")
    CD_MKG_DISCHARGE("CD-MKG-DISCHARGE", "1.0"),
    @XmlEnumValue("CD-MKG-ORIGIN")
    CD_MKG_ORIGIN("CD-MKG-ORIGIN", "1.0"),
    @XmlEnumValue("CD-MKG-REFERRER")
    CD_MKG_REFERRER("CD-MKG-REFERRER", "1.0"),
    @XmlEnumValue("CD-MKG-DESTINATION")
    CD_MKG_DESTINATION("CD-MKG-DESTINATION", "1.0"),
    @XmlEnumValue("CD-LEGAL-SERVICE")
    CD_LEGAL_SERVICE("CD-LEGAL-SERVICE", "1.0"),
    @XmlEnumValue("CD-CONTACT-PERSON")
    CD_CONTACT_PERSON("CD-CONTACT-PERSON", "1.1"),
    @XmlEnumValue("CD-ENCOUNTER-TYPE")
    CD_ENCOUNTER_TYPE("CD-ENCOUNTER-TYPE", "1.0"),
    @XmlEnumValue("CD-CARENET-ACCIDENT-TYPE")
    CD_CARENET_ACCIDENT_TYPE("CD-CARENET-ACCIDENT-TYPE", "1.0"),
    @XmlEnumValue("CD-CARENET-AGREEMENT-TYPE")
    CD_CARENET_AGREEMENT_TYPE("CD-CARENET-AGREEMENT-TYPE", "1.0"),
    @XmlEnumValue("CD-CARENET-SISCARD-ERROR")
    CD_CARENET_SISCARD_ERROR("CD-CARENET-SISCARD-ERROR", "1.0"),
    @XmlEnumValue("CD-CARENET-SOCIAL-CODE")
    CD_CARENET_SOCIAL_CODE("CD-CARENET-SOCIAL-CODE", "1.0"),
    @XmlEnumValue("CD-CARENET-REFUSAL-REASON")
    CD_CARENET_REFUSAL_REASON("CD-CARENET-REFUSAL-REASON", "1.0"),
    @XmlEnumValue("CD-CARENET-NATIONAL-INSURANCE")
    CD_CARENET_NATIONAL_INSURANCE("CD-CARENET-NATIONAL-INSURANCE", "1.0"),
    @XmlEnumValue("CD-CARENET-MESSAGE")
    CD_CARENET_MESSAGE("CD-CARENET-MESSAGE", "1.0"),
    @XmlEnumValue("CD-CARENET-MUTATION-INDICATOR")
    CD_CARENET_MUTATION_INDICATOR("CD-CARENET-MUTATION-INDICATOR", "1.0"),
    @XmlEnumValue("CD-CARENET-DOCUMENT")
    CD_CARENET_DOCUMENT("CD-CARENET-DOCUMENT", "1.0"),
    @XmlEnumValue("CD-CARENET-INSURANCY-STATUS")
    CD_CARENET_INSURANCY_STATUS("CD-CARENET-INSURANCY-STATUS", "1.0"),
    @XmlEnumValue("CD-CARENET-TYPE")
    CD_CARENET_TYPE("CD-CARENET-TYPE", "1.0"),
    @XmlEnumValue("CD-MAA-TYPE")
    CD_MAA_TYPE("CD-MAA-TYPE", "1.0"),
    @XmlEnumValue("CD-MAA-REQUESTTYPE")
    CD_MAA_REQUESTTYPE("CD-MAA-REQUESTTYPE", "1.0"),
    @XmlEnumValue("CD-MAA-RESPONSETYPE")
    CD_MAA_RESPONSETYPE("CD-MAA-RESPONSETYPE", "1.0"),
    @XmlEnumValue("CD-MAA-REFUSALJUSTIFICATION")
    CD_MAA_REFUSALJUSTIFICATION("CD-MAA-REFUSALJUSTIFICATION", "1.0"),
    @XmlEnumValue("CD-MAA-COVERAGE-TYPE")
    CD_MAA_COVERAGE_TYPE("CD-MAA-COVERAGE-TYPE", "1.0"),
    @XmlEnumValue("CD-SITE")
    CD_SITE("CD-SITE", "1.0"),
    @XmlEnumValue("CD-INCAPACITY")
    CD_INCAPACITY("CD-INCAPACITY", "1.0"),
    @XmlEnumValue("CD-ENCOUNTERSAFETYISSUE")
    CD_ENCOUNTERSAFETYISSUE("CD-ENCOUNTERSAFETYISSUE", "1.0"),
    @XmlEnumValue("CD-EMERGENCYEVALUATION")
    CD_EMERGENCYEVALUATION("CD-EMERGENCYEVALUATION", "1.0"),
    ICD("ICD", "10"),
    ICPC("ICPC", "2"),
    @XmlEnumValue("CD-CLINICAL")
    CD_CLINICAL("CD-CLINICAL", "1.0"),
    LOCAL("LOCAL", "1.0"),
    @XmlEnumValue("CD-ECARE-HAQVALUE")
    CD_ECARE_HAQVALUE("CD-ECARE-HAQVALUE", "1.0"),
    @XmlEnumValue("CD-EBIRTH-PREGNANCYORIGIN")
    CD_EBIRTH_PREGNANCYORIGIN("CD-EBIRTH-PREGNANCYORIGIN", "1.0"),
    @XmlEnumValue("CD-EBIRTH-SPECIALVALUES")
    CD_EBIRTH_SPECIALVALUES("CD-EBIRTH-SPECIALVALUES", "1.0"),
    @XmlEnumValue("CD-EBIRTH-CHILDPOSITION")
    CD_EBIRTH_CHILDPOSITION("CD-EBIRTH-CHILDPOSITION", "1.0"),
    @XmlEnumValue("CD-EBIRTH-FOETALMONITORING")
    CD_EBIRTH_FOETALMONITORING("CD-EBIRTH-FOETALMONITORING", "1.0"),
    @XmlEnumValue("CD-EBIRTH-DELIVERYWAY")
    CD_EBIRTH_DELIVERYWAY("CD-EBIRTH-DELIVERYWAY", "1.0"),
    @XmlEnumValue("CD-EBIRTH-CAESEREANINDICATION")
    CD_EBIRTH_CAESEREANINDICATION("CD-EBIRTH-CAESEREANINDICATION", "1.0"),
    @XmlEnumValue("CD-EBIRTH-ARTIFICIALRESPIRATIONTYPE")
    CD_EBIRTH_ARTIFICIALRESPIRATIONTYPE("CD-EBIRTH-ARTIFICIALRESPIRATIONTYPE", "1.0"),
    @XmlEnumValue("CD-EBIRTH-NEONATALDEPTTYPE")
    CD_EBIRTH_NEONATALDEPTTYPE("CD-EBIRTH-NEONATALDEPTTYPE", "1.0"),
    @XmlEnumValue("CD-EBIRTH-CONGENITALMALFORMATION")
    CD_EBIRTH_CONGENITALMALFORMATION("CD-EBIRTH-CONGENITALMALFORMATION", "1.0"),
    @XmlEnumValue("CD-VACCINEINDICATION")
    CD_VACCINEINDICATION("CD-VACCINEINDICATION", "1.0"),
    @XmlEnumValue("CD-ATC")
    CD_ATC("CD-ATC", "1.0"),
    @XmlEnumValue("CD-CAREPATH")
    CD_CAREPATH("CD-CAREPATH", "1.0"),
    @XmlEnumValue("CD-CLINICALPLAN")
    CD_CLINICALPLAN("CD-CLINICALPLAN", "1.0"),
    @XmlEnumValue("CD-PATIENTWILL")
    CD_PATIENTWILL("CD-PATIENTWILL", "1.0"),
    @XmlEnumValue("CD-CONSENT")
    CD_CONSENT("CD-CONSENT", "1.0"),
    @XmlEnumValue("CD-CHAPTER4PARAGRAPH")
    CD_CHAPTER_4_PARAGRAPH("CD-CHAPTER4PARAGRAPH", "1.0"),
    @XmlEnumValue("CD-CHAPTER4CATEGORY")
    CD_CHAPTER_4_CATEGORY("CD-CHAPTER4CATEGORY", "1.0"),
    @XmlEnumValue("CD-CHAPTER4CRITERIA")
    CD_CHAPTER_4_CRITERIA("CD-CHAPTER4CRITERIA", "1.0"),
    @XmlEnumValue("CD-CHAPTER4VERSESEQAPPENDIX")
    CD_CHAPTER_4_VERSESEQAPPENDIX("CD-CHAPTER4VERSESEQAPPENDIX", "1.0"),
    @XmlEnumValue("CD-CHAPTER4DOCUMENTSEQAPPENDIX")
    CD_CHAPTER_4_DOCUMENTSEQAPPENDIX("CD-CHAPTER4DOCUMENTSEQAPPENDIX", "1.0"),
    @XmlEnumValue("CD-CHAPTER4VERSE")
    CD_CHAPTER_4_VERSE("CD-CHAPTER4VERSE", "1.0"),
    @XmlEnumValue("CD-BVT-CONSERVATIONMODE")
    CD_BVT_CONSERVATIONMODE("CD-BVT-CONSERVATIONMODE", "1.0"),
    @XmlEnumValue("CD-BVT-CONSERVATIONDELAY")
    CD_BVT_CONSERVATIONDELAY("CD-BVT-CONSERVATIONDELAY", "1.0"),
    @XmlEnumValue("CD-BVT-AVAILABLEMATERIALS")
    CD_BVT_AVAILABLEMATERIALS("CD-BVT-AVAILABLEMATERIALS", "1.0"),
    @XmlEnumValue("CD-BCR-DIFFERENTATIONDEGREE")
    CD_BCR_DIFFERENTATIONDEGREE("CD-BCR-DIFFERENTATIONDEGREE", "1.0"),
    @XmlEnumValue("CD-BVT-SAMPLETYPE")
    CD_BVT_SAMPLETYPE("CD-BVT-SAMPLETYPE", "1.0"),
    @XmlEnumValue("CD-BVT-LATERALITY")
    CD_BVT_LATERALITY("CD-BVT-LATERALITY", "1.0"),
    @XmlEnumValue("CD-BVT-PATIENTOPPOSITION")
    CD_BVT_PATIENTOPPOSITION("CD-BVT-PATIENTOPPOSITION", "1.0"),
    @XmlEnumValue("CD-BVT-STATUS")
    CD_BVT_STATUS("CD-BVT-STATUS", "1.0");
    private final String value; //
    private final String version;
    CDCONTENTschemes(String v, String vs) {
        value = v;
        version = vs;
    }

    public String value() {
        return value;
    } //

    public String version() {
        return version;
    }

    public static CDCONTENTschemes fromValue(String v) {
        for (CDCONTENTschemes c: CDCONTENTschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
