package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttributeType",
   propOrder = {"attributeValues"}
)
@XmlRootElement(
   name = "Attribute"
)
public class Attribute extends AttributeDesignatorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AttributeValue",
      required = true
   )
   protected List<Object> attributeValues;

   public List<Object> getAttributeValues() {
      if (this.attributeValues == null) {
         this.attributeValues = new ArrayList();
      }

      return this.attributeValues;
   }
}
