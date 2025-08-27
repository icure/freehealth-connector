package be.fgov.ehealth.hubservices.core.v1;

import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "transactionBaseType",
   propOrder = {"id", "author"}
)
@XmlRootElement(
   name = "transactionBaseType"
)
public class TransactionBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected IDKMEHR id;
   protected AuthorType author;

   public IDKMEHR getId() {
      return this.id;
   }

   public void setId(IDKMEHR value) {
      this.id = value;
   }

   public AuthorType getAuthor() {
      return this.author;
   }

   public void setAuthor(AuthorType value) {
      this.author = value;
   }
}
