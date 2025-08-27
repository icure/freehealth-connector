package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-AUTONOMYvalues"
)
@XmlEnum
public enum CDAUTONOMYvalues {
   @XmlEnumValue("homebound")
   HOMEBOUND("homebound");

   private final String value;

   private CDAUTONOMYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDAUTONOMYvalues fromValue(String v) {
      CDAUTONOMYvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDAUTONOMYvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
