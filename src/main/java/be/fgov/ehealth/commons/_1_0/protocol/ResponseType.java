package be.fgov.ehealth.commons._1_0.protocol;

import be.fgov.ehealth.commons._1_0.core.Status;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkResponse;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"status"}
)
@XmlSeeAlso({GetEtkResponse.class})
public class ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Status",
      namespace = "urn:be:fgov:ehealth:commons:1_0:core",
      required = true
   )
   protected Status status;
   @XmlAttribute(
      name = "Id"
   )
   protected String id;

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status value) {
      this.status = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
