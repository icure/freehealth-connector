package be.fgov.ehealth.genericinsurability.core.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "InsurabilityRequestTypeType"
)
@XmlEnum
public enum InsurabilityRequestTypeType {
   @XmlEnumValue("information")
   INFORMATION("information"),
   @XmlEnumValue("invoicing")
   INVOICING("invoicing");

   private final String value;

   private InsurabilityRequestTypeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static InsurabilityRequestTypeType fromValue(String v) {
      InsurabilityRequestTypeType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         InsurabilityRequestTypeType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
