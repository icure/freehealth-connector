package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BusinessAnomalyType",
   propOrder = {"code", "severity", "description"}
)
public class BusinessAnomalyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Severity",
      required = true
   )
   protected String severity;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected Object description;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getSeverity() {
      return this.severity;
   }

   public void setSeverity(String value) {
      this.severity = value;
   }

   public Object getDescription() {
      return this.description;
   }

   public void setDescription(Object value) {
      this.description = value;
   }
}
