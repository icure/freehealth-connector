package be.fgov.ehealth.certra.core.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContactDataType",
   propOrder = {"emailPrivate", "emailGeneral", "phonePrivate", "phoneGeneral"}
)
public class ContactDataType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EmailPrivate",
      required = true
   )
   protected String emailPrivate;
   @XmlElement(
      name = "EmailGeneral"
   )
   protected String emailGeneral;
   @XmlElement(
      name = "PhonePrivate",
      required = true
   )
   protected String phonePrivate;
   @XmlElement(
      name = "PhoneGeneral"
   )
   protected String phoneGeneral;

   public String getEmailPrivate() {
      return this.emailPrivate;
   }

   public void setEmailPrivate(String value) {
      this.emailPrivate = value;
   }

   public String getEmailGeneral() {
      return this.emailGeneral;
   }

   public void setEmailGeneral(String value) {
      this.emailGeneral = value;
   }

   public String getPhonePrivate() {
      return this.phonePrivate;
   }

   public void setPhonePrivate(String value) {
      this.phonePrivate = value;
   }

   public String getPhoneGeneral() {
      return this.phoneGeneral;
   }

   public void setPhoneGeneral(String value) {
      this.phoneGeneral = value;
   }
}
