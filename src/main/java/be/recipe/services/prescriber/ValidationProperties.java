package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "validationProperties",
   propOrder = {"validationPropertiesParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "validationProperties"
)
public class ValidationProperties {
   @XmlElement(
      name = "ValidationPropertiesParamSealed",
      required = true
   )
   protected byte[] validationPropertiesParamSealed;
   @XmlElement(
      name = "ProgramIdentification",
      required = true
   )
   protected String programIdentification;
   @XmlElement(
      name = "Mguid",
      required = true
   )
   protected String mguid;

   public byte[] getValidationPropertiesParamSealed() {
      return this.validationPropertiesParamSealed;
   }

   public void setValidationPropertiesParamSealed(byte[] value) {
      this.validationPropertiesParamSealed = value;
   }

   public String getProgramIdentification() {
      return this.programIdentification;
   }

   public void setProgramIdentification(String value) {
      this.programIdentification = value;
   }

   public String getMguid() {
      return this.mguid;
   }

   public void setMguid(String value) {
      this.mguid = value;
   }

}
