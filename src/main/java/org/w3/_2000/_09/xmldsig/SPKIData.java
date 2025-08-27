package org.w3._2000._09.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SPKIDataType",
   propOrder = {"spkiSexpsAndAnies"}
)
@XmlRootElement(
   name = "SPKIData"
)
public class SPKIData implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRef(
      name = "SPKISexp",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      type = JAXBElement.class
   )
   @XmlAnyElement
   protected List<java.lang.Object> spkiSexpsAndAnies;

   public List<java.lang.Object> getSPKISexpsAndAnies() {
      if (this.spkiSexpsAndAnies == null) {
         this.spkiSexpsAndAnies = new ArrayList();
      }

      return this.spkiSexpsAndAnies;
   }
}
