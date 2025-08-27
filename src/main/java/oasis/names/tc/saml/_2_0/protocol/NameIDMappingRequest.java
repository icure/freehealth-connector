package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.BaseID;
import oasis.names.tc.saml._2_0.assertion.EncryptedElementType;
import oasis.names.tc.saml._2_0.assertion.NameIDType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameIDMappingRequestType",
   propOrder = {"encryptedID", "nameID", "baseID", "nameIDPolicy"}
)
@XmlRootElement(
   name = "NameIDMappingRequest"
)
public class NameIDMappingRequest extends RequestAbstractType implements Serializable {
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
   @XmlElement(
      name = "BaseID",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected BaseID baseID;
   @XmlElement(
      name = "NameIDPolicy",
      required = true
   )
   protected NameIDPolicy nameIDPolicy;

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

   public BaseID getBaseID() {
      return this.baseID;
   }

   public void setBaseID(BaseID value) {
      this.baseID = value;
   }

   public NameIDPolicy getNameIDPolicy() {
      return this.nameIDPolicy;
   }

   public void setNameIDPolicy(NameIDPolicy value) {
      this.nameIDPolicy = value;
   }
}
