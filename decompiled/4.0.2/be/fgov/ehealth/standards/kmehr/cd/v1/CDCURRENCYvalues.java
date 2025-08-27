package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CURRENCYvalues"
)
@XmlEnum
public enum CDCURRENCYvalues {
   @XmlEnumValue("eur")
   EUR("eur");

   private final String value;

   private CDCURRENCYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCURRENCYvalues fromValue(String v) {
      for(CDCURRENCYvalues c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
