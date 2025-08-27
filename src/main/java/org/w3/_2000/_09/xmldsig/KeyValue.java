package org.w3._2000._09.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KeyValueType",
   propOrder = {"content"}
)
@XmlRootElement(
   name = "KeyValue"
)
public class KeyValue implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRefs({@XmlElementRef(
   name = "RSAKeyValue",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = RSAKeyValue.class
), @XmlElementRef(
   name = "DSAKeyValue",
   namespace = "http://www.w3.org/2000/09/xmldsig#",
   type = DSAKeyValue.class
)})
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   protected List<java.lang.Object> content;

   public List<java.lang.Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }
}
