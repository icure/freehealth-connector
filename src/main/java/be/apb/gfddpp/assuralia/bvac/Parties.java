package be.apb.gfddpp.assuralia.bvac;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "parties",
   propOrder = {"party"}
)
public class Parties {
   protected List<Party> party;

   public List<Party> getParty() {
      if (this.party == null) {
         this.party = new ArrayList();
      }

      return this.party;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"name", "requestorId"}
   )
   public static class Party {
      protected String name;
      @XmlElement(
         name = "requestor-id"
      )
      protected String requestorId;

      public String getName() {
         return this.name;
      }

      public void setName(String value) {
         this.name = value;
      }

      public String getRequestorId() {
         return this.requestorId;
      }

      public void setRequestorId(String value) {
         this.requestorId = value;
      }
   }
}
