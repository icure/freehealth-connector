package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SignatureProductionPlaceType",
   propOrder = {"city", "stateOrProvince", "postalCode", "countryName"}
)
@XmlRootElement(
   name = "SignatureProductionPlace"
)
public class SignatureProductionPlace implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "City"
   )
   protected String city;
   @XmlElement(
      name = "StateOrProvince"
   )
   protected String stateOrProvince;
   @XmlElement(
      name = "PostalCode"
   )
   protected String postalCode;
   @XmlElement(
      name = "CountryName"
   )
   protected String countryName;

   public String getCity() {
      return this.city;
   }

   public void setCity(String value) {
      this.city = value;
   }

   public String getStateOrProvince() {
      return this.stateOrProvince;
   }

   public void setStateOrProvince(String value) {
      this.stateOrProvince = value;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String value) {
      this.postalCode = value;
   }

   public String getCountryName() {
      return this.countryName;
   }

   public void setCountryName(String value) {
      this.countryName = value;
   }
}
