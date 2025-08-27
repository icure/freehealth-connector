package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
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
   name = "SubstituteType",
   propOrder = {"absentFrom", "absentTo"}
)
public class SubstituteType extends BoxIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AbsentFrom",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime absentFrom;
   @XmlElement(
      name = "AbsentTo",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime absentTo;

   public DateTime getAbsentFrom() {
      return this.absentFrom;
   }

   public void setAbsentFrom(DateTime value) {
      this.absentFrom = value;
   }

   public DateTime getAbsentTo() {
      return this.absentTo;
   }

   public void setAbsentTo(DateTime value) {
      this.absentTo = value;
   }
}
