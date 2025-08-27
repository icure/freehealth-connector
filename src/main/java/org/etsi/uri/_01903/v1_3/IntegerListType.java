package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IntegerListType",
   propOrder = {"ints"}
)
public class IntegerListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "int"
   )
   protected List<BigInteger> ints;

   public List<BigInteger> getInts() {
      if (this.ints == null) {
         this.ints = new ArrayList();
      }

      return this.ints;
   }
}
