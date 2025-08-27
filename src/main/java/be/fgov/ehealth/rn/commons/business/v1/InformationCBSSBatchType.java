package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InformationCBSSBatchType",
   propOrder = {"fileId", "fileCreationTimestamp"}
)
public class InformationCBSSBatchType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FileId",
      required = true
   )
   @XmlSchemaType(
      name = "unsignedLong"
   )
   protected BigInteger fileId;
   @XmlElement(
      name = "FileCreationTimestamp",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime fileCreationTimestamp;

   public BigInteger getFileId() {
      return this.fileId;
   }

   public void setFileId(BigInteger value) {
      this.fileId = value;
   }

   public DateTime getFileCreationTimestamp() {
      return this.fileCreationTimestamp;
   }

   public void setFileCreationTimestamp(DateTime value) {
      this.fileCreationTimestamp = value;
   }
}
