package be.fgov.ehealth.mycarenet.commons.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractIdType",
   propOrder = {"name"}
)
@XmlSeeAlso({IdType.class, PackageType.class})
public abstract class AbstractIdType extends SelfRefType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name"
   )
   protected ValueRefString name;

   public ValueRefString getName() {
      return this.name;
   }

   public void setName(ValueRefString value) {
      this.name = value;
   }
}
