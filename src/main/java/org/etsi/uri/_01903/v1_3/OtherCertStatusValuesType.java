package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OtherCertStatusValuesType",
   propOrder = {"otherValues"}
)
public class OtherCertStatusValuesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OtherValue",
      required = true
   )
   protected List<Any> otherValues;

   public List<Any> getOtherValues() {
      if (this.otherValues == null) {
         this.otherValues = new ArrayList();
      }

      return this.otherValues;
   }
}
