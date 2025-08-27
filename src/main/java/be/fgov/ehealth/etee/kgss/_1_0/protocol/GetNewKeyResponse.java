package be.fgov.ehealth.etee.kgss._1_0.protocol;

import be.fgov.ehealth.etee.commons._1_0.etee.EteeResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"sealedNewKeyResponse"}
)
@XmlRootElement(
   name = "GetNewKeyResponse"
)
public class GetNewKeyResponse extends EteeResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SealedNewKeyResponse"
   )
   protected SealedContentType sealedNewKeyResponse;

   public SealedContentType getSealedNewKeyResponse() {
      return this.sealedNewKeyResponse;
   }

   public void setSealedNewKeyResponse(SealedContentType value) {
      this.sealedNewKeyResponse = value;
   }
}
