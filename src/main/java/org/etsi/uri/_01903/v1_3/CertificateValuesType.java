package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CertificateValuesType",
   propOrder = {"encapsulatedX509CertificatesAndOtherCertificates"}
)
public class CertificateValuesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "EncapsulatedX509Certificate",
   type = EncapsulatedPKIData.class
), @XmlElement(
   name = "OtherCertificate",
   type = Any.class
)})
   protected List<Serializable> encapsulatedX509CertificatesAndOtherCertificates;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public List<Serializable> getEncapsulatedX509CertificatesAndOtherCertificates() {
      if (this.encapsulatedX509CertificatesAndOtherCertificates == null) {
         this.encapsulatedX509CertificatesAndOtherCertificates = new ArrayList();
      }

      return this.encapsulatedX509CertificatesAndOtherCertificates;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
