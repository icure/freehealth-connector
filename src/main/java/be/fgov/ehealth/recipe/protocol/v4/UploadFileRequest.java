package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UploadFileRequestType",
   propOrder = {"securedUploadFileRequest"}
)
@XmlRootElement(
   name = "UploadFileRequest"
)
public class UploadFileRequest extends RequestType {
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
