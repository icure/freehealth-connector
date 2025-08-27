package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"signatureObject"}
)
@XmlRootElement(
   name = "VerifyRequest"
)
public class VerifyRequest extends RequestBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignatureObject"
   )
   protected SignatureObject signatureObject;

   public SignatureObject getSignatureObject() {
      return this.signatureObject;
   }

   public void setSignatureObject(SignatureObject value) {
      this.signatureObject = value;
   }
}
