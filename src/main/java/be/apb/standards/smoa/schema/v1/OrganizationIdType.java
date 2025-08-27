package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "OrganizationIdType"
)
@XmlEnum
public enum OrganizationIdType {
   NIHII,
   CBE,
   EHP;

   public String value() {
      return this.name();
   }

   public static OrganizationIdType fromValue(String v) {
      return valueOf(v);
   }
}
