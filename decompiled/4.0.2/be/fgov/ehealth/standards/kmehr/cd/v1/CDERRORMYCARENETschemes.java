package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ERROR-MYCARENETschemes"
)
@XmlEnum
public enum CDERRORMYCARENETschemes {
   @XmlEnumValue("CD-ERROR")
   CD_ERROR("CD-ERROR"),
   @XmlEnumValue("CD-REFUSAL-MYCARENET")
   CD_REFUSAL_MYCARENET("CD-REFUSAL-MYCARENET");

   private final String value;

   private CDERRORMYCARENETschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDERRORMYCARENETschemes fromValue(String v) {
      for(CDERRORMYCARENETschemes c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
