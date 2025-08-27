//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-ITEMschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ITEMschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-ITEM"/>
 *     &lt;enumeration value="CD-ITEM-MAA"/>
 *     &lt;enumeration value="CD-ITEM-CARENET"/>
 *     &lt;enumeration value="CD-LAB"/>
 *     &lt;enumeration value="CD-TECHNICAL"/>
 *     &lt;enumeration value="CD-CONTACT-PERSON"/>
 *     &lt;enumeration value="ICD"/>
 *     &lt;enumeration value="ICPC"/>
 *     &lt;enumeration value="LOCAL"/>
 *     &lt;enumeration value="CD-VACCINE"/>
 *     &lt;enumeration value="CD-ECG"/>
 *     &lt;enumeration value="CD-ECARE-CLINICAL"/>
 *     &lt;enumeration value="CD-ECARE-LAB"/>
 *     &lt;enumeration value="CD-ECARE-HAQ"/>
 *     &lt;enumeration value="CD-ITEM-EBIRTH"/>
 *     &lt;enumeration value="CD-PARAMETER"/>
 *     &lt;enumeration value="CD-ITEM-BVT"/>
 *     &lt;enumeration value="CD-BVT-AVAILABLEMATERIALS"/>
 *     &lt;enumeration value="CD-BVT-CONSERVATIONDELAY"/>
 *     &lt;enumeration value="CD-BVT-CONSERVATIONMODE"/>
 *     &lt;enumeration value="CD-BVT-SAMPLETYPE"/>
 *     &lt;enumeration value="CD-BCR-DIFFERENTATIONDEGREE"/>
 *     &lt;enumeration value="CD-BVT-LATERALITY"/>
 *     &lt;enumeration value="CD-BVT-PATIENTOPPOSITION"/>
 *     &lt;enumeration value="CD-BVT-STATUS"/>
 *     &lt;enumeration value="CD-ITEM-REG"/>
 *     &lt;enumeration value="CD-ITEM-MYCARENET"/>
 *     &lt;enumeration value="CD-DEFIB-DIAGNOSIS"/>
 *     &lt;enumeration value="CD-ACTS-NURSING"/>
 *     &lt;enumeration value="CD-QERMID-INTERVENTIONTYPE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ITEMschemes")
@XmlEnum
public enum CDITEMschemes {

    @XmlEnumValue("CD-ITEM")
    CD_ITEM("CD-ITEM"),
    @XmlEnumValue("CD-ITEM-MAA")
    CD_ITEM_MAA("CD-ITEM-MAA"),
    @XmlEnumValue("CD-ITEM-CARENET")
    CD_ITEM_CARENET("CD-ITEM-CARENET"),
    @XmlEnumValue("CD-LAB")
    CD_LAB("CD-LAB"),
    @XmlEnumValue("CD-TECHNICAL")
    CD_TECHNICAL("CD-TECHNICAL"),
    @XmlEnumValue("CD-CONTACT-PERSON")
    CD_CONTACT_PERSON("CD-CONTACT-PERSON"),
    ICD("ICD"),
    ICPC("ICPC"),
    LOCAL("LOCAL"),
    @XmlEnumValue("CD-VACCINE")
    CD_VACCINE("CD-VACCINE"),
    @XmlEnumValue("CD-ECG")
    CD_ECG("CD-ECG"),
    @XmlEnumValue("CD-ECARE-CLINICAL")
    CD_ECARE_CLINICAL("CD-ECARE-CLINICAL"),
    @XmlEnumValue("CD-ECARE-LAB")
    CD_ECARE_LAB("CD-ECARE-LAB"),
    @XmlEnumValue("CD-ECARE-HAQ")
    CD_ECARE_HAQ("CD-ECARE-HAQ"),
    @XmlEnumValue("CD-ITEM-EBIRTH")
    CD_ITEM_EBIRTH("CD-ITEM-EBIRTH"),
    @XmlEnumValue("CD-PARAMETER")
    CD_PARAMETER("CD-PARAMETER"),
    @XmlEnumValue("CD-ITEM-BVT")
    CD_ITEM_BVT("CD-ITEM-BVT"),
    @XmlEnumValue("CD-BVT-AVAILABLEMATERIALS")
    CD_BVT_AVAILABLEMATERIALS("CD-BVT-AVAILABLEMATERIALS"),
    @XmlEnumValue("CD-BVT-CONSERVATIONDELAY")
    CD_BVT_CONSERVATIONDELAY("CD-BVT-CONSERVATIONDELAY"),
    @XmlEnumValue("CD-BVT-CONSERVATIONMODE")
    CD_BVT_CONSERVATIONMODE("CD-BVT-CONSERVATIONMODE"),
    @XmlEnumValue("CD-BVT-SAMPLETYPE")
    CD_BVT_SAMPLETYPE("CD-BVT-SAMPLETYPE"),
    @XmlEnumValue("CD-BCR-DIFFERENTATIONDEGREE")
    CD_BCR_DIFFERENTATIONDEGREE("CD-BCR-DIFFERENTATIONDEGREE"),
    @XmlEnumValue("CD-BVT-LATERALITY")
    CD_BVT_LATERALITY("CD-BVT-LATERALITY"),
    @XmlEnumValue("CD-BVT-PATIENTOPPOSITION")
    CD_BVT_PATIENTOPPOSITION("CD-BVT-PATIENTOPPOSITION"),
    @XmlEnumValue("CD-BVT-STATUS")
    CD_BVT_STATUS("CD-BVT-STATUS"),
    @XmlEnumValue("CD-ITEM-REG")
    CD_ITEM_REG("CD-ITEM-REG"),
    @XmlEnumValue("CD-ITEM-MYCARENET")
    CD_ITEM_MYCARENET("CD-ITEM-MYCARENET"),
    @XmlEnumValue("CD-DEFIB-DIAGNOSIS")
    CD_DEFIB_DIAGNOSIS("CD-DEFIB-DIAGNOSIS"),
    @XmlEnumValue("CD-ACTS-NURSING")
    CD_ACTS_NURSING("CD-ACTS-NURSING"),
    @XmlEnumValue("CD-QERMID-INTERVENTIONTYPE")
    CD_QERMID_INTERVENTIONTYPE("CD-QERMID-INTERVENTIONTYPE");
    private final String value;

    CDITEMschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDITEMschemes fromValue(String v) {
        for (CDITEMschemes c: CDITEMschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
