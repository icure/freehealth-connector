package be.ehealth.apb.gfddpp.services.tipsystem;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LocalisedStringType",
   namespace = "urn:be:fgov:ehealth:errors:core:v1",
   propOrder = {"value"}
)
public class LocalisedStringType {
   @XmlValue
   protected String value;
   @XmlAttribute(
      namespace = "http://www.w3.org/XML/1998/namespace"
   )
   protected String lang;

   public String getValue() {
      return this.value;
   }

   public void setValue(String var1) {
      this.value = var1;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String var1) {
      this.lang = var1;
   }
}
