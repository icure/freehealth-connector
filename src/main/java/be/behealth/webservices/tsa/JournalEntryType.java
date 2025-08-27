package be.behealth.webservices.tsa;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "JournalEntryType",
   propOrder = {"hash", "ref"}
)
public class JournalEntryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Hash",
      required = true
   )
   protected String hash;
   @XmlElement(
      name = "Ref"
   )
   protected long ref;

   public String getHash() {
      return this.hash;
   }

   public void setHash(String value) {
      this.hash = value;
   }

   public long getRef() {
      return this.ref;
   }

   public void setRef(long value) {
      this.ref = value;
   }
}
