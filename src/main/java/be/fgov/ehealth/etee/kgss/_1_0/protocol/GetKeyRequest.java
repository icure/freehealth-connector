package be.fgov.ehealth.etee.kgss._1_0.protocol;

import be.fgov.ehealth.commons._1_0.protocol.RequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"sealedKeyRequest"}
)
@XmlRootElement(
   name = "GetKeyRequest"
)
public class GetKeyRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SealedKeyRequest",
      required = true
   )
   protected SealedContentType sealedKeyRequest;

   public SealedContentType getSealedKeyRequest() {
      return this.sealedKeyRequest;
   }

   public void setSealedKeyRequest(SealedContentType value) {
      this.sealedKeyRequest = value;
   }
}
