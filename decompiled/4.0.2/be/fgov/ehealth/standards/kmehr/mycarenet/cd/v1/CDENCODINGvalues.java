package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ENCODINGvalues"
)
@XmlEnum
public enum CDENCODINGvalues {
   @XmlEnumValue("B64")
   B_64("B64"),
   TXT("TXT");

   private final String value;

   private CDENCODINGvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDENCODINGvalues fromValue(String v) {
      for(CDENCODINGvalues c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
