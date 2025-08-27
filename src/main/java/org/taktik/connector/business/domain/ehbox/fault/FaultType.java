package org.taktik.connector.business.domain.ehbox.fault;

import be.cin.types.v1.StringLangType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Fault",
   propOrder = {"faultCode", "faultString", "details"}
)
public class FaultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "faultcode",
      required = true
   )
   protected String faultCode;
   @XmlElement(
      name = "faultstring",
      required = true
   )
   protected String faultString;
   @XmlElement(
      name = "detail",
      required = true
   )
   protected DetailsType details;

   public String getFaultCode() {
      return this.faultCode;
   }

   public void setFaultCode(String value) {
      this.faultCode = value;
   }

   public String getFaultString() {
      return this.faultString;
   }

   public void setFaultString(String value) {
      this.faultString = value;
   }

   public DetailsType getDetails() {
      return this.details;
   }

   public void setDetails(DetailsType value) {
      this.details = value;
   }
}
