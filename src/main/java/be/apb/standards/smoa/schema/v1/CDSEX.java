package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-SEX"
)
@XmlEnum
public enum CDSEX {
   CHANGED,
   FEMALE,
   MALE,
   UNKNOWN;

   public String value() {
      return this.name();
   }

   public static CDSEX fromValue(String v) {
      return valueOf(v);
   }
}
