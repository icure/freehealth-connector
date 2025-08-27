package oasis.names.tc.dss._1_0.core.schema;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   name = "VerificationTimeInfoType",
   propOrder = {"verificationTime", "additionalTimeInfos"}
)
@XmlRootElement(
   name = "VerificationTimeInfo"
)
public class VerificationTimeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VerificationTime",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime verificationTime;
   @XmlElement(
      name = "AdditionalTimeInfo"
   )
   protected List<AdditionalTimeInfo> additionalTimeInfos;

   public DateTime getVerificationTime() {
      return this.verificationTime;
   }

   public void setVerificationTime(DateTime value) {
      this.verificationTime = value;
   }

   public List<AdditionalTimeInfo> getAdditionalTimeInfos() {
      if (this.additionalTimeInfos == null) {
         this.additionalTimeInfos = new ArrayList();
      }

      return this.additionalTimeInfos;
   }
}
