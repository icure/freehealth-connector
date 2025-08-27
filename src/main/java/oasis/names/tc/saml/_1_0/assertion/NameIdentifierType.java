package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameIdentifierType",
   propOrder = {"value"}
)
public class NameIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "NameQualifier"
   )
   protected String nameQualifier;
   @XmlAttribute(
      name = "Format"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String format;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getNameQualifier() {
      return this.nameQualifier;
   }

   public void setNameQualifier(String value) {
      this.nameQualifier = value;
   }

   public String getFormat() {
      return this.format;
   }

   public void setFormat(String value) {
      this.format = value;
   }
}
