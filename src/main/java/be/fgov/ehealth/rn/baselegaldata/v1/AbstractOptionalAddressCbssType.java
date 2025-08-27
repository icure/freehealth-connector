package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalAddressCbssType",
   propOrder = {"residentialAddress"}
)
@XmlSeeAlso({AddressCbssBaseType.class, AddressCbssWithUpdateStatusType.class, AddressCbssWithStatusAndSourceType.class})
public abstract class AbstractOptionalAddressCbssType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ResidentialAddress"
   )
   protected ResidentialAddressType residentialAddress;

   public ResidentialAddressType getResidentialAddress() {
      return this.residentialAddress;
   }

   public void setResidentialAddress(ResidentialAddressType value) {
      this.residentialAddress = value;
   }
}
