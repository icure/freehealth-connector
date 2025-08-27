package be.ehealth.apb.gfddpp.services.tipsystem;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LocalisedString",
   namespace = "urn:be:fgov:ehealth:commons:core:v1",
   propOrder = {"value"}
)
public class LocalisedString {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Lang"
   )
   protected LangageType lang;

   public String getValue() {
      return this.value;
   }

   public void setValue(String var1) {
      this.value = var1;
   }

   public LangageType getLang() {
      return this.lang;
   }

   public void setLang(LangageType var1) {
      this.lang = var1;
   }
}
