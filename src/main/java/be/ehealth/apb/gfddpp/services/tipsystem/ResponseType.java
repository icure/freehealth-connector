package be.ehealth.apb.gfddpp.services.tipsystem;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   namespace = "urn:be:fgov:ehealth:commons:protocol:v1",
   propOrder = {"status"}
)
@XmlSeeAlso({CheckAliveResponseType.class, RoutedSealedResponseType.class, SealedResponseType.class, SimpleResponseType.class, RoutedCheckAliveResponseType.class})
public class ResponseType {
   @XmlElement(
      name = "Status",
      required = true
   )
   protected StatusType status;
   @XmlAttribute(
      name = "Id"
   )
   protected String id;

   public StatusType getStatus() {
      return this.status;
   }

   public void setStatus(StatusType var1) {
      this.status = var1;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String var1) {
      this.id = var1;
   }
}
