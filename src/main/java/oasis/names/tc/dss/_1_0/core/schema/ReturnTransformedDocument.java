package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = ""
)
@XmlRootElement(
   name = "ReturnTransformedDocument"
)
public class ReturnTransformedDocument implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "WhichReference",
      required = true
   )
   protected BigInteger whichReference;

   public BigInteger getWhichReference() {
      return this.whichReference;
   }

   public void setWhichReference(BigInteger value) {
      this.whichReference = value;
   }
}
