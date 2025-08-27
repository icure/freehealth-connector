package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FileType",
   propOrder = {"name", "mimeType", "size", "content", "url"}
)
public class FileType {
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "MimeType",
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected MediaTypeType mimeType;
   @XmlElement(
      name = "Size"
   )
   protected String size;
   @XmlElement(
      name = "Content"
   )
   protected byte[] content;
   @XmlElement(
      name = "URL"
   )
   protected String url;

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public MediaTypeType getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(MediaTypeType value) {
      this.mimeType = value;
   }

   public String getSize() {
      return this.size;
   }

   public void setSize(String value) {
      this.size = value;
   }

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] value) {
      this.content = value;
   }

   public String getURL() {
      return this.url;
   }

   public void setURL(String value) {
      this.url = value;
   }
}
