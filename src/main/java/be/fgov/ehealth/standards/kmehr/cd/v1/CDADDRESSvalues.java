package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ADDRESSvalues"
)
@XmlEnum
public enum CDADDRESSvalues {
   @XmlEnumValue("home")
   HOME("home"),
   @XmlEnumValue("other")
   OTHER("other"),
   @XmlEnumValue("vacation")
   VACATION("vacation"),
   @XmlEnumValue("work")
   WORK("work"),
   @XmlEnumValue("careaddress")
   CAREADDRESS("careaddress");

   private final String value;

   private CDADDRESSvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDADDRESSvalues fromValue(String v) {
      CDADDRESSvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDADDRESSvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
