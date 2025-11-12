package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BVT-SAMPLETYPEvalues"
)
@XmlEnum
public enum CDBVTSAMPLETYPEvalues {
   @XmlEnumValue("primarytumor")
   PRIMARYTUMOR("primarytumor"),
   @XmlEnumValue("metastasis")
   METASTASIS("metastasis"),
   @XmlEnumValue("normaltissue")
   NORMALTISSUE("normaltissue"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDBVTSAMPLETYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBVTSAMPLETYPEvalues fromValue(String v) {
      for(CDBVTSAMPLETYPEvalues c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
