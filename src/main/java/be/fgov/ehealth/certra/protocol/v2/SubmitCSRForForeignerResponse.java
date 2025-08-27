package be.fgov.ehealth.certra.protocol.v2;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubmitCSRForForeignerResponseType",
   propOrder = {"validationUrl", "expirationDate"}
)
@XmlRootElement(
   name = "SubmitCSRForForeignerResponse"
)
public class SubmitCSRForForeignerResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ValidationUrl"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String validationUrl;
   @XmlElement(
      name = "ExpirationDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime expirationDate;

   public String getValidationUrl() {
      return this.validationUrl;
   }

   public void setValidationUrl(String value) {
      this.validationUrl = value;
   }

   public DateTime getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(DateTime value) {
      this.expirationDate = value;
   }
}
