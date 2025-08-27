package be.fgov.ehealth.genericinsurability.core.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "FaultCodeType"
)
@XmlEnum
public enum FaultCodeType {
   INPUT_ERROR,
   NO_IO_ERROR,
   MULTIIO_ERROR;

   public String value() {
      return this.name();
   }

   public static FaultCodeType fromValue(String v) {
      return valueOf(v);
   }
}
