package be.fgov.ehealth.etee.commons.core.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "ETKUsageType"
)
@XmlEnum
public enum ETKUsageType {
   TS,
   COD,
   CRN,
   OTH;

   public String value() {
      return this.name();
   }

   public static ETKUsageType fromValue(String v) {
      return valueOf(v);
   }
}
