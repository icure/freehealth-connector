package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PlainAddressType",
   propOrder = {"address", "country"}
)
public class PlainAddressType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Address"
   )
   protected String address;
   @XmlElement(
      name = "Country",
      required = true
   )
   protected CountryType country;

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String value) {
      this.address = value;
   }

   public CountryType getCountry() {
      return this.country;
   }

   public void setCountry(CountryType value) {
      this.country = value;
   }
}
