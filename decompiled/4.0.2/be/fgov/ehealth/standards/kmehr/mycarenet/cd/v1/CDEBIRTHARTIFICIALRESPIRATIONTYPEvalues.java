package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-ARTIFICIALRESPIRATIONTYPEvalues"
)
@XmlEnum
public enum CDEBIRTHARTIFICIALRESPIRATIONTYPEvalues {
   @XmlEnumValue("intubation")
   INTUBATION("intubation"),
   @XmlEnumValue("balloon-mask")
   BALLOON_MASK("balloon-mask");

   private final String value;

   private CDEBIRTHARTIFICIALRESPIRATIONTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHARTIFICIALRESPIRATIONTYPEvalues fromValue(String v) {
      for(CDEBIRTHARTIFICIALRESPIRATIONTYPEvalues c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
