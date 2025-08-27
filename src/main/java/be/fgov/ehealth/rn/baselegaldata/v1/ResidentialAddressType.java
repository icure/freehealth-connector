package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResidentialAddressType"
)
public class ResidentialAddressType extends ResidentialAddressOptionalInceptionDateType implements Serializable {
   private static final long serialVersionUID = 1L;
}
