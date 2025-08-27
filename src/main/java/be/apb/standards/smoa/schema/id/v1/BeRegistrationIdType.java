package be.apb.standards.smoa.schema.id.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BeRegistrationIdType",
   propOrder = {"cti"}
)
public class BeRegistrationIdType extends AbstractMedicinalProductIdType {
   protected int cti;

   public int getCti() {
      return this.cti;
   }

   public void setCti(int value) {
      this.cti = value;
   }
}
