package be.fgov.ehealth.etee.kgss._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SealedContentType",
   propOrder = {"sealedContent"}
)
public class SealedContentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SealedContent",
      required = true
   )
   protected byte[] sealedContent;

   public byte[] getSealedContent() {
      return this.sealedContent;
   }

   public void setSealedContent(byte[] value) {
      this.sealedContent = value;
   }
}
