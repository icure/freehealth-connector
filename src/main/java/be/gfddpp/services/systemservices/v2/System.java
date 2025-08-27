package be.gfddpp.services.systemservices.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "system",
   propOrder = {"systemType", "systemId"}
)
public class System {
   @XmlElement(
      required = true
   )
   protected String systemType;
   @XmlElement(
      required = true
   )
   protected String systemId;

   public String getSystemType() {
      return this.systemType;
   }

   public void setSystemType(String value) {
      this.systemType = value;
   }

   public String getSystemId() {
      return this.systemId;
   }

   public void setSystemId(String value) {
      this.systemId = value;
   }
}
