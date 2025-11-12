package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

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
      for(CDORTHONAVCOMvalues c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
