package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TELECOMvalues"
)
@XmlEnum
public enum CDTELECOMvalues {
   @XmlEnumValue("phone")
   PHONE("phone"),
   @XmlEnumValue("mobile")
   MOBILE("mobile"),
   @XmlEnumValue("fax")
   FAX("fax"),
   @XmlEnumValue("email")
   EMAIL("email"),
   @XmlEnumValue("carenet")
   CARENET("carenet");

   private final String value;

   private CDTELECOMvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTELECOMvalues fromValue(String v) {
      CDTELECOMvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTELECOMvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
