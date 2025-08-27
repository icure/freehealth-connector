package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import be.fgov.ehealth.commons.protocol.v2.PaginationRequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListConsultationRequestType"
)
public class ListConsultationRequestType extends PaginationRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Delta"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime delta;

   public DateTime getDelta() {
      return this.delta;
   }

   public void setDelta(DateTime value) {
      this.delta = value;
   }
}
