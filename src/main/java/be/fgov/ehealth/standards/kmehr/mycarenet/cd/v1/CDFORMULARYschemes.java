package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-FORMULARYschemes"
)
@XmlEnum
public enum CDFORMULARYschemes {
   @XmlEnumValue("CD-FORMULARY")
   CD_FORMULARY("CD-FORMULARY"),
   @XmlEnumValue("CD-FORMULARYREFERENCE")
   CD_FORMULARYREFERENCE("CD-FORMULARYREFERENCE");

   private final String value;

   private CDFORMULARYschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDFORMULARYschemes fromValue(String v) {
      CDFORMULARYschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDFORMULARYschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
