package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OriginatorType",
   propOrder = {"originatorType", "originatorId", "serviceType"}
)
public class OriginatorType {
   @XmlElement(
      name = "OriginatorType",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String originatorType;
   @XmlElement(
      name = "OriginatorId",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String originatorId;
   @XmlElement(
      name = "ServiceType",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String serviceType;

   public String getOriginatorType() {
      return this.originatorType;
   }

   public void setOriginatorType(String value) {
      this.originatorType = value;
   }

   public String getOriginatorId() {
      return this.originatorId;
   }

   public void setOriginatorId(String value) {
      this.originatorId = value;
   }

   public String getServiceType() {
      return this.serviceType;
   }

   public void setServiceType(String value) {
      this.serviceType = value;
   }
}
