package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "SexType"
)
@XmlEnum
public enum SexType {
   @XmlEnumValue("male")
   MALE("male"),
   @XmlEnumValue("female")
   FEMALE("female");

   private final String value;

   private SexType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static SexType fromValue(String v) {
      for(SexType c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
