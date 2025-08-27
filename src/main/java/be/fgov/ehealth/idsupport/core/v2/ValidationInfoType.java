package be.fgov.ehealth.idsupport.core.v2;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ValidationInfoType",
   propOrder = {"value"}
)
public class ValidationInfoType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Date"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlAttribute(
      name = "Detail"
   )
   protected String detail;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public String getDetail() {
      return this.detail;
   }

   public void setDetail(String value) {
      this.detail = value;
   }
}
