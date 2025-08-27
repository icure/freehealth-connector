package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "referenceType"
)
@XmlEnum
public enum ReferenceType {
   SUBJECT_SSIN,
   VALIDATION_ERROR;

   public String value() {
      return this.name();
   }

   public static ReferenceType fromValue(String v) {
      return valueOf(v);
   }
}
