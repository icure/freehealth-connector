package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.PaginationStatusResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListConsultationResponseType"
)
@XmlSeeAlso({GetListOfActualMedicinalProductPackagesResponse.class, GetListOfActualMedicinalProductsResponse.class, GetListOfVmpGroupsResponse.class, GetListOfVirtualMedicinalProductsResponse.class})
public class ListConsultationResponseType extends PaginationStatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Total"
   )
   protected Integer total;

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer value) {
      this.total = value;
   }
}
