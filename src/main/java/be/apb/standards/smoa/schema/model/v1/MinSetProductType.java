package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MinSetProductType",
   propOrder = {"description", "dispensation"}
)
@XmlSeeAlso({MaxSetProductType.class, HistoryProductType.class})
public class MinSetProductType {
   @XmlElement(
      required = true
   )
   protected Description description;
   @XmlElement(
      required = true
   )
   protected Dispensation dispensation;

   public Description getDescription() {
      return this.description;
   }

   public void setDescription(Description value) {
      this.description = value;
   }

   public Dispensation getDispensation() {
      return this.dispensation;
   }

   public void setDispensation(Dispensation value) {
      this.dispensation = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"numberOfUnits", "administrationInstructions"}
   )
   public static class Dispensation {
      protected Integer numberOfUnits;
      protected AdministrationType administrationInstructions;

      public Integer getNumberOfUnits() {
         return this.numberOfUnits;
      }

      public void setNumberOfUnits(Integer value) {
         this.numberOfUnits = value;
      }

      public AdministrationType getAdministrationInstructions() {
         return this.administrationInstructions;
      }

      public void setAdministrationInstructions(AdministrationType value) {
         this.administrationInstructions = value;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"productCode", "magistralPreparation", "unregisteredProduct"}
   )
   public static class Description {
      protected AbstractMedicinalProductIdType productCode;
      protected MagistralPreparationType magistralPreparation;
      protected String unregisteredProduct;

      public AbstractMedicinalProductIdType getProductCode() {
         return this.productCode;
      }

      public void setProductCode(AbstractMedicinalProductIdType value) {
         this.productCode = value;
      }

      public MagistralPreparationType getMagistralPreparation() {
         return this.magistralPreparation;
      }

      public void setMagistralPreparation(MagistralPreparationType value) {
         this.magistralPreparation = value;
      }

      public String getUnregisteredProduct() {
         return this.unregisteredProduct;
      }

      public void setUnregisteredProduct(String value) {
         this.unregisteredProduct = value;
      }
   }
}
