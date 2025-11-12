package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "MessageNatureType"
)
@XmlEnum
public enum MessageNatureType {
   @XmlEnumValue("visit")
   VISIT("visit"),
   @XmlEnumValue("agreementClosure")
   AGREEMENT_CLOSURE("agreementClosure"),
   @XmlEnumValue("newProvider")
   NEW_PROVIDER("newProvider"),
   @XmlEnumValue("decisionModification")
   DECISION_MODIFICATION("decisionModification");

   private final String value;

   private MessageNatureType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static MessageNatureType fromValue(String v) {
      for(MessageNatureType c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
