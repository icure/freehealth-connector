package be.cin.mycarenet.esb.medicaladvisoragreement.chap4.ask.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"encryptedContent"}
)
public class RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptedContent",
      required = true
   )
   protected byte[] encryptedContent;

   public byte[] getEncryptedContent() {
      return this.encryptedContent;
   }

   public void setEncryptedContent(byte[] value) {
      this.encryptedContent = value;
   }
}
