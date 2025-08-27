package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "AvailabilityStatus"
)
@XmlEnum
public enum AvailabilityStatus {
   ACTIVE,
   ENDED;

   public String value() {
      return this.name();
   }

   public static AvailabilityStatus fromValue(String v) {
      return valueOf(v);
   }
}
