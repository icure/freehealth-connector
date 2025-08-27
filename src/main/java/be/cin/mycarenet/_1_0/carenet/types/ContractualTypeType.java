package be.cin.mycarenet._1_0.carenet.types;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(
   name = "ContractualTypeType"
)
@XmlEnum
public enum ContractualTypeType {
   A("A"),
   B("B"),
   C("C"),
   @XmlEnumValue("toilet")
   TOILET("toilet"),
   @XmlEnumValue("nomenclature")
   NOMENCLATURE("nomenclature");

   private final String value;

   private ContractualTypeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static ContractualTypeType fromValue(String v) {
      ContractualTypeType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContractualTypeType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
