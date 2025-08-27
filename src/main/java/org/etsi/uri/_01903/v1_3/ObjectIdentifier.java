package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ObjectIdentifierType",
   propOrder = {"identifier", "description", "documentationReferences"}
)
@XmlRootElement(
   name = "ObjectIdentifier"
)
public class ObjectIdentifier implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Identifier",
      required = true
   )
   protected IdentifierType identifier;
   @XmlElement(
      name = "Description"
   )
   protected String description;
   @XmlElement(
      name = "DocumentationReferences"
   )
   protected DocumentationReferencesType documentationReferences;

   public IdentifierType getIdentifier() {
      return this.identifier;
   }

   public void setIdentifier(IdentifierType value) {
      this.identifier = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }

   public DocumentationReferencesType getDocumentationReferences() {
      return this.documentationReferences;
   }

   public void setDocumentationReferences(DocumentationReferencesType value) {
      this.documentationReferences = value;
   }
}
