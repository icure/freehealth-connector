package be.fgov.ehealth.standards.kmehr.cd.v1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-GRAFTvalues"
)
@XmlEnum
public enum CDORTHOGRAFTvalues {
   @XmlEnumValue("allograft")
   ALLOGRAFT("allograft"),
   @XmlEnumValue("homograft")
   HOMOGRAFT("homograft"),
   @XmlEnumValue("autograft")
   AUTOGRAFT("autograft"),
   @XmlEnumValue("alloandautograft")
   ALLOANDAUTOGRAFT("alloandautograft"),
   @XmlEnumValue("none")
   NONE("none");

   private final String value;

   private CDORTHOGRAFTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHOGRAFTvalues fromValue(String v) {
      CDORTHOGRAFTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDORTHOGRAFTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
