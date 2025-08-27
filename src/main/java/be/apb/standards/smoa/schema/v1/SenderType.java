package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.AbstractCareServiceType;
import be.apb.standards.smoa.schema.model.v1.AbstractPharmacyType;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SenderType",
   propOrder = {"abstractPharmacy", "abstractCareService"}
)
public class SenderType {
   @XmlElementRef(
      name = "abstract-Pharmacy",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected JAXBElement<? extends AbstractPharmacyType> abstractPharmacy;
   @XmlElementRef(
      name = "abstract-CareService",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class,
      required = false
   )
   protected JAXBElement<? extends AbstractCareServiceType> abstractCareService;

   public JAXBElement<? extends AbstractPharmacyType> getAbstractPharmacy() {
      return this.abstractPharmacy;
   }

   public void setAbstractPharmacy(JAXBElement<? extends AbstractPharmacyType> value) {
      this.abstractPharmacy = value;
   }

   public JAXBElement<? extends AbstractCareServiceType> getAbstractCareService() {
      return this.abstractCareService;
   }

   public void setAbstractCareService(JAXBElement<? extends AbstractCareServiceType> value) {
      this.abstractCareService = value;
   }
}
