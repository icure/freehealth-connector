package be.fgov.ehealth.rn.registries.commons.v1;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ValidationErrors createValidationErrors() {
      return new ValidationErrors();
   }

   public DetailedStatusType createDetailedStatusType() {
      return new DetailedStatusType();
   }

   public AnomaliesType createAnomaliesType() {
      return new AnomaliesType();
   }
}
