package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MessageType",
   propOrder = {"severity", "reasonCode", "diagnostic", "authorCode", "informations"}
)
@XmlRootElement(
   name = "Message"
)
public class Message implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Severity",
      required = true
   )
   protected String severity;
   @XmlElement(
      name = "ReasonCode",
      required = true
   )
   protected String reasonCode;
   @XmlElement(
      name = "Diagnostic",
      required = true
   )
   protected String diagnostic;
   @XmlElement(
      name = "AuthorCode",
      required = true
   )
   protected String authorCode;
   @XmlElement(
      name = "Information"
   )
   protected List informations;

   public String getSeverity() {
      return this.severity;
   }

   public void setSeverity(String value) {
      this.severity = value;
   }

   public String getReasonCode() {
      return this.reasonCode;
   }

   public void setReasonCode(String value) {
      this.reasonCode = value;
   }

   public String getDiagnostic() {
      return this.diagnostic;
   }

   public void setDiagnostic(String value) {
      this.diagnostic = value;
   }

   public String getAuthorCode() {
      return this.authorCode;
   }

   public void setAuthorCode(String value) {
      this.authorCode = value;
   }

   public List getInformations() {
      if (this.informations == null) {
         this.informations = new ArrayList();
      }

      return this.informations;
   }
}
