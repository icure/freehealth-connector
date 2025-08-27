package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.ContactDataType;
import be.fgov.ehealth.commons.core.v2.ActorType;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubmitCSRForForeignerRequestType",
   propOrder = {"foreignPerson", "contactData", "csr"}
)
@XmlRootElement(
   name = "SubmitCSRForForeignerRequest"
)
public class SubmitCSRForForeignerRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ForeignPerson",
      required = true
   )
   protected ActorType foreignPerson;
   @XmlElement(
      name = "ContactData",
      required = true
   )
   protected ContactDataType contactData;
   @XmlElement(
      name = "CSR",
      required = true
   )
   protected byte[] csr;

   public ActorType getForeignPerson() {
      return this.foreignPerson;
   }

   public void setForeignPerson(ActorType value) {
      this.foreignPerson = value;
   }

   public ContactDataType getContactData() {
      return this.contactData;
   }

   public void setContactData(ContactDataType value) {
      this.contactData = value;
   }

   public byte[] getCSR() {
      return this.csr;
   }

   public void setCSR(byte[] value) {
      this.csr = value;
   }
}
