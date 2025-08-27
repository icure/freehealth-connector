package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetOpenPrescriptionListRequestType",
   propOrder = {"securedGetOpenPrescriptionListRequest"}
)
public class GetOpenPrescriptionListRequestType extends RequestType {
   @XmlElement(
      name = "SecuredGetOpenPrescriptionListRequest",
      required = true
   )
   protected SecuredContentType securedGetOpenPrescriptionListRequest;

   public SecuredContentType getSecuredGetOpenPrescriptionListRequest() {
      return this.securedGetOpenPrescriptionListRequest;
   }

   public void setSecuredGetOpenPrescriptionListRequest(SecuredContentType value) {
      this.securedGetOpenPrescriptionListRequest = value;
   }
}
