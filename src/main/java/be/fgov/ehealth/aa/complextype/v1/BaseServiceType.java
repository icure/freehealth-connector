package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BaseServiceType",
   propOrder = {"value"}
)
public class BaseServiceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "resourceEnumeration",
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      required = true
   )
   protected String resourceEnumeration;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getResourceEnumeration() {
      return this.resourceEnumeration;
   }

   public void setResourceEnumeration(String value) {
      this.resourceEnumeration = value;
   }
}
