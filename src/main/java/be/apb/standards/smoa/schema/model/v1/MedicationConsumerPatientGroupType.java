package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicationConsumerPatientGroupType",
   propOrder = {"abstractCareProvider"}
)
public class MedicationConsumerPatientGroupType extends AbstractMedicationConsumerPatientGroupType {
   @XmlElementRef(
      name = "abstract-CareProvider",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractCareProviderType> abstractCareProvider;

   public JAXBElement<? extends AbstractCareProviderType> getAbstractCareProvider() {
      return this.abstractCareProvider;
   }

   public void setAbstractCareProvider(JAXBElement<? extends AbstractCareProviderType> value) {
      this.abstractCareProvider = value;
   }
}
