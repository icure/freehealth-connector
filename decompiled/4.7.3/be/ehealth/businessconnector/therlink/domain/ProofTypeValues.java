package be.ehealth.businessconnector.therlink.domain;

public enum ProofTypeValues {
   EIDREADING("eidreading"),
   ISIREADING("isireading"),
   EIDSIGNING("eidsigning");

   private String value;

   private ProofTypeValues(String value) {
      this.value = value;
   }

   public String getValue() {
      return this.value;
   }
}
