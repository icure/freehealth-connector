package be.fgov.ehealth.etee.ra.revoke._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"requestId", "contract", "revocableCertificatesDataSignedResponse"}
)
@XmlRootElement(
   name = "RevokeDataRequest"
)
public class RevokeDataRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RequestId",
      required = true
   )
   protected String requestId;
   @XmlElement(
      name = "Contract",
      required = true
   )
   protected String contract;
   @XmlElement(
      name = "RevocableCertificatesDataSignedResponse"
   )
   protected byte[] revocableCertificatesDataSignedResponse;

   public String getRequestId() {
      return this.requestId;
   }

   public void setRequestId(String value) {
      this.requestId = value;
   }

   public String getContract() {
      return this.contract;
   }

   public void setContract(String value) {
      this.contract = value;
   }

   public byte[] getRevocableCertificatesDataSignedResponse() {
      return this.revocableCertificatesDataSignedResponse;
   }

   public void setRevocableCertificatesDataSignedResponse(byte[] value) {
      this.revocableCertificatesDataSignedResponse = value;
   }
}
