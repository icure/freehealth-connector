package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "compoundprescriptionType",
   propOrder = {"compound", "magistraltext", "galenicform", "quantity", "formularyreference", "mixedContent"}
)
public class CompoundprescriptionType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected List<CompoundType> compound;
   protected List<TextType> magistraltext;
   protected List<GalenicformType> galenicform;
   protected List<QuantityType> quantity;
   protected List<FormularyreferenceType> formularyreference;
   @XmlMixed
   protected List<String> mixedContent;
   @XmlAttribute(
      name = "L"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "language"
   )
   protected String l;

   public List<CompoundType> getCompound() {
      if (this.compound == null) {
         this.compound = new ArrayList();
      }

      return this.compound;
   }

   public List<TextType> getMagistraltext() {
      if (this.magistraltext == null) {
         this.magistraltext = new ArrayList();
      }

      return this.magistraltext;
   }

   public List<GalenicformType> getGalenicform() {
      if (this.galenicform == null) {
         this.galenicform = new ArrayList();
      }

      return this.galenicform;
   }

   public List<QuantityType> getQuantity() {
      if (this.quantity == null) {
         this.quantity = new ArrayList();
      }

      return this.quantity;
   }

   public List<FormularyreferenceType> getFormularyreference() {
      if (this.formularyreference == null) {
         this.formularyreference = new ArrayList();
      }

      return this.formularyreference;
   }

   public List<String> getMixedContent() {
      if (this.mixedContent == null) {
         this.mixedContent = new ArrayList();
      }

      return this.mixedContent;
   }

   public String getL() {
      return this.l;
   }

   public void setL(String value) {
      this.l = value;
   }
}
