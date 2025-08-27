package be.fgov.ehealth.hubservices.core.v2;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OperationContextType",
   propOrder = {"operation", "recorddatetime", "author", "prooves"}
)
public class OperationContextType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected TherapeuticLinkOperationType operation;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime recorddatetime;
   protected RequestType author;
   @XmlElement(
      name = "proof"
   )
   protected List<ProofType> prooves;

   public TherapeuticLinkOperationType getOperation() {
      return this.operation;
   }

   public void setOperation(TherapeuticLinkOperationType value) {
      this.operation = value;
   }

   public DateTime getRecorddatetime() {
      return this.recorddatetime;
   }

   public void setRecorddatetime(DateTime value) {
      this.recorddatetime = value;
   }

   public RequestType getAuthor() {
      return this.author;
   }

   public void setAuthor(RequestType value) {
      this.author = value;
   }

   public List<ProofType> getProoves() {
      if (this.prooves == null) {
         this.prooves = new ArrayList();
      }

      return this.prooves;
   }
}
