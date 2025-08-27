package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "MessageType"
)
@XmlEnum
public enum MessageType {
   ERROR,
   WARNING,
   INFO,
   REQUEST,
   UNCLASSIFIED;

   public String value() {
      return this.name();
   }

   public static MessageType fromValue(String v) {
      return valueOf(v);
   }
}
