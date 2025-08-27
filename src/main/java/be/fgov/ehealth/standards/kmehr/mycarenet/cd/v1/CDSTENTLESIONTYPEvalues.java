package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-STENT-LESIONTYPEvalues"
)
@XmlEnum
public enum CDSTENTLESIONTYPEvalues {
   @XmlEnumValue("simple")
   SIMPLE("simple"),
   @XmlEnumValue("multi-segment")
   MULTI_SEGMENT("multi-segment"),
   @XmlEnumValue("aorto-ostiale")
   AORTO_OSTIALE("aorto-ostiale"),
   @XmlEnumValue("bifurcation")
   BIFURCATION("bifurcation"),
   @XmlEnumValue("occlusionchroniquetotplus3m")
   OCCLUSIONCHRONIQUETOTPLUS_3_M("occlusionchroniquetotplus3m");

   private final String value;

   private CDSTENTLESIONTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSTENTLESIONTYPEvalues fromValue(String v) {
      CDSTENTLESIONTYPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDSTENTLESIONTYPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
