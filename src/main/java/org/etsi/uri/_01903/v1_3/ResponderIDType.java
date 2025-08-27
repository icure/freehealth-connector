package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponderIDType",
   propOrder = {"byKey", "byName"}
)
public class ResponderIDType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ByKey"
   )
   protected byte[] byKey;
   @XmlElement(
      name = "ByName"
   )
   protected String byName;

   public byte[] getByKey() {
      return this.byKey;
   }

   public void setByKey(byte[] value) {
      this.byKey = value;
   }

   public String getByName() {
      return this.byName;
   }

   public void setByName(String value) {
      this.byName = value;
   }
}
