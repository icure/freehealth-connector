package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BaseServiceListType",
   propOrder = {"baseServices"}
)
public class BaseServiceListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BaseService"
   )
   protected List<BaseServiceType> baseServices;

   public List<BaseServiceType> getBaseServices() {
      if (this.baseServices == null) {
         this.baseServices = new ArrayList();
      }

      return this.baseServices;
   }
}
