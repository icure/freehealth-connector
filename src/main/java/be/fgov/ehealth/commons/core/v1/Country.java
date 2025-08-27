package be.fgov.ehealth.commons.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"code", "abbreviation", "descriptions"}
)
public class Country implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code"
   )
   protected String code;
   @XmlElement(
      name = "Abbreviation"
   )
   protected String abbreviation;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getAbbreviation() {
      return this.abbreviation;
   }

   public void setAbbreviation(String value) {
      this.abbreviation = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
