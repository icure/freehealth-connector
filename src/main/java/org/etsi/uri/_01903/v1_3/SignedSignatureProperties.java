package org.etsi.uri._01903.v1_3;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SignedSignaturePropertiesType",
   propOrder = {"signingTime", "signingCertificate", "signaturePolicyIdentifier", "signatureProductionPlace", "signerRole"}
)
@XmlRootElement(
   name = "SignedSignatureProperties"
)
public class SignedSignatureProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SigningTime",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime signingTime;
   @XmlElement(
      name = "SigningCertificate"
   )
   protected SigningCertificate signingCertificate;
   @XmlElement(
      name = "SignaturePolicyIdentifier"
   )
   protected SignaturePolicyIdentifier signaturePolicyIdentifier;
   @XmlElement(
      name = "SignatureProductionPlace"
   )
   protected SignatureProductionPlace signatureProductionPlace;
   @XmlElement(
      name = "SignerRole"
   )
   protected SignerRole signerRole;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public DateTime getSigningTime() {
      return this.signingTime;
   }

   public void setSigningTime(DateTime value) {
      this.signingTime = value;
   }

   public SigningCertificate getSigningCertificate() {
      return this.signingCertificate;
   }

   public void setSigningCertificate(SigningCertificate value) {
      this.signingCertificate = value;
   }

   public SignaturePolicyIdentifier getSignaturePolicyIdentifier() {
      return this.signaturePolicyIdentifier;
   }

   public void setSignaturePolicyIdentifier(SignaturePolicyIdentifier value) {
      this.signaturePolicyIdentifier = value;
   }

   public SignatureProductionPlace getSignatureProductionPlace() {
      return this.signatureProductionPlace;
   }

   public void setSignatureProductionPlace(SignatureProductionPlace value) {
      this.signatureProductionPlace = value;
   }

   public SignerRole getSignerRole() {
      return this.signerRole;
   }

   public void setSignerRole(SignerRole value) {
      this.signerRole = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
