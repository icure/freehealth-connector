package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AliveCheckResponseType",
   propOrder = {"aliveCheckResult"}
)
@XmlRootElement(
   name = "AliveCheckResponse"
)
public class AliveCheckResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AliveCheckResult"
   )
   protected String aliveCheckResult;

   public String getAliveCheckResult() {
      return this.aliveCheckResult;
   }

   public void setAliveCheckResult(String value) {
      this.aliveCheckResult = value;
   }
}
