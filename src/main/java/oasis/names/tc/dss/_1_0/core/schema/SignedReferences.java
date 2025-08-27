package oasis.names.tc.dss._1_0.core.schema;

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
   name = "",
   propOrder = {"signedReferences"}
)
@XmlRootElement(
   name = "SignedReferences"
)
public class SignedReferences implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedReference",
      required = true
   )
   protected List<SignedReference> signedReferences;

   public List<SignedReference> getSignedReferences() {
      if (this.signedReferences == null) {
         this.signedReferences = new ArrayList();
      }

      return this.signedReferences;
   }
}
