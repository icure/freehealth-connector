package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "OxygenTherapies"
)
@XmlEnum
public enum OxygenTherapies {
   GAS,
   LIQUID,
   OXYGENCONCENTRATOR;

   public String value() {
      return this.name();
   }

   public static OxygenTherapies fromValue(String v) {
      return valueOf(v);
   }
}
