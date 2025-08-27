package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-DRUG-CNKschemes"
)
@XmlEnum
public enum CDDRUGCNKschemes {
   @XmlEnumValue("CD-DRUG-CNK")
   CD_DRUG_CNK("CD-DRUG-CNK"),
   @XmlEnumValue("CD-CNK-CLUSTER")
   CD_CNK_CLUSTER("CD-CNK-CLUSTER"),
   @XmlEnumValue("CD-AMP")
   CD_AMP("CD-AMP");

   private final String value;

   private CDDRUGCNKschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDDRUGCNKschemes fromValue(String v) {
      for(CDDRUGCNKschemes c : values()) {
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
