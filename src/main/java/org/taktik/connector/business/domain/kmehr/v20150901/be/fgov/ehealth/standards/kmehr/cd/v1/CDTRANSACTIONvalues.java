//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.11.10 à 11:53:46 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-TRANSACTIONvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-TRANSACTIONvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="admission"/>
 *     &lt;enumeration value="alert"/>
 *     &lt;enumeration value="contactreport"/>
 *     &lt;enumeration value="death"/>
 *     &lt;enumeration value="discharge"/>
 *     &lt;enumeration value="dischargereport"/>
 *     &lt;enumeration value="epidemiology"/>
 *     &lt;enumeration value="labrequest"/>
 *     &lt;enumeration value="labresult"/>
 *     &lt;enumeration value="note"/>
 *     &lt;enumeration value="hospitalpharmaceuticalprescription"/>
 *     &lt;enumeration value="pharmaceuticalprescription"/>
 *     &lt;enumeration value="productdelivery"/>
 *     &lt;enumeration value="quickdischargereport"/>
 *     &lt;enumeration value="referral"/>
 *     &lt;enumeration value="request"/>
 *     &lt;enumeration value="result"/>
 *     &lt;enumeration value="vaccination"/>
 *     &lt;enumeration value="sumehr"/>
 *     &lt;enumeration value="ecare-safe-consultation"/>
 *     &lt;enumeration value="ebirth-mother-notification"/>
 *     &lt;enumeration value="ebirth-baby-notification"/>
 *     &lt;enumeration value="ebirth-baby-medicalform"/>
 *     &lt;enumeration value="ebirth-mother-medicalform"/>
 *     &lt;enumeration value="clinicalsummary"/>
 *     &lt;enumeration value="medicaladvisoragreement"/>
 *     &lt;enumeration value="bvt-sample"/>
 *     &lt;enumeration value="clinicalpath"/>
 *     &lt;enumeration value="telemonitoring"/>
 *     &lt;enumeration value="radiationexposuremonitoring"/>
 *     &lt;enumeration value="intervention"/>
 *     &lt;enumeration value="medicationschemeelement"/>
 *     &lt;enumeration value="treatmentsuspension"/>
 *     &lt;enumeration value="prescription"/>
 *     &lt;enumeration value="notification"/>
 *     &lt;enumeration value="report"/>
 *     &lt;enumeration value="medicationscheme"/>
 *     &lt;enumeration value="vaccinationscheme"/>
 *     &lt;enumeration value="vaccinationschemeelement"/>
 *     &lt;enumeration value="ecare-tardis-consultation"/>
 *     &lt;enumeration value="genericregistryentry"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-TRANSACTIONvalues")
@XmlEnum
public enum CDTRANSACTIONvalues {

    @XmlEnumValue("admission")
    ADMISSION("admission"),
    @XmlEnumValue("alert")
    ALERT("alert"),
    @XmlEnumValue("contactreport")
    CONTACTREPORT("contactreport"),
    @XmlEnumValue("death")
    DEATH("death"),
    @XmlEnumValue("discharge")
    DISCHARGE("discharge"),
    @XmlEnumValue("dischargereport")
    DISCHARGEREPORT("dischargereport"),
    @XmlEnumValue("epidemiology")
    EPIDEMIOLOGY("epidemiology"),
    @XmlEnumValue("labrequest")
    LABREQUEST("labrequest"),
    @XmlEnumValue("labresult")
    LABRESULT("labresult"),
    @XmlEnumValue("note")
    NOTE("note"),
    @XmlEnumValue("hospitalpharmaceuticalprescription")
    HOSPITALPHARMACEUTICALPRESCRIPTION("hospitalpharmaceuticalprescription"),
    @XmlEnumValue("pharmaceuticalprescription")
    PHARMACEUTICALPRESCRIPTION("pharmaceuticalprescription"),
    @XmlEnumValue("productdelivery")
    PRODUCTDELIVERY("productdelivery"),
    @XmlEnumValue("quickdischargereport")
    QUICKDISCHARGEREPORT("quickdischargereport"),
    @XmlEnumValue("referral")
    REFERRAL("referral"),
    @XmlEnumValue("request")
    REQUEST("request"),
    @XmlEnumValue("result")
    RESULT("result"),
    @XmlEnumValue("vaccination")
    VACCINATION("vaccination"),
    @XmlEnumValue("sumehr")
    SUMEHR("sumehr"),
    @XmlEnumValue("ecare-safe-consultation")
    ECARE_SAFE_CONSULTATION("ecare-safe-consultation"),
    @XmlEnumValue("ebirth-mother-notification")
    EBIRTH_MOTHER_NOTIFICATION("ebirth-mother-notification"),
    @XmlEnumValue("ebirth-baby-notification")
    EBIRTH_BABY_NOTIFICATION("ebirth-baby-notification"),
    @XmlEnumValue("ebirth-baby-medicalform")
    EBIRTH_BABY_MEDICALFORM("ebirth-baby-medicalform"),
    @XmlEnumValue("ebirth-mother-medicalform")
    EBIRTH_MOTHER_MEDICALFORM("ebirth-mother-medicalform"),
    @XmlEnumValue("clinicalsummary")
    CLINICALSUMMARY("clinicalsummary"),
    @XmlEnumValue("medicaladvisoragreement")
    MEDICALADVISORAGREEMENT("medicaladvisoragreement"),
    @XmlEnumValue("bvt-sample")
    BVT_SAMPLE("bvt-sample"),
    @XmlEnumValue("clinicalpath")
    CLINICALPATH("clinicalpath"),
    @XmlEnumValue("telemonitoring")
    TELEMONITORING("telemonitoring"),
    @XmlEnumValue("radiationexposuremonitoring")
    RADIATIONEXPOSUREMONITORING("radiationexposuremonitoring"),
    @XmlEnumValue("intervention")
    INTERVENTION("intervention"),
    @XmlEnumValue("medicationschemeelement")
    MEDICATIONSCHEMEELEMENT("medicationschemeelement"),
    @XmlEnumValue("treatmentsuspension")
    TREATMENTSUSPENSION("treatmentsuspension"),
    @XmlEnumValue("prescription")
    PRESCRIPTION("prescription"),
    @XmlEnumValue("notification")
    NOTIFICATION("notification"),
    @XmlEnumValue("report")
    REPORT("report"),
    @XmlEnumValue("medicationscheme")
    MEDICATIONSCHEME("medicationscheme"),
    @XmlEnumValue("vaccinationscheme")
    VACCINATIONSCHEME("vaccinationscheme"),
    @XmlEnumValue("vaccinationschemeelement")
    VACCINATIONSCHEMEELEMENT("vaccinationschemeelement"),
    @XmlEnumValue("ecare-tardis-consultation")
    ECARE_TARDIS_CONSULTATION("ecare-tardis-consultation"),
    @XmlEnumValue("genericregistryentry")
    GENERICREGISTRYENTRY("genericregistryentry");
    private final String value;

    CDTRANSACTIONvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDTRANSACTIONvalues fromValue(String v) {
        for (CDTRANSACTIONvalues c: CDTRANSACTIONvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
