package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PharmaceuticalInformationType",
   propOrder = {"deliveryDate", "encryptedContent", "product"}
)
public class PharmaceuticalInformationType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar deliveryDate;
   protected EncryptedContentType encryptedContent;
   protected MinSetProductType product;

   public XMLGregorianCalendar getDeliveryDate() {
      return this.deliveryDate;
   }

   public void setDeliveryDate(XMLGregorianCalendar value) {
      this.deliveryDate = value;
   }

   public EncryptedContentType getEncryptedContent() {
      return this.encryptedContent;
   }

   public void setEncryptedContent(EncryptedContentType value) {
      this.encryptedContent = value;
   }

   public MinSetProductType getProduct() {
      return this.product;
   }

   public void setProduct(MinSetProductType value) {
      this.product = value;
   }
}
