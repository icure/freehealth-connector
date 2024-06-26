//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2023.04.28 at 11:20:57 AM CEST
//


package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import be.recipe.services.core.VisionOtherPrescribers;
import be.recipe.services.core.VisionType;


/**
 * <p>Java class for putVisionParam complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="putVisionParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="symmKey" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="rid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visionOtherPrescribers" type="{http:/services.recipe.be/core}VisionOtherPrescribers" minOccurs="0"/>
 *       &lt;/all>
 *       &lt;attribute name="type" type="{http:/services.recipe.be/core}VisionType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "putVisionParam", propOrder = {

})
@XmlRootElement(name = "putVisionParam")
public class PutVisionParam {

   @XmlElement(required = true)
   protected byte[] symmKey;
   @XmlElement(required = true)
   protected String rid;
   protected String vision;
   protected VisionOtherPrescribers visionOtherPrescribers;
   @XmlAttribute(name = "type")
   protected VisionType type;

   /**
    * Gets the value of the symmKey property.
    *
    * @return
    *     possible object is
    *     byte[]
    */
   public byte[] getSymmKey() {
      return symmKey;
   }

   /**
    * Sets the value of the symmKey property.
    *
    * @param value
    *     allowed object is
    *     byte[]
    */
   public void setSymmKey(byte[] value) {
      this.symmKey = value;
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
    * Gets the value of the vision property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getVision() {
      return vision;
   }

   /**
    * Sets the value of the vision property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setVision(String value) {
      this.vision = value;
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
    * Gets the value of the type property.
    *
    * @return
    *     possible object is
    *     {@link VisionType }
    *
    */
   public VisionType getType() {
      return type;
   }

   /**
    * Sets the value of the type property.
    *
    * @param value
    *     allowed object is
    *     {@link VisionType }
    *
    */
   public void setType(VisionType value) {
      this.type = value;
   }

}
