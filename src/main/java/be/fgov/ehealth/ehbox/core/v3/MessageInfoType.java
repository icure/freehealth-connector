package be.fgov.ehealth.ehbox.core.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MessageInfoType",
   propOrder = {"publicationDate", "expirationDate", "size"}
)
public class MessageInfoType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PublicationDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime publicationDate;
   @XmlElement(
      name = "ExpirationDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime expirationDate;
   @XmlElement(
      name = "Size",
      required = true
   )
   protected String size;

   public DateTime getPublicationDate() {
      return this.publicationDate;
   }

   public void setPublicationDate(DateTime value) {
      this.publicationDate = value;
   }

   public DateTime getExpirationDate() {
      return this.expirationDate;
   }

   public void setExpirationDate(DateTime value) {
      this.expirationDate = value;
   }

   public String getSize() {
      return this.size;
   }

   public void setSize(String value) {
      this.size = value;
   }
}
