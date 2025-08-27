package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MS-ADAPTATIONvalues"
)
@XmlEnum
public enum CDMSADAPTATIONvalues {
   @XmlEnumValue("nochanges")
   NOCHANGES("nochanges"),
   @XmlEnumValue("medication")
   MEDICATION("medication"),
   @XmlEnumValue("posology")
   POSOLOGY("posology"),
   @XmlEnumValue("treatmentsuspension")
   TREATMENTSUSPENSION("treatmentsuspension");

   private final String value;

   private CDMSADAPTATIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMSADAPTATIONvalues fromValue(String v) {
      CDMSADAPTATIONvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMSADAPTATIONvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
