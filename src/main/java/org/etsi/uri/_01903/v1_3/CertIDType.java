package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.X509IssuerSerialType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CertIDType",
   propOrder = {"certDigest", "issuerSerial"}
)
public class CertIDType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CertDigest",
      required = true
   )
   protected DigestAlgAndValueType certDigest;
   @XmlElement(
      name = "IssuerSerial",
      required = true
   )
   protected X509IssuerSerialType issuerSerial;
   @XmlAttribute(
      name = "URI"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String uri;

   public DigestAlgAndValueType getCertDigest() {
      return this.certDigest;
   }

   public void setCertDigest(DigestAlgAndValueType value) {
      this.certDigest = value;
   }

   public X509IssuerSerialType getIssuerSerial() {
      return this.issuerSerial;
   }

   public void setIssuerSerial(X509IssuerSerialType value) {
      this.issuerSerial = value;
   }

   public String getURI() {
      return this.uri;
   }

   public void setURI(String value) {
      this.uri = value;
   }
}
