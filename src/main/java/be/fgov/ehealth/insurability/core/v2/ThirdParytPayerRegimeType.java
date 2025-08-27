package be.fgov.ehealth.insurability.core.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "ThirdParytPayerRegimeType"
)
@XmlEnum
public enum ThirdParytPayerRegimeType {
   @XmlEnumValue("none")
   NONE("none"),
   @XmlEnumValue("standard")
   STANDARD("standard"),
   @XmlEnumValue("elevated")
   ELEVATED("elevated");

   private final String value;

   private ThirdParytPayerRegimeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static ThirdParytPayerRegimeType fromValue(String v) {
      ThirdParytPayerRegimeType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ThirdParytPayerRegimeType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
