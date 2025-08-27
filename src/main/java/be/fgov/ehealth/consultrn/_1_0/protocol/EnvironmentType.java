package be.fgov.ehealth.consultrn._1_0.protocol;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "EnvironmentType"
)
@XmlEnum
public enum EnvironmentType {
   A,
   P,
   T;

   public String value() {
      return this.name();
   }

   public static EnvironmentType fromValue(String v) {
      return valueOf(v);
   }
}
