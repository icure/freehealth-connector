package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EXTERNALSOURCEschemes"
)
@XmlEnum
public enum CDEXTERNALSOURCEschemes {
   @XmlEnumValue("CD-EXTERNALSOURCE")
   CD_EXTERNALSOURCE("CD-EXTERNALSOURCE"),
   LOCAL("LOCAL");

   private final String value;

   private CDEXTERNALSOURCEschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEXTERNALSOURCEschemes fromValue(String v) {
      CDEXTERNALSOURCEschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDEXTERNALSOURCEschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
