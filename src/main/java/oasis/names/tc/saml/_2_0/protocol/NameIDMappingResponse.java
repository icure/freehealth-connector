package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.EncryptedElementType;
import oasis.names.tc.saml._2_0.assertion.NameIDType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameIDMappingResponseType",
   propOrder = {"encryptedID", "nameID"}
)
@XmlRootElement(
   name = "NameIDMappingResponse"
)
public class NameIDMappingResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptedID",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected EncryptedElementType encryptedID;
   @XmlElement(
      name = "NameID",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected NameIDType nameID;

   public EncryptedElementType getEncryptedID() {
      return this.encryptedID;
   }

   public void setEncryptedID(EncryptedElementType value) {
      this.encryptedID = value;
   }

   public NameIDType getNameID() {
      return this.nameID;
   }

   public void setNameID(NameIDType value) {
      this.nameID = value;
   }
}
