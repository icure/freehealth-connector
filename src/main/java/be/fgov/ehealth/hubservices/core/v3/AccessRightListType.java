package be.fgov.ehealth.hubservices.core.v3;

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
   name = "AccessRightListType",
   propOrder = {"accessrights"}
)
@XmlRootElement(
   name = "AccessRightListType"
)
public class AccessRightListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "accessright"
   )
   protected List<AccessRightType> accessrights;

   public List<AccessRightType> getAccessrights() {
      if (this.accessrights == null) {
         this.accessrights = new ArrayList();
      }

      return this.accessrights;
   }
}
