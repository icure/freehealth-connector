package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonWithFunctionType",
   propOrder = {"function"}
)
@XmlRootElement(
   name = "PersonWithFunction"
)
public class PersonWithFunction extends PersonType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Function",
      required = true
   )
   protected Object function;

   public Object getFunction() {
      return this.function;
   }

   public void setFunction(Object value) {
      this.function = value;
   }
}
