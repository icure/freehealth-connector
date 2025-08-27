package be.fgov.ehealth.consultrn._1_0.core;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CountryType",
   propOrder = {"insCode", "descriptions"}
)
@XmlSeeAlso({NationalityType.class})
public class CountryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InsCode"
   )
   @XmlSchemaType(
      name = "integer"
   )
   protected Integer insCode;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;

   public Integer getInsCode() {
      return this.insCode;
   }

   public void setInsCode(Integer value) {
      this.insCode = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
