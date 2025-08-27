package be.fgov.ehealth.idsupport.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.idsupport.core.v2.IdentificationData;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VerifyIdRequestType",
   propOrder = {"legalContext", "identificationData"}
)
@XmlRootElement(
   name = "VerifyIdRequest"
)
public class VerifyIdRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LegalContext",
      namespace = "urn:be:fgov:ehealth:idsupport:core:v2"
   )
   protected String legalContext;
   @XmlElement(
      name = "IdentificationData",
      namespace = "urn:be:fgov:ehealth:idsupport:core:v2",
      required = true
   )
   protected IdentificationData identificationData;

   public String getLegalContext() {
      return this.legalContext;
   }

   public void setLegalContext(String value) {
      this.legalContext = value;
   }

   public IdentificationData getIdentificationData() {
      return this.identificationData;
   }

   public void setIdentificationData(IdentificationData value) {
      this.identificationData = value;
   }
}
