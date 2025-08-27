package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DocumentationReferencesType",
   propOrder = {"documentationReferences"}
)
public class DocumentationReferencesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DocumentationReference",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> documentationReferences;

   public List<String> getDocumentationReferences() {
      if (this.documentationReferences == null) {
         this.documentationReferences = new ArrayList();
      }

      return this.documentationReferences;
   }
}
