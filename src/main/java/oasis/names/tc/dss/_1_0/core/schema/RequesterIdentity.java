package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._1_0.assertion.NameIdentifierType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"name", "supportingInfo"}
)
@XmlRootElement(
   name = "RequesterIdentity"
)
public class RequesterIdentity implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected NameIdentifierType name;
   @XmlElement(
      name = "SupportingInfo"
   )
   protected AnyType supportingInfo;

   public NameIdentifierType getName() {
      return this.name;
   }

   public void setName(NameIdentifierType value) {
      this.name = value;
   }

   public AnyType getSupportingInfo() {
      return this.supportingInfo;
   }

   public void setSupportingInfo(AnyType value) {
      this.supportingInfo = value;
   }
}
