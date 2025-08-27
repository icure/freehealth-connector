package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-NAVCOMvalues"
)
@XmlEnum
public enum CDORTHONAVCOMvalues {
   @XmlEnumValue("navigationcomputerglobal")
   NAVIGATIONCOMPUTERGLOBAL("navigationcomputerglobal"),
   @XmlEnumValue("navigationcomputerstem")
   NAVIGATIONCOMPUTERSTEM("navigationcomputerstem"),
   @XmlEnumValue("navigationcomputercup")
   NAVIGATIONCOMPUTERCUP("navigationcomputercup"),
   @XmlEnumValue("none")
   NONE("none");

   private final String value;

   private CDORTHONAVCOMvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHONAVCOMvalues fromValue(String v) {
      CDORTHONAVCOMvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDORTHONAVCOMvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
