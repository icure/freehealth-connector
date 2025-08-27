package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "pingResponse",
   propOrder = {"pingResult"}
)
@XmlRootElement(
   name = "pingResponse"
)
public class PingResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PingResult"
   )
   protected String pingResult;

   public String getPingResult() {
      return this.pingResult;
   }

   public void setPingResult(String value) {
      this.pingResult = value;
   }
}
