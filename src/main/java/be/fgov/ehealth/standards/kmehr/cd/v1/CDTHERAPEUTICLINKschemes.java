package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-THERAPEUTICLINKschemes"
)
@XmlEnum
public enum CDTHERAPEUTICLINKschemes {
   @XmlEnumValue("CD-THERAPEUTICLINKTYPE")
   CD_THERAPEUTICLINKTYPE("CD-THERAPEUTICLINKTYPE"),
   LOCAL("LOCAL");

   private final String value;

   private CDTHERAPEUTICLINKschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTHERAPEUTICLINKschemes fromValue(String v) {
      CDTHERAPEUTICLINKschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTHERAPEUTICLINKschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
