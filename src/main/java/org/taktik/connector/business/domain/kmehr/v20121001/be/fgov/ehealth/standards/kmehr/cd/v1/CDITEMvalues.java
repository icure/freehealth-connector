//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2015.03.05 à 11:48:06 AM CET
//


package org.taktik.connector.business.domain.kmehr.v20121001.be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-ITEMvalues.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ITEMvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="admissiontype"/>
 *     &lt;enumeration value="adr"/>
 *     &lt;enumeration value="allergy"/>
 *     &lt;enumeration value="autonomy"/>
 *     &lt;enumeration value="bloodtransfusionrefusal"/>
 *     &lt;enumeration value="clinical"/>
 *     &lt;enumeration value="complaint"/>
 *     &lt;enumeration value="complementaryproduct"/>
 *     &lt;enumeration value="conclusion"/>
 *     &lt;enumeration value="contactperson"/>
 *     &lt;enumeration value="dischargedatetime"/>
 *     &lt;enumeration value="dischargedestination"/>
 *     &lt;enumeration value="dischargetype"/>
 *     &lt;enumeration value="emergencyevaluation"/>
 *     &lt;enumeration value="encounterdatetime"/>
 *     &lt;enumeration value="encounterlegalservice"/>
 *     &lt;enumeration value="encounterlocation"/>
 *     &lt;enumeration value="encounterresponsible"/>
 *     &lt;enumeration value="encountersafetyissue"/>
 *     &lt;enumeration value="encountertype"/>
 *     &lt;enumeration value="evolution"/>
 *     &lt;enumeration value="expirationdatetime"/>
 *     &lt;enumeration value="gmdmanager"/>
 *     &lt;enumeration value="habit"/>
 *     &lt;enumeration value="hcpartyavailability"/>
 *     &lt;enumeration value="healthcareelement"/>
 *     &lt;enumeration value="healthissue"/>
 *     &lt;enumeration value="incapacity"/>
 *     &lt;enumeration value="lab"/>
 *     &lt;enumeration value="medication"/>
 *     &lt;enumeration value="ntbr"/>
 *     &lt;enumeration value="referrer"/>
 *     &lt;enumeration value="referringtype"/>
 *     &lt;enumeration value="reimbursementcertificate"/>
 *     &lt;enumeration value="requestdatetime"/>
 *     &lt;enumeration value="requesteddecisionsharing"/>
 *     &lt;enumeration value="requesteddischargedestination"/>
 *     &lt;enumeration value="requestedencountertype"/>
 *     &lt;enumeration value="requestedrecipient"/>
 *     &lt;enumeration value="requestnumber"/>
 *     &lt;enumeration value="requestor"/>
 *     &lt;enumeration value="risk"/>
 *     &lt;enumeration value="socialrisk"/>
 *     &lt;enumeration value="specimendatetime"/>
 *     &lt;enumeration value="technical"/>
 *     &lt;enumeration value="transactionreason"/>
 *     &lt;enumeration value="transcriptionist"/>
 *     &lt;enumeration value="transferdatetime"/>
 *     &lt;enumeration value="treatment"/>
 *     &lt;enumeration value="vaccine"/>
 *     &lt;enumeration value="actionplan"/>
 *     &lt;enumeration value="acts"/>
 *     &lt;enumeration value="careplansubscription"/>
 *     &lt;enumeration value="contacthcparty"/>
 *     &lt;enumeration value="diagnosis"/>
 *     &lt;enumeration value="familyrisk"/>
 *     &lt;enumeration value="healthcareapproach"/>
 *     &lt;enumeration value="insurancystatus"/>
 *     &lt;enumeration value="memberinsurancystatus"/>
 *     &lt;enumeration value="parameter"/>
 *     &lt;enumeration value="patientwill"/>
 *     &lt;enumeration value="professionalrisk"/>
 *     &lt;enumeration value="encounternumber"/>
 *     &lt;enumeration value="claim"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-ITEMvalues")
@XmlEnum
public enum CDITEMvalues {

    @XmlEnumValue("admissiontype")
    ADMISSIONTYPE("admissiontype"),
    @XmlEnumValue("adr")
    ADR("adr"),
    @XmlEnumValue("allergy")
    ALLERGY("allergy"),
    @XmlEnumValue("autonomy")
    AUTONOMY("autonomy"),
    @XmlEnumValue("bloodtransfusionrefusal")
    BLOODTRANSFUSIONREFUSAL("bloodtransfusionrefusal"),
    @XmlEnumValue("clinical")
    CLINICAL("clinical"),
    @XmlEnumValue("complaint")
    COMPLAINT("complaint"),
    @XmlEnumValue("complementaryproduct")
    COMPLEMENTARYPRODUCT("complementaryproduct"),
    @XmlEnumValue("conclusion")
    CONCLUSION("conclusion"),
    @XmlEnumValue("contactperson")
    CONTACTPERSON("contactperson"),
    @XmlEnumValue("dischargedatetime")
    DISCHARGEDATETIME("dischargedatetime"),
    @XmlEnumValue("dischargedestination")
    DISCHARGEDESTINATION("dischargedestination"),
    @XmlEnumValue("dischargetype")
    DISCHARGETYPE("dischargetype"),
    @XmlEnumValue("emergencyevaluation")
    EMERGENCYEVALUATION("emergencyevaluation"),
    @XmlEnumValue("encounterdatetime")
    ENCOUNTERDATETIME("encounterdatetime"),
    @XmlEnumValue("encounterlegalservice")
    ENCOUNTERLEGALSERVICE("encounterlegalservice"),
    @XmlEnumValue("encounterlocation")
    ENCOUNTERLOCATION("encounterlocation"),
    @XmlEnumValue("encounterresponsible")
    ENCOUNTERRESPONSIBLE("encounterresponsible"),
    @XmlEnumValue("encountersafetyissue")
    ENCOUNTERSAFETYISSUE("encountersafetyissue"),
    @XmlEnumValue("encountertype")
    ENCOUNTERTYPE("encountertype"),
    @XmlEnumValue("evolution")
    EVOLUTION("evolution"),
    @XmlEnumValue("expirationdatetime")
    EXPIRATIONDATETIME("expirationdatetime"),
    @XmlEnumValue("gmdmanager")
    GMDMANAGER("gmdmanager"),
    @XmlEnumValue("habit")
    HABIT("habit"),
    @XmlEnumValue("hcpartyavailability")
    HCPARTYAVAILABILITY("hcpartyavailability"),
    @XmlEnumValue("healthcareelement")
    HEALTHCAREELEMENT("healthcareelement"),
    @XmlEnumValue("healthissue")
    HEALTHISSUE("healthissue"),
    @XmlEnumValue("incapacity")
    INCAPACITY("incapacity"),
    @XmlEnumValue("lab")
    LAB("lab"),
    @XmlEnumValue("medication")
    MEDICATION("medication"),
    @XmlEnumValue("ntbr")
    NTBR("ntbr"),
    @XmlEnumValue("referrer")
    REFERRER("referrer"),
    @XmlEnumValue("referringtype")
    REFERRINGTYPE("referringtype"),
    @XmlEnumValue("reimbursementcertificate")
    REIMBURSEMENTCERTIFICATE("reimbursementcertificate"),
    @XmlEnumValue("requestdatetime")
    REQUESTDATETIME("requestdatetime"),
    @XmlEnumValue("requesteddecisionsharing")
    REQUESTEDDECISIONSHARING("requesteddecisionsharing"),
    @XmlEnumValue("requesteddischargedestination")
    REQUESTEDDISCHARGEDESTINATION("requesteddischargedestination"),
    @XmlEnumValue("requestedencountertype")
    REQUESTEDENCOUNTERTYPE("requestedencountertype"),
    @XmlEnumValue("requestedrecipient")
    REQUESTEDRECIPIENT("requestedrecipient"),
    @XmlEnumValue("requestnumber")
    REQUESTNUMBER("requestnumber"),
    @XmlEnumValue("requestor")
    REQUESTOR("requestor"),
    @XmlEnumValue("risk")
    RISK("risk"),
    @XmlEnumValue("socialrisk")
    SOCIALRISK("socialrisk"),
    @XmlEnumValue("specimendatetime")
    SPECIMENDATETIME("specimendatetime"),
    @XmlEnumValue("technical")
    TECHNICAL("technical"),
    @XmlEnumValue("transactionreason")
    TRANSACTIONREASON("transactionreason"),
    @XmlEnumValue("transcriptionist")
    TRANSCRIPTIONIST("transcriptionist"),
    @XmlEnumValue("transferdatetime")
    TRANSFERDATETIME("transferdatetime"),
    @XmlEnumValue("treatment")
    TREATMENT("treatment"),
    @XmlEnumValue("vaccine")
    VACCINE("vaccine"),
    @XmlEnumValue("actionplan")
    ACTIONPLAN("actionplan"),
    @XmlEnumValue("acts")
    ACTS("acts"),
    @XmlEnumValue("careplansubscription")
    CAREPLANSUBSCRIPTION("careplansubscription"),
    @XmlEnumValue("contacthcparty")
    CONTACTHCPARTY("contacthcparty"),
    @XmlEnumValue("diagnosis")
    DIAGNOSIS("diagnosis"),
    @XmlEnumValue("familyrisk")
    FAMILYRISK("familyrisk"),
    @XmlEnumValue("healthcareapproach")
    HEALTHCAREAPPROACH("healthcareapproach"),
    @XmlEnumValue("insurancystatus")
    INSURANCYSTATUS("insurancystatus"),
    @XmlEnumValue("memberinsurancystatus")
    MEMBERINSURANCYSTATUS("memberinsurancystatus"),
    @XmlEnumValue("parameter")
    PARAMETER("parameter"),
    @XmlEnumValue("patientwill")
    PATIENTWILL("patientwill"),
    @XmlEnumValue("professionalrisk")
    PROFESSIONALRISK("professionalrisk"),
    @XmlEnumValue("encounternumber")
    ENCOUNTERNUMBER("encounternumber"),
    @XmlEnumValue("claim")
    CLAIM("claim");
    private final String value;

    CDITEMvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDITEMvalues fromValue(String v) {
        for (CDITEMvalues c: CDITEMvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
