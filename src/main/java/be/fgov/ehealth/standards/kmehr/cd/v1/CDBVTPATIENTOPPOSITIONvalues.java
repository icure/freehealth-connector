package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BVT-PATIENTOPPOSITIONvalues"
)
@XmlEnum
public enum CDBVTPATIENTOPPOSITIONvalues {
   @XmlEnumValue("nopatientopposition")
   NOPATIENTOPPOSITION("nopatientopposition");

   private final String value;

   private CDBVTPATIENTOPPOSITIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBVTPATIENTOPPOSITIONvalues fromValue(String v) {
      CDBVTPATIENTOPPOSITIONvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDBVTPATIENTOPPOSITIONvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
