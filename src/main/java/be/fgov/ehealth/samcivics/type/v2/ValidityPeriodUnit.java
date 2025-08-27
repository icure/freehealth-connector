package be.fgov.ehealth.samcivics.type.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "ValidityPeriodUnit"
)
@XmlEnum
public enum ValidityPeriodUnit {
   D,
   W,
   M,
   Y;

   public String value() {
      return this.name();
   }

   public static ValidityPeriodUnit fromValue(String v) {
      return valueOf(v);
   }
}
