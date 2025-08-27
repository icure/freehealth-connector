package oasis.names.tc.saml._2_0.protocol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "AuthnContextComparisonType"
)
@XmlEnum
public enum AuthnContextComparisonType {
   @XmlEnumValue("exact")
   EXACT("exact"),
   @XmlEnumValue("minimum")
   MINIMUM("minimum"),
   @XmlEnumValue("maximum")
   MAXIMUM("maximum"),
   @XmlEnumValue("better")
   BETTER("better");

   private final String value;

   private AuthnContextComparisonType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static AuthnContextComparisonType fromValue(String v) {
      for(AuthnContextComparisonType c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
