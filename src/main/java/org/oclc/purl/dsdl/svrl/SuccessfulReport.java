package org.oclc.purl.dsdl.svrl;

import java.util.ArrayList;
import java.util.List;
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"diagnosticReference", "text"}
)
@XmlRootElement(
   name = "successful-report"
)
public class SuccessfulReport {
   @XmlElement(
      name = "diagnostic-reference"
   )
   protected List<DiagnosticReference> diagnosticReference;
   @XmlElement(
      required = true
   )
   protected String text;
   @XmlAttribute(
      name = "id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "location",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String location;
   @XmlAttribute(
      name = "test",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String test;
   @XmlAttribute(
      name = "role"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String role;
   @XmlAttribute(
      name = "flag"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String flag;

   public SuccessfulReport() {
   }

   public List<DiagnosticReference> getDiagnosticReference() {
      if (this.diagnosticReference == null) {
         this.diagnosticReference = new ArrayList();
      }

      return this.diagnosticReference;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String value) {
      this.text = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getLocation() {
      return this.location;
   }

   public void setLocation(String value) {
      this.location = value;
   }

   public String getTest() {
      return this.test;
   }

   public void setTest(String value) {
      this.test = value;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String value) {
      this.role = value;
   }

   public String getFlag() {
      return this.flag;
   }

   public void setFlag(String value) {
      this.flag = value;
   }
}
