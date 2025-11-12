package be.fgov.ehealth.etee.ra.csr._1_0.protocol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "UsageType"
)
@XmlEnum
public enum UsageType {
   @XmlEnumValue("Timestamping")
   TIMESTAMPING("Timestamping"),
   @XmlEnumValue("ConsultationRN")
   CONSULTATION_RN("ConsultationRN"),
   @XmlEnumValue("Codage")
   CODAGE("Codage");

   private final String value;

   private UsageType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static UsageType fromValue(String v) {
      for(UsageType c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
