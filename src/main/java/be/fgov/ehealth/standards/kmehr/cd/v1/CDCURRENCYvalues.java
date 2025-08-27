package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CURRENCYvalues"
)
@XmlEnum
public enum CDCURRENCYvalues {
   @XmlEnumValue("eur")
   EUR("eur");

   private final String value;

   private CDCURRENCYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCURRENCYvalues fromValue(String v) {
      CDCURRENCYvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDCURRENCYvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
