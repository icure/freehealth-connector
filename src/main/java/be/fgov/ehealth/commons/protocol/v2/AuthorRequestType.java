package be.fgov.ehealth.commons.protocol.v2;

import be.fgov.ehealth.commons.core.v2.Author;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthorRequestType",
   propOrder = {"author"}
)
@XmlSeeAlso({AuthorPaginationRequestType.class})
public class AuthorRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Author",
      required = true
   )
   protected Author author;

   public Author getAuthor() {
      return this.author;
   }

   public void setAuthor(Author value) {
      this.author = value;
   }
}
