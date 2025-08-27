package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"identifier", "value"}
)
@XmlRootElement(
   name = "Property"
)
public class Property implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Identifier",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String identifier;
   @XmlElement(
      name = "Value"
   )
   protected AnyType value;

   public String getIdentifier() {
      return this.identifier;
   }

   public void setIdentifier(String value) {
      this.identifier = value;
   }

   public AnyType getValue() {
      return this.value;
   }

   public void setValue(AnyType value) {
      this.value = value;
   }
}
