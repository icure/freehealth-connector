package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ACKNOWLEDGMENTvalues"
)
@XmlEnum
public enum CDACKNOWLEDGMENTvalues {
   @XmlEnumValue("always")
   ALWAYS("always"),
   @XmlEnumValue("never")
   NEVER("never"),
   @XmlEnumValue("onerror")
   ONERROR("onerror"),
   @XmlEnumValue("onsuccess")
   ONSUCCESS("onsuccess");

   private final String value;

   private CDACKNOWLEDGMENTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDACKNOWLEDGMENTvalues fromValue(String v) {
      CDACKNOWLEDGMENTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDACKNOWLEDGMENTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
