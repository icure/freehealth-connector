package be.fgov.ehealth.hubservices.core.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "TherapeuticLinkOperationType"
)
@XmlEnum
public enum TherapeuticLinkOperationType {
   @XmlEnumValue("declaration")
   DECLARATION("declaration"),
   @XmlEnumValue("revocation")
   REVOCATION("revocation");

   private final String value;

   private TherapeuticLinkOperationType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static TherapeuticLinkOperationType fromValue(String v) {
      TherapeuticLinkOperationType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         TherapeuticLinkOperationType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
