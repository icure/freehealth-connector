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
 * <p>Classe Java pour CD-HCPARTYvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-HCPARTYvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="deptanatomopathology"/>
 *     &lt;enumeration value="deptanesthesiology"/>
 *     &lt;enumeration value="deptbacteriology"/>
 *     &lt;enumeration value="deptcardiology"/>
 *     &lt;enumeration value="deptdermatology"/>
 *     &lt;enumeration value="deptdietetics"/>
 *     &lt;enumeration value="deptemergency"/>
 *     &lt;enumeration value="deptgastroenterology"/>
 *     &lt;enumeration value="deptgeneralpractice"/>
 *     &lt;enumeration value="deptgenetics"/>
 *     &lt;enumeration value="deptgeriatry"/>
 *     &lt;enumeration value="deptgynecology"/>
 *     &lt;enumeration value="depthematology"/>
 *     &lt;enumeration value="deptintensivecare"/>
 *     &lt;enumeration value="deptkinesitherapy"/>
 *     &lt;enumeration value="deptlaboratory"/>
 *     &lt;enumeration value="deptmedicine"/>
 *     &lt;enumeration value="deptmolecularbiology"/>
 *     &lt;enumeration value="deptneonatalogy"/>
 *     &lt;enumeration value="deptnephrology"/>
 *     &lt;enumeration value="deptneurology"/>
 *     &lt;enumeration value="deptnte"/>
 *     &lt;enumeration value="deptnuclear"/>
 *     &lt;enumeration value="deptoncology"/>
 *     &lt;enumeration value="deptophtalmology"/>
 *     &lt;enumeration value="deptpediatry"/>
 *     &lt;enumeration value="deptpharmacy"/>
 *     &lt;enumeration value="deptphysiotherapy"/>
 *     &lt;enumeration value="deptpneumology"/>
 *     &lt;enumeration value="deptpsychiatry"/>
 *     &lt;enumeration value="deptradiology"/>
 *     &lt;enumeration value="deptradiotherapy"/>
 *     &lt;enumeration value="deptrhumatology"/>
 *     &lt;enumeration value="deptstomatology"/>
 *     &lt;enumeration value="deptsurgery"/>
 *     &lt;enumeration value="depttoxicology"/>
 *     &lt;enumeration value="depturology"/>
 *     &lt;enumeration value="orghospital"/>
 *     &lt;enumeration value="orginsurance"/>
 *     &lt;enumeration value="orglaboratory"/>
 *     &lt;enumeration value="orgpublichealth"/>
 *     &lt;enumeration value="persbiologist"/>
 *     &lt;enumeration value="persdentist"/>
 *     &lt;enumeration value="persnurse"/>
 *     &lt;enumeration value="persparamedical"/>
 *     &lt;enumeration value="perspharmacist"/>
 *     &lt;enumeration value="persphysician"/>
 *     &lt;enumeration value="perssocialworker"/>
 *     &lt;enumeration value="perstechnician"/>
 *     &lt;enumeration value="persadministrative"/>
 *     &lt;enumeration value="persmidwife"/>
 *     &lt;enumeration value="ecaresafe"/>
 *     &lt;enumeration value="application"/>
 *     &lt;enumeration value="hub"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-HCPARTYvalues")
@XmlEnum
public enum CDHCPARTYvalues {

    @XmlEnumValue("deptanatomopathology")
    DEPTANATOMOPATHOLOGY("deptanatomopathology"),
    @XmlEnumValue("deptanesthesiology")
    DEPTANESTHESIOLOGY("deptanesthesiology"),
    @XmlEnumValue("deptbacteriology")
    DEPTBACTERIOLOGY("deptbacteriology"),
    @XmlEnumValue("deptcardiology")
    DEPTCARDIOLOGY("deptcardiology"),
    @XmlEnumValue("deptdermatology")
    DEPTDERMATOLOGY("deptdermatology"),
    @XmlEnumValue("deptdietetics")
    DEPTDIETETICS("deptdietetics"),
    @XmlEnumValue("deptemergency")
    DEPTEMERGENCY("deptemergency"),
    @XmlEnumValue("deptgastroenterology")
    DEPTGASTROENTEROLOGY("deptgastroenterology"),
    @XmlEnumValue("deptgeneralpractice")
    DEPTGENERALPRACTICE("deptgeneralpractice"),
    @XmlEnumValue("deptgenetics")
    DEPTGENETICS("deptgenetics"),
    @XmlEnumValue("deptgeriatry")
    DEPTGERIATRY("deptgeriatry"),
    @XmlEnumValue("deptgynecology")
    DEPTGYNECOLOGY("deptgynecology"),
    @XmlEnumValue("depthematology")
    DEPTHEMATOLOGY("depthematology"),
    @XmlEnumValue("deptintensivecare")
    DEPTINTENSIVECARE("deptintensivecare"),
    @XmlEnumValue("deptkinesitherapy")
    DEPTKINESITHERAPY("deptkinesitherapy"),
    @XmlEnumValue("deptlaboratory")
    DEPTLABORATORY("deptlaboratory"),
    @XmlEnumValue("deptmedicine")
    DEPTMEDICINE("deptmedicine"),
    @XmlEnumValue("deptmolecularbiology")
    DEPTMOLECULARBIOLOGY("deptmolecularbiology"),
    @XmlEnumValue("deptneonatalogy")
    DEPTNEONATALOGY("deptneonatalogy"),
    @XmlEnumValue("deptnephrology")
    DEPTNEPHROLOGY("deptnephrology"),
    @XmlEnumValue("deptneurology")
    DEPTNEUROLOGY("deptneurology"),
    @XmlEnumValue("deptnte")
    DEPTNTE("deptnte"),
    @XmlEnumValue("deptnuclear")
    DEPTNUCLEAR("deptnuclear"),
    @XmlEnumValue("deptoncology")
    DEPTONCOLOGY("deptoncology"),
    @XmlEnumValue("deptophtalmology")
    DEPTOPHTALMOLOGY("deptophtalmology"),
    @XmlEnumValue("deptpediatry")
    DEPTPEDIATRY("deptpediatry"),
    @XmlEnumValue("deptpharmacy")
    DEPTPHARMACY("deptpharmacy"),
    @XmlEnumValue("deptphysiotherapy")
    DEPTPHYSIOTHERAPY("deptphysiotherapy"),
    @XmlEnumValue("deptpneumology")
    DEPTPNEUMOLOGY("deptpneumology"),
    @XmlEnumValue("deptpsychiatry")
    DEPTPSYCHIATRY("deptpsychiatry"),
    @XmlEnumValue("deptradiology")
    DEPTRADIOLOGY("deptradiology"),
    @XmlEnumValue("deptradiotherapy")
    DEPTRADIOTHERAPY("deptradiotherapy"),
    @XmlEnumValue("deptrhumatology")
    DEPTRHUMATOLOGY("deptrhumatology"),
    @XmlEnumValue("deptstomatology")
    DEPTSTOMATOLOGY("deptstomatology"),
    @XmlEnumValue("deptsurgery")
    DEPTSURGERY("deptsurgery"),
    @XmlEnumValue("depttoxicology")
    DEPTTOXICOLOGY("depttoxicology"),
    @XmlEnumValue("depturology")
    DEPTUROLOGY("depturology"),
    @XmlEnumValue("orghospital")
    ORGHOSPITAL("orghospital"),
    @XmlEnumValue("orginsurance")
    ORGINSURANCE("orginsurance"),
    @XmlEnumValue("orglaboratory")
    ORGLABORATORY("orglaboratory"),
    @XmlEnumValue("orgpublichealth")
    ORGPUBLICHEALTH("orgpublichealth"),
    @XmlEnumValue("persbiologist")
    PERSBIOLOGIST("persbiologist"),
    @XmlEnumValue("persdentist")
    PERSDENTIST("persdentist"),
    @XmlEnumValue("persnurse")
    PERSNURSE("persnurse"),
    @XmlEnumValue("persparamedical")
    PERSPARAMEDICAL("persparamedical"),
    @XmlEnumValue("perspharmacist")
    PERSPHARMACIST("perspharmacist"),
    @XmlEnumValue("persphysician")
    PERSPHYSICIAN("persphysician"),
    @XmlEnumValue("perssocialworker")
    PERSSOCIALWORKER("perssocialworker"),
    @XmlEnumValue("perstechnician")
    PERSTECHNICIAN("perstechnician"),
    @XmlEnumValue("persadministrative")
    PERSADMINISTRATIVE("persadministrative"),
    @XmlEnumValue("persmidwife")
    PERSMIDWIFE("persmidwife"),
    @XmlEnumValue("ecaresafe")
    ECARESAFE("ecaresafe"),
    @XmlEnumValue("application")
    APPLICATION("application"),
    @XmlEnumValue("hub")
    HUB("hub");
    private final String value;

    CDHCPARTYvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDHCPARTYvalues fromValue(String v) {
        for (CDHCPARTYvalues c: CDHCPARTYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
