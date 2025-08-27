package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-PATIENTWILLvalues"
)
@XmlEnum
public enum CDPATIENTWILLvalues {
   @XmlEnumValue("ntbr")
   NTBR("ntbr"),
   @XmlEnumValue("bloodtransfusionrefusal")
   BLOODTRANSFUSIONREFUSAL("bloodtransfusionrefusal"),
   @XmlEnumValue("intubationrefusal")
   INTUBATIONREFUSAL("intubationrefusal"),
   @XmlEnumValue("euthanasiarequest")
   EUTHANASIAREQUEST("euthanasiarequest"),
   @XmlEnumValue("vaccinationrefusal")
   VACCINATIONREFUSAL("vaccinationrefusal"),
   @XmlEnumValue("organdonationconsent")
   ORGANDONATIONCONSENT("organdonationconsent"),
   @XmlEnumValue("datareuseforclinicalresearchconsent")
   DATAREUSEFORCLINICALRESEARCHCONSENT("datareuseforclinicalresearchconsent"),
   @XmlEnumValue("datareuseforclinicaltrialsconsent")
   DATAREUSEFORCLINICALTRIALSCONSENT("datareuseforclinicaltrialsconsent"),
   @XmlEnumValue("clinicaltrialparticipationconsent")
   CLINICALTRIALPARTICIPATIONCONSENT("clinicaltrialparticipationconsent"),
   @XmlEnumValue("omissionofmedicaldata")
   OMISSIONOFMEDICALDATA("omissionofmedicaldata");

   private final String value;

   private CDPATIENTWILLvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDPATIENTWILLvalues fromValue(String v) {
      CDPATIENTWILLvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDPATIENTWILLvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
