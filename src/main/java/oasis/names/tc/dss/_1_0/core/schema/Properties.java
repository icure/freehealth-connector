package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"signedProperties", "unsignedProperties"}
)
@XmlRootElement(
   name = "Properties"
)
public class Properties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedProperties"
   )
   protected PropertiesType signedProperties;
   @XmlElement(
      name = "UnsignedProperties"
   )
   protected PropertiesType unsignedProperties;

   public PropertiesType getSignedProperties() {
      return this.signedProperties;
   }

   public void setSignedProperties(PropertiesType value) {
      this.signedProperties = value;
   }

   public PropertiesType getUnsignedProperties() {
      return this.unsignedProperties;
   }

   public void setUnsignedProperties(PropertiesType value) {
      this.unsignedProperties = value;
   }
}
