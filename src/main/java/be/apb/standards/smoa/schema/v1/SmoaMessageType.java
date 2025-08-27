package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SmoaMessageType",
   propOrder = {"header", "abstractFolder"}
)
public class SmoaMessageType {
   @XmlElement(
      required = true
   )
   protected HeaderType header;
   @XmlElementRef(
      name = "abstract-Folder",
      namespace = "http://www.apb.be/standards/smoa/schema/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractFolderType> abstractFolder;

   public HeaderType getHeader() {
      return this.header;
   }

   public void setHeader(HeaderType value) {
      this.header = value;
   }

   public JAXBElement<? extends AbstractFolderType> getAbstractFolder() {
      return this.abstractFolder;
   }

   public void setAbstractFolder(JAXBElement<? extends AbstractFolderType> value) {
      this.abstractFolder = value;
   }
}
