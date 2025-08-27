package be.fgov.ehealth.rn.registries.commons.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "GivenNameMatchingType"
)
@XmlEnum
public enum GivenNameMatchingType {
   FIRST_LETTER_FIRST_GIVENNAME,
   COMPLETE_FIRST_GIVENNAME,
   ALL_GIVENNAME,
   IGNORE_GIVENNAME;

   public String value() {
      return this.name();
   }

   public static GivenNameMatchingType fromValue(String v) {
      return valueOf(v);
   }
}
