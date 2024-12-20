//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2023.04.28 at 11:20:57 AM CEST
//


package be.fgov.ehealth.recipe.protocol.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import be.recipe.services.core.PartialResult;
import be.recipe.services.core.PrescriberForSearchResult;
import be.recipe.services.core.PrescriptionStatus;
import be.recipe.services.core.ResponseType;
import be.recipe.services.core.VisionOtherPrescribers;


/**
 * <p>Java class for listPrescriptionsResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="listPrescriptionsResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http:/services.recipe.be/core}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="partial" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http:/services.recipe.be/core}PartialResult">
 *                 &lt;sequence>
 *                   &lt;element name="prescription" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="prescriber" type="{http:/services.recipe.be/core}prescriberForSearchResult"/>
 *                           &lt;/all>
 *                           &lt;attribute name="rid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="status" use="required" type="{http:/services.recipe.be/core}prescriptionStatus" />
 *                           &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                           &lt;attribute name="valid-until" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                           &lt;attribute name="vision-other-prescribers" use="required" type="{http:/services.recipe.be/core}VisionOtherPrescribers" />
 *                           &lt;attribute name="encryption-key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="encrypted-content" type="{http://www.w3.org/2001/XMLSchema}base64Binary" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="hasHidden" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listPrescriptionsResult", propOrder = {
    "partial"
})
@XmlRootElement(name = "listPrescriptionsResult")
public class ListPrescriptionsResult
    extends ResponseType
{

    protected Partial partial;

    /**
     * Gets the value of the partial property.
     *
     * @return
     *     possible object is
     *     {@link Partial }
     *
     */
    public Partial getPartial() {
        return partial;
    }

    /**
     * Sets the value of the partial property.
     *
     * @param value
     *     allowed object is
     *     {@link Partial }
     *
     */
    public void setPartial(Partial value) {
        this.partial = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http:/services.recipe.be/core}PartialResult">
     *       &lt;sequence>
     *         &lt;element name="prescription" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="prescriber" type="{http:/services.recipe.be/core}prescriberForSearchResult"/>
     *                 &lt;/all>
     *                 &lt;attribute name="rid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="status" use="required" type="{http:/services.recipe.be/core}prescriptionStatus" />
     *                 &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *                 &lt;attribute name="valid-until" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *                 &lt;attribute name="vision-other-prescribers" use="required" type="{http:/services.recipe.be/core}VisionOtherPrescribers" />
     *                 &lt;attribute name="encryption-key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="encrypted-content" type="{http://www.w3.org/2001/XMLSchema}base64Binary" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="hasHidden" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "prescriptions"
    })
    public static class Partial
        extends PartialResult
    {

        @XmlElement(name = "prescription")
        protected List<Prescription> prescriptions;
        @XmlAttribute(name = "hasHidden", required = true)
        protected boolean hasHidden;

        /**
         * Gets the value of the prescriptions property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the prescriptions property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPrescriptions().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Prescription }
         *
         *
         */
        public List<Prescription> getPrescriptions() {
            if (prescriptions == null) {
                prescriptions = new ArrayList<Prescription>();
            }
            return this.prescriptions;
        }

        /**
         * Gets the value of the hasHidden property.
         *
         */
        public boolean isHasHidden() {
            return hasHidden;
        }

        /**
         * Sets the value of the hasHidden property.
         *
         */
        public void setHasHidden(boolean value) {
            this.hasHidden = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;all>
         *         &lt;element name="prescriber" type="{http:/services.recipe.be/core}prescriberForSearchResult"/>
         *       &lt;/all>
         *       &lt;attribute name="rid" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="status" use="required" type="{http:/services.recipe.be/core}prescriptionStatus" />
         *       &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
         *       &lt;attribute name="valid-until" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
         *       &lt;attribute name="vision-other-prescribers" use="required" type="{http:/services.recipe.be/core}VisionOtherPrescribers" />
         *       &lt;attribute name="encryption-key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="encrypted-content" type="{http://www.w3.org/2001/XMLSchema}base64Binary" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class Prescription {

            @XmlElement(required = true)
            protected PrescriberForSearchResult prescriber;
            @XmlAttribute(name = "rid", required = true)
            protected String rid;
            @XmlAttribute(name = "status", required = true)
            protected PrescriptionStatus status;
            @XmlAttribute(name = "date", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar date;
            @XmlAttribute(name = "valid-until", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar validUntil;
            @XmlAttribute(name = "vision-other-prescribers", required = true)
            protected VisionOtherPrescribers visionOtherPrescribers;
            @XmlAttribute(name = "encryption-key", required = true)
            protected String encryptionKey;
            @XmlAttribute(name = "encrypted-content")
            protected byte[] encryptedContent;

            /**
             * Gets the value of the prescriber property.
             *
             * @return
             *     possible object is
             *     {@link PrescriberForSearchResult }
             *
             */
            public PrescriberForSearchResult getPrescriber() {
                return prescriber;
            }

            /**
             * Sets the value of the prescriber property.
             *
             * @param value
             *     allowed object is
             *     {@link PrescriberForSearchResult }
             *
             */
            public void setPrescriber(PrescriberForSearchResult value) {
                this.prescriber = value;
            }

            /**
             * Gets the value of the rid property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getRid() {
                return rid;
            }

            /**
             * Sets the value of the rid property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setRid(String value) {
                this.rid = value;
            }

            /**
             * Gets the value of the status property.
             *
             * @return
             *     possible object is
             *     {@link PrescriptionStatus }
             *
             */
            public PrescriptionStatus getStatus() {
                return status;
            }

            /**
             * Sets the value of the status property.
             *
             * @param value
             *     allowed object is
             *     {@link PrescriptionStatus }
             *
             */
            public void setStatus(PrescriptionStatus value) {
                this.status = value;
            }

            /**
             * Gets the value of the date property.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getDate() {
                return date;
            }

            /**
             * Sets the value of the date property.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setDate(XMLGregorianCalendar value) {
                this.date = value;
            }

            /**
             * Gets the value of the validUntil property.
             *
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public XMLGregorianCalendar getValidUntil() {
                return validUntil;
            }

            /**
             * Sets the value of the validUntil property.
             *
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *
             */
            public void setValidUntil(XMLGregorianCalendar value) {
                this.validUntil = value;
            }

            /**
             * Gets the value of the visionOtherPrescribers property.
             *
             * @return
             *     possible object is
             *     {@link VisionOtherPrescribers }
             *
             */
            public VisionOtherPrescribers getVisionOtherPrescribers() {
                return visionOtherPrescribers;
            }

            /**
             * Sets the value of the visionOtherPrescribers property.
             *
             * @param value
             *     allowed object is
             *     {@link VisionOtherPrescribers }
             *
             */
            public void setVisionOtherPrescribers(VisionOtherPrescribers value) {
                this.visionOtherPrescribers = value;
            }

            /**
             * Gets the value of the encryptionKey property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getEncryptionKey() {
                return encryptionKey;
            }

            /**
             * Sets the value of the encryptionKey property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setEncryptionKey(String value) {
                this.encryptionKey = value;
            }

            /**
             * Gets the value of the encryptedContent property.
             *
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getEncryptedContent() {
                return encryptedContent;
            }

            /**
             * Sets the value of the encryptedContent property.
             *
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setEncryptedContent(byte[] value) {
                this.encryptedContent = value;
            }

        }

    }

}
