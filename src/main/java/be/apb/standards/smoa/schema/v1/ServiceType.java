package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "ServiceType"
)
@XmlEnum
public enum ServiceType {
   PCDH,
   TIP,
   CM,
   UNCLASSIFIED;

   public String value() {
      return this.name();
   }

   public static ServiceType fromValue(String v) {
      return valueOf(v);
   }
}
