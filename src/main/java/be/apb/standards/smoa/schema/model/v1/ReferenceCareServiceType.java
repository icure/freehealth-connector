package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractCareServiceIdType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReferenceCareServiceType",
   propOrder = {"careServiceId"}
)
public class ReferenceCareServiceType extends AbstractCareServiceType {
   @XmlElement(
      required = true
   )
   protected AbstractCareServiceIdType careServiceId;

   public AbstractCareServiceIdType getCareServiceId() {
      return this.careServiceId;
   }

   public void setCareServiceId(AbstractCareServiceIdType value) {
      this.careServiceId = value;
   }
}
