package be.fgov.ehealth.hubservices.core.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "StatusType"
)
@XmlEnum
public enum StatusType {
   GIVEN,
   REVOKED,
   DECEASED;

   public String value() {
      return this.name();
   }

   public static StatusType fromValue(String v) {
      return valueOf(v);
   }
}
