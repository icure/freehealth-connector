package be.cin.nip.async.business;

import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericRequestListType",
   propOrder = {"genericRequests"}
)
@XmlRootElement(
   name = "GenericRequestList"
)
public class GenericRequestList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenericRequest",
      required = true
   )
   protected List<GenericRequest> genericRequests;
   @XmlAttribute(
      name = "timestamp",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestamp;

   public List<GenericRequest> getGenericRequests() {
      if (this.genericRequests == null) {
         this.genericRequests = new ArrayList();
      }

      return this.genericRequests;
   }

   public DateTime getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(DateTime value) {
      this.timestamp = value;
   }
}
