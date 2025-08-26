package be.fgov.ehealth.standards.kmehr.id.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ID-PROFESSIONschemes"
)
@XmlEnum
public enum IDPROFESSIONschemes {
   @XmlEnumValue("ID-MEDEX")
   ID_MEDEX("ID-MEDEX"),
   @XmlEnumValue("ID-CBE")
   ID_CBE("ID-CBE");

   private final String value;

   private IDPROFESSIONschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static IDPROFESSIONschemes fromValue(String v) {
      for(IDPROFESSIONschemes c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
