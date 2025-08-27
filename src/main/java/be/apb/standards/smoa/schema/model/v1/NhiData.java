package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NhiData",
   propOrder = {"identification", "insuredStatus"}
)
public class NhiData {
   protected IdentificationBvacNhid identification;
   protected InsuredStatus insuredStatus;

   public IdentificationBvacNhid getIdentification() {
      return this.identification;
   }

   public void setIdentification(IdentificationBvacNhid value) {
      this.identification = value;
   }

   public InsuredStatus getInsuredStatus() {
      return this.insuredStatus;
   }

   public void setInsuredStatus(InsuredStatus value) {
      this.insuredStatus = value;
   }
}
