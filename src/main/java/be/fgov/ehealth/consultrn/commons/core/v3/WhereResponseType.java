package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "WhereResponseType",
   propOrder = {"countryCode", "countryNames", "cityCode", "cityNames"}
)
@XmlSeeAlso({ResidentialAddressResponseType.class})
public class WhereResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryCode"
   )
   protected String countryCode;
   @XmlElement(
      name = "CountryName"
   )
   protected List<NameType> countryNames;
   @XmlElement(
      name = "CityCode"
   )
   protected String cityCode;
   @XmlElement(
      name = "CityName"
   )
   protected List<NameType> cityNames;

   public String getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(String value) {
      this.countryCode = value;
   }

   public List<NameType> getCountryNames() {
      if (this.countryNames == null) {
         this.countryNames = new ArrayList();
      }

      return this.countryNames;
   }

   public String getCityCode() {
      return this.cityCode;
   }

   public void setCityCode(String value) {
      this.cityCode = value;
   }

   public List<NameType> getCityNames() {
      if (this.cityNames == null) {
         this.cityNames = new ArrayList();
      }

      return this.cityNames;
   }
}
