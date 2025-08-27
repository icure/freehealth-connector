package be.fgov.ehealth.mycarenet.commons.core.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PackageType",
   propOrder = {"license"}
)
public class PackageType extends AbstractIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "License",
      required = true
   )
   protected LicenseType license;

   public LicenseType getLicense() {
      return this.license;
   }

   public void setLicense(LicenseType value) {
      this.license = value;
   }
}
