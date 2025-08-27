package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MAA-RESPONSETYPEvalues"
)
@XmlEnum
public enum CDMAARESPONSETYPEvalues {
   @XmlEnumValue("agreement")
   AGREEMENT("agreement"),
   @XmlEnumValue("refusal")
   REFUSAL("refusal"),
   @XmlEnumValue("intreatment")
   INTREATMENT("intreatment");

   private final String value;

   private CDMAARESPONSETYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMAARESPONSETYPEvalues fromValue(String v) {
      CDMAARESPONSETYPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMAARESPONSETYPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
