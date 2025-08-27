package be.fgov.ehealth.certra.core.v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "EntityType"
)
@XmlEnum
public enum EntityType {
   @XmlEnumValue("NaturalPerson")
   NATURAL_PERSON("NaturalPerson"),
   @XmlEnumValue("Organization")
   ORGANIZATION("Organization");

   private final String value;

   private EntityType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static EntityType fromValue(String v) {
      EntityType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EntityType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
