package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProxyRestrictionType",
   propOrder = {"audiences"}
)
@XmlRootElement(
   name = "ProxyRestriction"
)
public class ProxyRestriction extends ConditionAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Audience"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> audiences;
   @XmlAttribute(
      name = "Count"
   )
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   protected BigInteger count;

   public List<String> getAudiences() {
      if (this.audiences == null) {
         this.audiences = new ArrayList();
      }

      return this.audiences;
   }

   public BigInteger getCount() {
      return this.count;
   }

   public void setCount(BigInteger value) {
      this.count = value;
   }
}
