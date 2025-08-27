package org.oclc.purl.dsdl.svrl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"text"}
)
@XmlRootElement(
   name = "diagnostic-reference"
)
public class DiagnosticReference {
   @XmlElement(
      required = true
   )
   protected String text;
   @XmlAttribute(
      name = "diagnostic",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String diagnostic;

   public DiagnosticReference() {
   }

   public String getText() {
      return this.text;
   }

   public void setText(String value) {
      this.text = value;
   }

   public String getDiagnostic() {
      return this.diagnostic;
   }

   public void setDiagnostic(String value) {
      this.diagnostic = value;
   }
}
