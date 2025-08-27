package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TEMPORALITY"
)
@XmlEnum
public enum CDTEMPORALITY {
   @XmlEnumValue("oneshot")
   ONESHOT("oneshot"),
   @XmlEnumValue("acute")
   ACUTE("acute"),
   @XmlEnumValue("chronic")
   CHRONIC("chronic"),
   @XmlEnumValue("reactivation")
   REACTIVATION("reactivation"),
   @XmlEnumValue("remission")
   REMISSION("remission"),
   @XmlEnumValue("subacute")
   SUBACUTE("subacute");

   private final String value;

   private CDTEMPORALITY(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTEMPORALITY fromValue(String v) {
      CDTEMPORALITY[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTEMPORALITY c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
