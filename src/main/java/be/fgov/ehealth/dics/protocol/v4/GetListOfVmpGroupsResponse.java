package be.fgov.ehealth.dics.protocol.v4;

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
   name = "GetListOfVmpGroupsResponseType",
   propOrder = {"vmpGroups"}
)
@XmlRootElement(
   name = "GetListOfVmpGroupsResponse"
)
public class GetListOfVmpGroupsResponse extends ListConsultationResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VmpGroup"
   )
   protected List<VmpGroupListType> vmpGroups;

   public List<VmpGroupListType> getVmpGroups() {
      if (this.vmpGroups == null) {
         this.vmpGroups = new ArrayList();
      }

      return this.vmpGroups;
   }
}
