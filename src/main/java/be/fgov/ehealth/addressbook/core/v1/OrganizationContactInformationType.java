package be.fgov.ehealth.addressbook.core.v1;

import be.fgov.ehealth.aa.complextype.v1.OrganizationAddressbookType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OrganizationContactInformationType",
   propOrder = {"eHealthBoxes"}
)
public class OrganizationContactInformationType extends OrganizationAddressbookType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EHealthBox"
   )
   protected List<EHealthBoxType> eHealthBoxes;

   public List<EHealthBoxType> getEHealthBoxes() {
      if (this.eHealthBoxes == null) {
         this.eHealthBoxes = new ArrayList();
      }

      return this.eHealthBoxes;
   }
}
