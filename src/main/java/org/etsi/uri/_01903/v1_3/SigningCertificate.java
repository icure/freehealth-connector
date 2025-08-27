package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CertIDListType",
   propOrder = {"certs"}
)
@XmlRootElement(
   name = "SigningCertificate"
)
public class SigningCertificate implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Cert",
      required = true
   )
   protected List<CertIDType> certs;

   public List<CertIDType> getCerts() {
      if (this.certs == null) {
         this.certs = new ArrayList();
      }

      return this.certs;
   }
}
