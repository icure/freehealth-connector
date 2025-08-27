package be.fgov.ehealth.bcp.protocol.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "StatusType"
)
@XmlEnum
public enum StatusType {
   ACTIVE,
   INACTIVE;

   public String value() {
      return this.name();
   }

   public static StatusType fromValue(String v) {
      return valueOf(v);
   }
}
