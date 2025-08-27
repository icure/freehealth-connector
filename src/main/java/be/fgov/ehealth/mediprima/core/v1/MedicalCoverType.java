
package be.fgov.ehealth.mediprima.core.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Representation of all the covers of a medical card.
 *
 * <p>Classe Java pour MedicalCoverType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="MedicalCoverType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Doctor" type="{urn:be:fgov:ehealth:mediprima:core:v1}DoctorType" minOccurs="0"/>
 *         &lt;element name="Hospitalization" type="{urn:be:fgov:ehealth:mediprima:core:v1}HospitalizationType" minOccurs="0"/>
 *         &lt;element name="AmbulatoryHospitalization" type="{urn:be:fgov:ehealth:mediprima:core:v1}HospitalizationType" minOccurs="0"/>
 *         &lt;element name="MedicalTransportation" type="{urn:be:fgov:ehealth:mediprima:core:v1}MedicalTransportationType" minOccurs="0"/>
 *         &lt;element name="Miscellaneous" type="{urn:be:fgov:ehealth:mediprima:core:v1}MiscellaneousType" minOccurs="0"/>
 *         &lt;element name="Paramedic" type="{urn:be:fgov:ehealth:mediprima:core:v1}ParamedicType" minOccurs="0"/>
 *         &lt;element name="PharmaceuticalDrug" type="{urn:be:fgov:ehealth:mediprima:core:v1}PharmaceuticalDrugType" minOccurs="0"/>
 *         &lt;element name="Prosthesis" type="{urn:be:fgov:ehealth:mediprima:core:v1}ProsthesisType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedicalCoverType", namespace = "urn:be:fgov:ehealth:mediprima:core:v1", propOrder = {
    "doctor",
    "hospitalization",
    "ambulatoryHospitalization",
    "medicalTransportation",
    "miscellaneous",
    "paramedic",
    "pharmaceuticalDrug",
    "prosthesis"
})
public class MedicalCoverType {

    @XmlElement(name = "Doctor")
    protected DoctorType doctor;
    @XmlElement(name = "Hospitalization")
    protected HospitalizationType hospitalization;
    @XmlElement(name = "AmbulatoryHospitalization")
    protected HospitalizationType ambulatoryHospitalization;
    @XmlElement(name = "MedicalTransportation")
    protected MedicalTransportationType medicalTransportation;
    @XmlElement(name = "Miscellaneous")
    protected MiscellaneousType miscellaneous;
    @XmlElement(name = "Paramedic")
    protected ParamedicType paramedic;
    @XmlElement(name = "PharmaceuticalDrug")
    protected PharmaceuticalDrugType pharmaceuticalDrug;
    @XmlElement(name = "Prosthesis")
    protected ProsthesisType prosthesis;

    /**
     * Obtient la valeur de la propriété doctor.
     *
     * @return
     *     possible object is
     *     {@link DoctorType }
     *
     */
    public DoctorType getDoctor() {
        return doctor;
    }

    /**
     * Définit la valeur de la propriété doctor.
     *
     * @param value
     *     allowed object is
     *     {@link DoctorType }
     *
     */
    public void setDoctor(DoctorType value) {
        this.doctor = value;
    }

    /**
     * Obtient la valeur de la propriété hospitalization.
     *
     * @return
     *     possible object is
     *     {@link HospitalizationType }
     *
     */
    public HospitalizationType getHospitalization() {
        return hospitalization;
    }

    /**
     * Définit la valeur de la propriété hospitalization.
     *
     * @param value
     *     allowed object is
     *     {@link HospitalizationType }
     *
     */
    public void setHospitalization(HospitalizationType value) {
        this.hospitalization = value;
    }

    /**
     * Obtient la valeur de la propriété ambulatoryHospitalization.
     *
     * @return
     *     possible object is
     *     {@link HospitalizationType }
     *
     */
    public HospitalizationType getAmbulatoryHospitalization() {
        return ambulatoryHospitalization;
    }

    /**
     * Définit la valeur de la propriété ambulatoryHospitalization.
     *
     * @param value
     *     allowed object is
     *     {@link HospitalizationType }
     *
     */
    public void setAmbulatoryHospitalization(HospitalizationType value) {
        this.ambulatoryHospitalization = value;
    }

    /**
     * Obtient la valeur de la propriété medicalTransportation.
     *
     * @return
     *     possible object is
     *     {@link MedicalTransportationType }
     *
     */
    public MedicalTransportationType getMedicalTransportation() {
        return medicalTransportation;
    }

    /**
     * Définit la valeur de la propriété medicalTransportation.
     *
     * @param value
     *     allowed object is
     *     {@link MedicalTransportationType }
     *
     */
    public void setMedicalTransportation(MedicalTransportationType value) {
        this.medicalTransportation = value;
    }

    /**
     * Obtient la valeur de la propriété miscellaneous.
     *
     * @return
     *     possible object is
     *     {@link MiscellaneousType }
     *
     */
    public MiscellaneousType getMiscellaneous() {
        return miscellaneous;
    }

    /**
     * Définit la valeur de la propriété miscellaneous.
     *
     * @param value
     *     allowed object is
     *     {@link MiscellaneousType }
     *
     */
    public void setMiscellaneous(MiscellaneousType value) {
        this.miscellaneous = value;
    }

    /**
     * Obtient la valeur de la propriété paramedic.
     *
     * @return
     *     possible object is
     *     {@link ParamedicType }
     *
     */
    public ParamedicType getParamedic() {
        return paramedic;
    }

    /**
     * Définit la valeur de la propriété paramedic.
     *
     * @param value
     *     allowed object is
     *     {@link ParamedicType }
     *
     */
    public void setParamedic(ParamedicType value) {
        this.paramedic = value;
    }

    /**
     * Obtient la valeur de la propriété pharmaceuticalDrug.
     *
     * @return
     *     possible object is
     *     {@link PharmaceuticalDrugType }
     *
     */
    public PharmaceuticalDrugType getPharmaceuticalDrug() {
        return pharmaceuticalDrug;
    }

    /**
     * Définit la valeur de la propriété pharmaceuticalDrug.
     *
     * @param value
     *     allowed object is
     *     {@link PharmaceuticalDrugType }
     *
     */
    public void setPharmaceuticalDrug(PharmaceuticalDrugType value) {
        this.pharmaceuticalDrug = value;
    }

    /**
     * Obtient la valeur de la propriété prosthesis.
     *
     * @return
     *     possible object is
     *     {@link ProsthesisType }
     *
     */
    public ProsthesisType getProsthesis() {
        return prosthesis;
    }

    /**
     * Définit la valeur de la propriété prosthesis.
     *
     * @param value
     *     allowed object is
     *     {@link ProsthesisType }
     *
     */
    public void setProsthesis(ProsthesisType value) {
        this.prosthesis = value;
    }

}
