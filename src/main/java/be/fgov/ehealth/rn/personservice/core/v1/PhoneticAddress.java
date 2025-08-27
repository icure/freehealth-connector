package be.fgov.ehealth.rn.personservice.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticAddress",
   propOrder = {"countryCode", "cityCode"}
)
public class PhoneticAddress implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected int countryCode;
   @XmlElement(
      name = "CityCode"
   )
   protected String cityCode;

   public int getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(int value) {
      this.countryCode = value;
   }

   public String getCityCode() {
      return this.cityCode;
   }

   public void setCityCode(String value) {
      this.cityCode = value;
   }
}
