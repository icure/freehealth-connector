package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "PersonRoleType"
)
@XmlEnum
public enum PersonRoleType {
   PHYSICIAN,
   PHARMACIST,
   NURSE;

   public String value() {
      return this.name();
   }

   public static PersonRoleType fromValue(String v) {
      return valueOf(v);
   }
}
