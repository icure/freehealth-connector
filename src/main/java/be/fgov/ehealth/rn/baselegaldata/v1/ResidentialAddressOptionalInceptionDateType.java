package be.fgov.ehealth.rn.baselegaldata.v1;

import be.fgov.ehealth.rn.commons.business.v1.LocalizedDescriptionType;
import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResidentialAddressOptionalInceptionDateType",
   propOrder = {"countryCode", "countryIsoCode", "countryNames", "regionCode", "regionNames", "cityCode", "cityRegionalCode", "cityNames", "postalCode", "streetCode", "streetRegionalCode", "streetNames", "houseNumber", "boxNumber", "addressRegionalCode", "inceptionDate"}
)
@XmlSeeAlso({ResidentialAddressType.class})
public class ResidentialAddressOptionalInceptionDateType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CountryCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer countryCode;
   @XmlElement(
      name = "CountryIsoCode"
   )
   protected String countryIsoCode;
   @XmlElement(
      name = "CountryName"
   )
   protected List<LocalizedDescriptionType> countryNames;
   @XmlElement(
      name = "RegionCode"
   )
   protected String regionCode;
   @XmlElement(
      name = "RegionName"
   )
   protected List<LocalizedDescriptionType> regionNames;
   @XmlElement(
      name = "CityCode"
   )
   protected String cityCode;
   @XmlElement(
      name = "CityRegionalCode"
   )
   protected BestIdentifierType cityRegionalCode;
   @XmlElement(
      name = "CityName"
   )
   protected List<LocalizedDescriptionType> cityNames;
   @XmlElement(
      name = "PostalCode"
   )
   protected String postalCode;
   @XmlElement(
      name = "StreetCode"
   )
   protected String streetCode;
   @XmlElement(
      name = "StreetRegionalCode"
   )
   protected BestIdentifierType streetRegionalCode;
   @XmlElement(
      name = "StreetName"
   )
   protected List<LocalizedDescriptionType> streetNames;
   @XmlElement(
      name = "HouseNumber"
   )
   protected String houseNumber;
   @XmlElement(
      name = "BoxNumber"
   )
   protected String boxNumber;
   @XmlElement(
      name = "AddressRegionalCode"
   )
   protected BestIdentifierType addressRegionalCode;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public Integer getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(Integer value) {
      this.countryCode = value;
   }

   public String getCountryIsoCode() {
      return this.countryIsoCode;
   }

   public void setCountryIsoCode(String value) {
      this.countryIsoCode = value;
   }

   public List<LocalizedDescriptionType> getCountryNames() {
      if (this.countryNames == null) {
         this.countryNames = new ArrayList();
      }

      return this.countryNames;
   }

   public String getRegionCode() {
      return this.regionCode;
   }

   public void setRegionCode(String value) {
      this.regionCode = value;
   }

   public List<LocalizedDescriptionType> getRegionNames() {
      if (this.regionNames == null) {
         this.regionNames = new ArrayList();
      }

      return this.regionNames;
   }

   public String getCityCode() {
      return this.cityCode;
   }

   public void setCityCode(String value) {
      this.cityCode = value;
   }

   public BestIdentifierType getCityRegionalCode() {
      return this.cityRegionalCode;
   }

   public void setCityRegionalCode(BestIdentifierType value) {
      this.cityRegionalCode = value;
   }

   public List<LocalizedDescriptionType> getCityNames() {
      if (this.cityNames == null) {
         this.cityNames = new ArrayList();
      }

      return this.cityNames;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String value) {
      this.postalCode = value;
   }

   public String getStreetCode() {
      return this.streetCode;
   }

   public void setStreetCode(String value) {
      this.streetCode = value;
   }

   public BestIdentifierType getStreetRegionalCode() {
      return this.streetRegionalCode;
   }

   public void setStreetRegionalCode(BestIdentifierType value) {
      this.streetRegionalCode = value;
   }

   public List<LocalizedDescriptionType> getStreetNames() {
      if (this.streetNames == null) {
         this.streetNames = new ArrayList();
      }

      return this.streetNames;
   }

   public String getHouseNumber() {
      return this.houseNumber;
   }

   public void setHouseNumber(String value) {
      this.houseNumber = value;
   }

   public String getBoxNumber() {
      return this.boxNumber;
   }

   public void setBoxNumber(String value) {
      this.boxNumber = value;
   }

   public BestIdentifierType getAddressRegionalCode() {
      return this.addressRegionalCode;
   }

   public void setAddressRegionalCode(BestIdentifierType value) {
      this.addressRegionalCode = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
