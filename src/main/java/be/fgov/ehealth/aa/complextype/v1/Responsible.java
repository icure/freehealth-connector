package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponsibleType",
   propOrder = {"responsibleFunction"}
)
@XmlRootElement(
   name = "Responsible"
)
public class Responsible extends PersonType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ResponsibleFunction",
      required = true
   )
   protected String responsibleFunction;

   public String getResponsibleFunction() {
      return this.responsibleFunction;
   }

   public void setResponsibleFunction(String value) {
      this.responsibleFunction = value;
   }
}
