package be.fgov.ehealth.etee.commons.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EteeErrorType",
   propOrder = {"code", "message", "parameters"}
)
public class EteeErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Message",
      required = true
   )
   protected String message;
   @XmlElement(
      name = "Parameters"
   )
   protected List<EteeErrorParameterType> parameters;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String value) {
      this.message = value;
   }

   public List<EteeErrorParameterType> getParameters() {
      if (this.parameters == null) {
         this.parameters = new ArrayList();
      }

      return this.parameters;
   }
}
