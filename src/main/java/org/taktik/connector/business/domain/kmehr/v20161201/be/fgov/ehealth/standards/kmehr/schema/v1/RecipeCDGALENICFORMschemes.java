//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.11 à 02:53:46 PM CEST
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDGALENICFORMschemes;


/**
 * <p>Classe Java pour recipeCD-GALENICFORMschemes.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="recipeCD-GALENICFORMschemes">
 *   &lt;restriction base="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-GALENICFORMschemes">
 *     &lt;enumeration value="CD-MAGISTRALFORM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "recipeCD-GALENICFORMschemes")
@XmlEnum(CDGALENICFORMschemes.class)
public enum RecipeCDGALENICFORMschemes {

    @XmlEnumValue("CD-MAGISTRALFORM")
    CD_MAGISTRALFORM(CDGALENICFORMschemes.CD_MAGISTRALFORM);
    private final CDGALENICFORMschemes value;

    RecipeCDGALENICFORMschemes(CDGALENICFORMschemes v) {
        value = v;
    }

    public CDGALENICFORMschemes value() {
        return value;
    }

    public static RecipeCDGALENICFORMschemes fromValue(CDGALENICFORMschemes v) {
        for (RecipeCDGALENICFORMschemes c: RecipeCDGALENICFORMschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
