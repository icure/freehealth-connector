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
 * <p>Classe Java pour CD-CONTACT-PERSONvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-CONTACT-PERSONvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="mother"/>
 *     &lt;enumeration value="father"/>
 *     &lt;enumeration value="child"/>
 *     &lt;enumeration value="familymember"/>
 *     &lt;enumeration value="spouse"/>
 *     &lt;enumeration value="husband"/>
 *     &lt;enumeration value="partner"/>
 *     &lt;enumeration value="other"/>
 *     &lt;enumeration value="brother"/>
 *     &lt;enumeration value="sister"/>
 *     &lt;enumeration value="brotherinlaw"/>
 *     &lt;enumeration value="tutor"/>
 *     &lt;enumeration value="notary"/>
 *     &lt;enumeration value="lawyer"/>
 *     &lt;enumeration value="employer"/>
 *     &lt;enumeration value="grandparent"/>
 *     &lt;enumeration value="son"/>
 *     &lt;enumeration value="daughter"/>
 *     &lt;enumeration value="grandchild"/>
 *     &lt;enumeration value="neighbour"/>
 *     &lt;enumeration value="stepson"/>
 *     &lt;enumeration value="stepdaughter"/>
 *     &lt;enumeration value="stepfather"/>
 *     &lt;enumeration value="stepmother"/>
 *     &lt;enumeration value="sisterinlaw"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-CONTACT-PERSONvalues")
@XmlEnum
public enum CDCONTACTPERSONvalues {

    @XmlEnumValue("mother")
    MOTHER("mother"),
    @XmlEnumValue("father")
    FATHER("father"),
    @XmlEnumValue("child")
    CHILD("child"),
    @XmlEnumValue("familymember")
    FAMILYMEMBER("familymember"),
    @XmlEnumValue("spouse")
    SPOUSE("spouse"),
    @XmlEnumValue("husband")
    HUSBAND("husband"),
    @XmlEnumValue("partner")
    PARTNER("partner"),
    @XmlEnumValue("other")
    OTHER("other"),
    @XmlEnumValue("brother")
    BROTHER("brother"),
    @XmlEnumValue("sister")
    SISTER("sister"),
    @XmlEnumValue("brotherinlaw")
    BROTHERINLAW("brotherinlaw"),
    @XmlEnumValue("tutor")
    TUTOR("tutor"),
    @XmlEnumValue("notary")
    NOTARY("notary"),
    @XmlEnumValue("lawyer")
    LAWYER("lawyer"),
    @XmlEnumValue("employer")
    EMPLOYER("employer"),
    @XmlEnumValue("grandparent")
    GRANDPARENT("grandparent"),
    @XmlEnumValue("son")
    SON("son"),
    @XmlEnumValue("daughter")
    DAUGHTER("daughter"),
    @XmlEnumValue("grandchild")
    GRANDCHILD("grandchild"),
    @XmlEnumValue("neighbour")
    NEIGHBOUR("neighbour"),
    @XmlEnumValue("stepson")
    STEPSON("stepson"),
    @XmlEnumValue("stepdaughter")
    STEPDAUGHTER("stepdaughter"),
    @XmlEnumValue("stepfather")
    STEPFATHER("stepfather"),
    @XmlEnumValue("stepmother")
    STEPMOTHER("stepmother"),
    @XmlEnumValue("sisterinlaw")
    SISTERINLAW("sisterinlaw");
    private final String value;

    CDCONTACTPERSONvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDCONTACTPERSONvalues fromValue(String v) {
        for (CDCONTACTPERSONvalues c: CDCONTACTPERSONvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
