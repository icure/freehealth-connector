package be.fgov.ehealth.consultrn._1_0.protocol;

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
   name = "MutationListType",
   propOrder = {"mutations"}
)
@XmlRootElement(
   name = "MutationList"
)
public class MutationList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Mutation",
      namespace = "urn:be:fgov:ehealth:consultRN:1_0:protocol",
      required = true
   )
   protected List<Mutation> mutations;

   public List<Mutation> getMutations() {
      if (this.mutations == null) {
         this.mutations = new ArrayList();
      }

      return this.mutations;
   }
}
