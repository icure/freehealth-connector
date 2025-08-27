package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractMedicationSchemeResponse",
   propOrder = {"clientMessageId", "serverMessageId", "status"}
)
@XmlSeeAlso({MedicationSchemeTimestampsResponse.class, MedicationSchemeEntriesResponse.class})
public class AbstractMedicationSchemeResponse extends AbstractEventType {
   protected String clientMessageId;
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String serverMessageId;
   @XmlElement(
      required = true
   )
   protected Status status;

   public String getClientMessageId() {
      return this.clientMessageId;
   }

   public void setClientMessageId(String value) {
      this.clientMessageId = value;
   }

   public String getServerMessageId() {
      return this.serverMessageId;
   }

   public void setServerMessageId(String value) {
      this.serverMessageId = value;
   }

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status value) {
      this.status = value;
   }
}
