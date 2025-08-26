package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ToiletsFrequencyType"
)
@XmlEnum
public enum ToiletsFrequencyType {
   @XmlEnumValue("day")
   DAY("day"),
   @XmlEnumValue("week")
   WEEK("week");

   private final String value;

   private ToiletsFrequencyType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static ToiletsFrequencyType fromValue(String v) {
      for(ToiletsFrequencyType c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
