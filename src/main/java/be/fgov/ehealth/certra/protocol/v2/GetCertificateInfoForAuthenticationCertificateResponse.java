package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.CertificateInfoType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetCertificateInfoForAuthenticationCertificateResponseType",
   propOrder = {"certificateInfo"}
)
@XmlRootElement(
   name = "GetCertificateInfoForAuthenticationCertificateResponse"
)
public class GetCertificateInfoForAuthenticationCertificateResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CertificateInfo"
   )
   protected CertificateInfoType certificateInfo;

   public CertificateInfoType getCertificateInfo() {
      return this.certificateInfo;
   }

   public void setCertificateInfo(CertificateInfoType value) {
      this.certificateInfo = value;
   }
}
