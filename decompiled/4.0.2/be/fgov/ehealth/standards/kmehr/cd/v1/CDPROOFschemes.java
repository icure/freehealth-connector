package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-PROOFschemes"
)
@XmlEnum
public enum CDPROOFschemes {
   @XmlEnumValue("CD-PROOFTYPE")
   CD_PROOFTYPE("CD-PROOFTYPE"),
   LOCAL("LOCAL");

   private final String value;

   private CDPROOFschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDPROOFschemes fromValue(String v) {
      for(CDPROOFschemes c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
