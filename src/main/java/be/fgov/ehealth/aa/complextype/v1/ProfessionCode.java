package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProfessionCodeType",
   propOrder = {"value"}
)
@XmlRootElement(
   name = "ProfessionCode"
)
public class ProfessionCode implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "authenticSource",
      required = true
   )
   protected String authenticSource;
   @XmlAttribute(
      name = "type",
      required = true
   )
   protected String type;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getAuthenticSource() {
      return this.authenticSource;
   }

   public void setAuthenticSource(String value) {
      this.authenticSource = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }
}
