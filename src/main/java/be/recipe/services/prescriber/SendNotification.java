package be.recipe.services.prescriber;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "sendNotification",
   propOrder = {"sendNotificationParamSealed", "programIdentification", "mguid"}
)
@XmlRootElement(
   name = "sendNotification"
)
public class SendNotification {
   @XmlElement(
      name = "SendNotificationParamSealed",
      required = true
   )
   protected byte[] sendNotificationParamSealed;
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

   public byte[] getSendNotificationParamSealed() {
      return this.sendNotificationParamSealed;
   }

   public void setSendNotificationParamSealed(byte[] value) {
      this.sendNotificationParamSealed = value;
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
