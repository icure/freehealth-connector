package be.fgov.ehealth.commons.enc.v2;

import java.io.Serializable;
import jakarta.activation.DataHandler;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttachmentRef;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CipherReferenceType",
   propOrder = {"uri", "digest"}
)
@XmlRootElement(
   name = "CipherReference"
)
public class CipherReference implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "URI",
      required = true,
      type = String.class
   )
   @XmlAttachmentRef
   @XmlSchemaType(
      name = "anyURI"
   )
   protected DataHandler uri;
   @XmlElement(
      name = "Digest",
      required = true
   )
   protected Digest digest;

   public DataHandler getURI() {
      return this.uri;
   }

   public void setURI(DataHandler value) {
      this.uri = value;
   }

   public Digest getDigest() {
      return this.digest;
   }

   public void setDigest(Digest value) {
      this.digest = value;
   }
}
