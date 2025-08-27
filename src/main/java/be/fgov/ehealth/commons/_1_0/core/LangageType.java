package be.fgov.ehealth.commons._1_0.core;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "LangageType"
)
@XmlEnum
public enum LangageType {
   FR,
   NL,
   EN,
   DE,
   NA;

   public String value() {
      return this.name();
   }

   public static LangageType fromValue(String v) {
      return valueOf(v);
   }
}
