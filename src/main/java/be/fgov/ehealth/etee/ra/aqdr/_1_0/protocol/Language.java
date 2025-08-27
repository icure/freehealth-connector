package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "Language"
)
@XmlEnum
public enum Language {
   FR,
   NL,
   EN;

   public String value() {
      return this.name();
   }

   public static Language fromValue(String v) {
      return valueOf(v);
   }
}
