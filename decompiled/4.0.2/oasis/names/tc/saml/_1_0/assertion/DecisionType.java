package oasis.names.tc.saml._1_0.assertion;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "DecisionType"
)
@XmlEnum
public enum DecisionType {
   @XmlEnumValue("Permit")
   PERMIT("Permit"),
   @XmlEnumValue("Deny")
   DENY("Deny"),
   @XmlEnumValue("Indeterminate")
   INDETERMINATE("Indeterminate");

   private final String value;

   private DecisionType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static DecisionType fromValue(String v) {
      for(DecisionType c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
