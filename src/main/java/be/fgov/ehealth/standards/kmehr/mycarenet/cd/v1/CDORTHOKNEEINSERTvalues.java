package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-KNEE-INSERTvalues"
)
@XmlEnum
public enum CDORTHOKNEEINSERTvalues {
   @XmlEnumValue("fixed")
   FIXED("fixed"),
   @XmlEnumValue("mobile")
   MOBILE("mobile"),
   @XmlEnumValue("none")
   NONE("none");

   private final String value;

   private CDORTHOKNEEINSERTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHOKNEEINSERTvalues fromValue(String v) {
      CDORTHOKNEEINSERTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDORTHOKNEEINSERTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
