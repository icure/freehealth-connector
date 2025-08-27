package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusDetailType",
   propOrder = {"anies"}
)
@XmlRootElement(
   name = "StatusDetail"
)
public class StatusDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> anies;

   public List<Object> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }
}
