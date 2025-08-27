package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UploadFileRequestType",
   propOrder = {"securedUploadFileRequest"}
)
public class UploadFileRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredUploadFileRequest",
      required = true
   )
   protected SecuredContentType securedUploadFileRequest;

   public SecuredContentType getSecuredUploadFileRequest() {
      return this.securedUploadFileRequest;
   }

   public void setSecuredUploadFileRequest(SecuredContentType value) {
      this.securedUploadFileRequest = value;
   }
}
