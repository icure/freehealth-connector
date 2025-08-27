package be.recipe.services.prescriber;

import org.taktik.connector.business.recipe.utils.CalendarAdapter;

import java.util.Calendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbackItem",
   propOrder = {"rid", "sentBy", "sentDate", "content"}
)
@XmlRootElement(
   name = "listFeedbackItem"
)
public class ListFeedbackItem {
   @XmlElement(
      required = true
   )
   protected String rid;
   @XmlElement(
      required = true
   )
   protected String sentBy;
   @XmlElement(
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(CalendarAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected Calendar sentDate;
   @XmlElement(
      required = true
   )
   protected byte[] content;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public String getSentBy() {
      return this.sentBy;
   }

   public void setSentBy(String value) {
      this.sentBy = value;
   }

   public Calendar getSentDate() {
      return this.sentDate;
   }

   public void setSentDate(Calendar value) {
      this.sentDate = value;
   }

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] value) {
      this.content = value;
   }
}
