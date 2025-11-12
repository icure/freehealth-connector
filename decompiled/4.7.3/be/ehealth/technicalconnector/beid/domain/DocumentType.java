package be.ehealth.technicalconnector.beid.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum DocumentType implements Serializable {
   BELGIAN_CITIZEN(new String[]{"1"}),
   KIDS_CARD(new String[]{"6"}),
   BOOTSTRAP_CARD(new String[]{"7"}),
   HABILITATION_CARD(new String[]{"8"}),
   FOREIGNER_A(new String[]{"11", "33"}),
   FOREIGNER_B(new String[]{"12"}),
   FOREIGNER_C(new String[]{"13"}),
   FOREIGNER_D(new String[]{"14"}),
   FOREIGNER_E(new String[]{"15"}),
   FOREIGNER_E_PLUS(new String[]{"16"}),
   FOREIGNER_F(new String[]{"17"}),
   FOREIGNER_F_PLUS(new String[]{"18"}),
   EUROPEAN_BLUE_CARD_H(new String[]{"19"}),
   FOREIGNER_I(new String[]{"20"}),
   FOREIGNER_J(new String[]{"21"}),
   FOREIGNER_M(new String[]{"22"}),
   FOREIGNER_N(new String[]{"23"}),
   FOREIGNER_K(new String[]{"27"}),
   FOREIGNER_L(new String[]{"28"}),
   FOREIGNER_EU(new String[]{"31"}),
   FOREIGNER_EU_PLUS(new String[]{"32"});

   private final Set<Integer> keys = new HashSet();
   private static Map<Integer, DocumentType> documentTypes = new HashMap();

   private DocumentType(String... valueList) {
      for(String value : valueList) {
         this.keys.add(toKey(value));
      }

   }

   private static int toKey(String value) {
      char c1 = value.charAt(0);
      int key1 = c1 - 48;
      if (2 == value.length()) {
         key1 *= 10;
         char c2 = value.charAt(1);
         key1 += c2 - 48;
      }

      return key1;
   }

   private static int toKey(byte[] value) {
      int key = value[0] - 48;
      if (2 == value.length) {
         key *= 10;
         key += value[1] - 48;
      }

      return key;
   }

   public Set<Integer> getKeys() {
      return this.keys;
   }

   public static DocumentType toDocumentType(byte[] value) {
      int key = toKey(value);
      return (DocumentType)documentTypes.get(key);
   }

   public static String toString(byte[] documentTypeValue) {
      return Integer.toString(toKey(documentTypeValue));
   }

   static {
      for(DocumentType documentType : values()) {
         for(Integer key : documentType.keys) {
            if (documentTypes.containsKey(key)) {
               throw new RuntimeException("duplicate document type enum: " + key);
            }

            documentTypes.put(key, documentType);
         }
      }

   }
}
