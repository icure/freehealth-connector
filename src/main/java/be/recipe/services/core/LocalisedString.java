package be.recipe.services.core;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LocalisedString",
   propOrder = {"value"}
)
public class LocalisedString {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "lang"
   )
   protected LangageType lang;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public LangageType getLang() {
      return this.lang;
   }

   public void setLang(LangageType value) {
      this.lang = value;
   }
}
