package org.etsi.uri._01903.v1_3;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OCSPIdentifierType",
   propOrder = {"responderID", "producedAt"}
)
public class OCSPIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ResponderID",
      required = true
   )
   protected ResponderIDType responderID;
   @XmlElement(
      name = "ProducedAt",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime producedAt;
   @XmlAttribute(
      name = "URI"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String uri;

   public ResponderIDType getResponderID() {
      return this.responderID;
   }

   public void setResponderID(ResponderIDType value) {
      this.responderID = value;
   }

   public DateTime getProducedAt() {
      return this.producedAt;
   }

   public void setProducedAt(DateTime value) {
      this.producedAt = value;
   }

   public String getURI() {
      return this.uri;
   }

   public void setURI(String value) {
      this.uri = value;
   }
}
