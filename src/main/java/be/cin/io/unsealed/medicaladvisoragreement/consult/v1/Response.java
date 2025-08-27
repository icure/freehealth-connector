package be.cin.io.unsealed.medicaladvisoragreement.consult.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"timestampReply", "kmehrResponse"}
)
@XmlRootElement(
   name = "Response"
)
public class Response implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TimestampReply",
      required = true
   )
   protected byte[] timestampReply;
   @XmlElement(
      name = "KmehrResponse",
      required = true
   )
   protected byte[] kmehrResponse;

   public byte[] getTimestampReply() {
      return this.timestampReply;
   }

   public void setTimestampReply(byte[] value) {
      this.timestampReply = value;
   }

   public byte[] getKmehrResponse() {
      return this.kmehrResponse;
   }

   public void setKmehrResponse(byte[] value) {
      this.kmehrResponse = value;
   }
}
