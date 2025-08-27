package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultStandardRouteType",
   propOrder = {"name", "definition", "url"}
)
@XmlSeeAlso({ConsultStandardRouteAndRouteOfAdministrationType.class})
public class ConsultStandardRouteType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name"
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "Definition"
   )
   protected ConsultTextType definition;
   @XmlElement(
      name = "Url"
   )
   protected String url;
   @XmlAttribute(
      name = "Standard",
      required = true
   )
   protected String standard;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public ConsultTextType getDefinition() {
      return this.definition;
   }

   public void setDefinition(ConsultTextType value) {
      this.definition = value;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String value) {
      this.url = value;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String value) {
      this.standard = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
