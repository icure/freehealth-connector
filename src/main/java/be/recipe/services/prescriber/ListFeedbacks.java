package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacks",
   propOrder = {"listFeedbacksParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "listFeedbacks"
)
public class ListFeedbacks {
   @XmlElement(
      name = "ListFeedbacksParamSealed",
      required = true
   )
   protected byte[] listFeedbacksParamSealed;
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

   public byte[] getListFeedbacksParamSealed() {
      return this.listFeedbacksParamSealed;
   }

   public void setListFeedbacksParamSealed(byte[] value) {
      this.listFeedbacksParamSealed = value;
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
