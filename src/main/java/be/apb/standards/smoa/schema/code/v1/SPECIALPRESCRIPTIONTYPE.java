package be.apb.standards.smoa.schema.code.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "SPECIALPRESCRIPTIONTYPE"
)
@XmlEnum
public enum SPECIALPRESCRIPTIONTYPE {
   URGENCYTROUSSE,
   CENTRUMFORTOXICOMANEN,
   PRINSLEOPOLDINSTITUUR,
   PATIENTSGROUP,
   PENITENTIARY,
   CENTERFORASYLUMSEEKERS,
   VACCINATIONCAMPAIGNS;

   public String value() {
      return this.name();
   }

   public static SPECIALPRESCRIPTIONTYPE fromValue(String v) {
      return valueOf(v);
   }
}
