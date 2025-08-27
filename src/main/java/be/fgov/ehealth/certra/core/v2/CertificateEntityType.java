package be.fgov.ehealth.certra.core.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CertificateEntityType",
   propOrder = {"certificateIdentifier"}
)
public class CertificateEntityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CertificateIdentifier",
      required = true
   )
   protected CertificateIdentifierType certificateIdentifier;
   @XmlAttribute(
      name = "EntityType"
   )
   protected EntityType entityType;

   public CertificateIdentifierType getCertificateIdentifier() {
      return this.certificateIdentifier;
   }

   public void setCertificateIdentifier(CertificateIdentifierType value) {
      this.certificateIdentifier = value;
   }

   public EntityType getEntityType() {
      return this.entityType;
   }

   public void setEntityType(EntityType value) {
      this.entityType = value;
   }
}
