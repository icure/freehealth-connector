package be.cin.nip.sync.reg.v1;

import be.cin.mycarenet.esb.common.v2.CareProviderType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "registrantType",
   propOrder = {"careProvider"}
)
public class RegistrantType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CareProvider",
      required = true
   )
   protected CareProviderType careProvider;

   public CareProviderType getCareProvider() {
      return this.careProvider;
   }

   public void setCareProvider(CareProviderType value) {
      this.careProvider = value;
   }
}
