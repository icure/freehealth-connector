package be.fgov.ehealth.etkra.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RaResponseType",
   propOrder = {"response", "errors"}
)
@XmlSeeAlso({RevokeResponse.class, RevokeAndActivateResponse.class, ActivateETKResponse.class, ProcessCsrResponse.class, GetValidAuthCertsResponse.class, CompleteEtkRegistrationResponse.class, StartEtkRegistrationResponse.class})
public class RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Response",
      required = true
   )
   protected ResponseType response;
   @XmlElement(
      name = "Error"
   )
   protected List<ErrorType> errors;
   @XmlAttribute(
      name = "Id"
   )
   protected String id;

   public ResponseType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseType value) {
      this.response = value;
   }

   public List<ErrorType> getErrors() {
      if (this.errors == null) {
         this.errors = new ArrayList();
      }

      return this.errors;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
