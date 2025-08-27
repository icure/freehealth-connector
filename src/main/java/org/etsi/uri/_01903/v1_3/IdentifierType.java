package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentifierType",
   propOrder = {"value"}
)
public class IdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String value;
   @XmlAttribute(
      name = "Qualifier"
   )
   protected QualifierType qualifier;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public QualifierType getQualifier() {
      return this.qualifier;
   }

   public void setQualifier(QualifierType value) {
      this.qualifier = value;
   }
}
