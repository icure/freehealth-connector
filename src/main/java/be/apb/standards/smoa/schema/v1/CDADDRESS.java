package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ADDRESS"
)
@XmlEnum
public enum CDADDRESS {
   HOME,
   WORK,
   VACATION,
   OTHER;

   public String value() {
      return this.name();
   }

   public static CDADDRESS fromValue(String v) {
      return valueOf(v);
   }
}
