package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CountryType",
   propOrder = {"isoCode", "nsiCode", "descriptions"}
)
@XmlRootElement(
   name = "Country"
)
public class Country implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ISOCode"
   )
   protected String isoCode;
   @XmlElement(
      name = "NSICode"
   )
   protected String nsiCode;
   @XmlElement(
      name = "Description"
   )
   protected List<Description> descriptions;

   public String getISOCode() {
      return this.isoCode;
   }

   public void setISOCode(String value) {
      this.isoCode = value;
   }

   public String getNSICode() {
      return this.nsiCode;
   }

   public void setNSICode(String value) {
      this.nsiCode = value;
   }

   public List<Description> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
