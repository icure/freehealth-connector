package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthorType",
   propOrder = {"hcParties"}
)
@XmlRootElement(
   name = "Author"
)
public class Author implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "HcParty",
      required = true
   )
   protected List<ActorType> hcParties;

   public List<ActorType> getHcParties() {
      if (this.hcParties == null) {
         this.hcParties = new ArrayList();
      }

      return this.hcParties;
   }
}
