package be.apb.standards.smoa.schema.id.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CnkIdType",
   propOrder = {"cnk"}
)
public class CnkIdType extends AbstractMedicinalProductIdType {
   @XmlElement(
      required = true
   )
   protected String cnk;

   public String getCnk() {
      return this.cnk;
   }

   public void setCnk(String value) {
      this.cnk = value;
   }
}
