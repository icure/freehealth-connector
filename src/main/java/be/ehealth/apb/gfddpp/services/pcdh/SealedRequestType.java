package be.ehealth.apb.gfddpp.services.pcdh;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SealedRequestType",
   propOrder = {"requestParametersSealed", "authorizationParameters"}
)
public class SealedRequestType extends RequestType {
   @XmlElement(
      name = "RequestParametersSealed",
      required = true
   )
   protected byte[] requestParametersSealed;
   @XmlElement(
      name = "AuthorizationParameters"
   )
   protected AuthorizationParametersType authorizationParameters;

   public byte[] getRequestParametersSealed() {
      return this.requestParametersSealed;
   }

   public void setRequestParametersSealed(byte[] var1) {
      this.requestParametersSealed = (byte[])var1;
   }

   public AuthorizationParametersType getAuthorizationParameters() {
      return this.authorizationParameters;
   }

   public void setAuthorizationParameters(AuthorizationParametersType var1) {
      this.authorizationParameters = var1;
   }
}
