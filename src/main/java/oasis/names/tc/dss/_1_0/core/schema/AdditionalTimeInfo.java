package oasis.names.tc.dss._1_0.core.schema;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AdditionalTimeInfoType",
   propOrder = {"value"}
)
@XmlRootElement(
   name = "AdditionalTimeInfo"
)
public class AdditionalTimeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime value;
   @XmlAttribute(
      name = "Type",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;
   @XmlAttribute(
      name = "Ref"
   )
   protected String ref;

   public DateTime getValue() {
      return this.value;
   }

   public void setValue(DateTime value) {
      this.value = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getRef() {
      return this.ref;
   }

   public void setRef(String value) {
      this.ref = value;
   }
}
