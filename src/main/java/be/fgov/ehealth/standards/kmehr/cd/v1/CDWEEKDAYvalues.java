package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-WEEKDAYvalues"
)
@XmlEnum
public enum CDWEEKDAYvalues {
   @XmlEnumValue("sunday")
   SUNDAY("sunday"),
   @XmlEnumValue("monday")
   MONDAY("monday"),
   @XmlEnumValue("tuesday")
   TUESDAY("tuesday"),
   @XmlEnumValue("wednesday")
   WEDNESDAY("wednesday"),
   @XmlEnumValue("thursday")
   THURSDAY("thursday"),
   @XmlEnumValue("friday")
   FRIDAY("friday"),
   @XmlEnumValue("saturday")
   SATURDAY("saturday");

   private final String value;

   private CDWEEKDAYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDWEEKDAYvalues fromValue(String v) {
      CDWEEKDAYvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDWEEKDAYvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
