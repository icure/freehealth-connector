package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-STENT-DEVICEvalues"
)
@XmlEnum
public enum CDSTENTDEVICEvalues {
   @XmlEnumValue("bms")
   BMS("bms"),
   @XmlEnumValue("des")
   DES("des"),
   @XmlEnumValue("bvs")
   BVS("bvs"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDSTENTDEVICEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSTENTDEVICEvalues fromValue(String v) {
      CDSTENTDEVICEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDSTENTDEVICEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
