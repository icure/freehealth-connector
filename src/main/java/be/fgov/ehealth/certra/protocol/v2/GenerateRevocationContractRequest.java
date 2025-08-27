package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.certra.core.v2.RevocationReasonType;
import be.fgov.ehealth.commons.core.v2.ActorType;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenerateRevocationContractRequestType",
   propOrder = {"publicKeyIdentifier", "signer", "revocationReason"}
)
@XmlRootElement(
   name = "GenerateRevocationContractRequest"
)
public class GenerateRevocationContractRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PublicKeyIdentifier",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(HexBinaryAdapter.class)
   @XmlSchemaType(
      name = "hexBinary"
   )
   protected byte[] publicKeyIdentifier;
   @XmlElement(
      name = "Signer",
      required = true
   )
   protected ActorType signer;
   @XmlElement(
      name = "RevocationReason",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected RevocationReasonType revocationReason;

   public byte[] getPublicKeyIdentifier() {
      return this.publicKeyIdentifier;
   }

   public void setPublicKeyIdentifier(byte[] value) {
      this.publicKeyIdentifier = value;
   }

   public ActorType getSigner() {
      return this.signer;
   }

   public void setSigner(ActorType value) {
      this.signer = value;
   }

   public RevocationReasonType getRevocationReason() {
      return this.revocationReason;
   }

   public void setRevocationReason(RevocationReasonType value) {
      this.revocationReason = value;
   }
}
