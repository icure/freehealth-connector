package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DataEntryResponse",
   propOrder = {"author"}
)
public class DataEntryResponse extends AbstractDataEntry {
   @XmlElement(
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
