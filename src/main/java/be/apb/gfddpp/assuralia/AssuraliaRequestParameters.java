package be.apb.gfddpp.assuralia;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AssuraliaRequestParameters",
   propOrder = {"bvacDocumentID", "cbfa", "requestorId", "signatureType"}
)
public class AssuraliaRequestParameters {
   @XmlElement(
      name = "BVACDocumentID"
   )
   protected String bvacDocumentID;
   @XmlElement(
      name = "CBFA",
      required = true
   )
   protected String cbfa;
   @XmlElement(
      name = "RequestorId",
      required = true
   )
   protected String requestorId;
   @XmlElement(
      name = "SignatureType",
      required = true
   )
   protected SignatureType signatureType;

   public String getBVACDocumentID() {
      return this.bvacDocumentID;
   }

   public void setBVACDocumentID(String value) {
      this.bvacDocumentID = value;
   }

   public String getCBFA() {
      return this.cbfa;
   }

   public void setCBFA(String value) {
      this.cbfa = value;
   }

   public String getRequestorId() {
      return this.requestorId;
   }

   public void setRequestorId(String value) {
      this.requestorId = value;
   }

   public SignatureType getSignatureType() {
      return this.signatureType;
   }

   public void setSignatureType(SignatureType value) {
      this.signatureType = value;
   }
}
