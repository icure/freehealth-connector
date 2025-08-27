package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "formularyReferenceType",
   propOrder = {"formulaCode", "formulaName", "formulary"}
)
public class FormularyReferenceType {
   @XmlElement(
      required = true
   )
   protected String formulaCode;
   @XmlElement(
      required = true
   )
   protected String formulaName;
   protected String formulary;

   public String getFormulaCode() {
      return this.formulaCode;
   }

   public void setFormulaCode(String value) {
      this.formulaCode = value;
   }

   public String getFormulaName() {
      return this.formulaName;
   }

   public void setFormulaName(String value) {
      this.formulaName = value;
   }

   public String getFormulary() {
      return this.formulary;
   }

   public void setFormulary(String value) {
      this.formulary = value;
   }
}
