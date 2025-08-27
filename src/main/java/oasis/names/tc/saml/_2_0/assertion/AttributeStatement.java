package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AttributeStatementType",
   propOrder = {"attributesAndEncryptedAttributes"}
)
@XmlRootElement(
   name = "AttributeStatement"
)
public class AttributeStatement extends StatementAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "Attribute",
   type = Attribute.class
), @XmlElement(
   name = "EncryptedAttribute",
   type = EncryptedElementType.class
)})
   protected List<Serializable> attributesAndEncryptedAttributes;

   public List<Serializable> getAttributesAndEncryptedAttributes() {
      if (this.attributesAndEncryptedAttributes == null) {
         this.attributesAndEncryptedAttributes = new ArrayList();
      }

      return this.attributesAndEncryptedAttributes;
   }
}
