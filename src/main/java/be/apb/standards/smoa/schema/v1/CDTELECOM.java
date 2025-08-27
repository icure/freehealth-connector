package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TELECOM"
)
@XmlEnum
public enum CDTELECOM {
   FAX,
   EMAIL,
   MOBILE,
   PHONE;

   public String value() {
      return this.name();
   }

   public static CDTELECOM fromValue(String v) {
      return valueOf(v);
   }
}
