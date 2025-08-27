package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPrescriptionIdType;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "deferredPrescriptionType",
   propOrder = {"id", "recipeLines", "originalPrescription"}
)
public class DeferredPrescriptionType extends AbstractDeferredPrescriptionType {
   @XmlElement(
      required = true
   )
   protected AbstractPrescriptionIdType id;
   protected RecipeLinesComplexType recipeLines;
   @XmlElement(
      required = true
   )
   protected OriginalPrescription originalPrescription;

   public AbstractPrescriptionIdType getId() {
      return this.id;
   }

   public void setId(AbstractPrescriptionIdType value) {
      this.id = value;
   }

   public RecipeLinesComplexType getRecipeLines() {
      return this.recipeLines;
   }

   public void setRecipeLines(RecipeLinesComplexType value) {
      this.recipeLines = value;
   }

   public OriginalPrescription getOriginalPrescription() {
      return this.originalPrescription;
   }

   public void setOriginalPrescription(OriginalPrescription value) {
      this.originalPrescription = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"abstractPrescription"}
   )
   public static class OriginalPrescription {
      @XmlElementRef(
         name = "abstract-Prescription",
         namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
         type = JAXBElement.class
      )
      protected JAXBElement<? extends AbstractPrescriptionType> abstractPrescription;

      public JAXBElement<? extends AbstractPrescriptionType> getAbstractPrescription() {
         return this.abstractPrescription;
      }

      public void setAbstractPrescription(JAXBElement<? extends AbstractPrescriptionType> value) {
         this.abstractPrescription = value;
      }
   }
}
