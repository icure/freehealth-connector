package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.id.v1.IDINSURANCE;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "memberinsuranceType",
   propOrder = {"id", "membership"}
)
public class MemberinsuranceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected IDINSURANCE id;
   @XmlElement(
      required = true
   )
   protected String membership;

   public IDINSURANCE getId() {
      return this.id;
   }

   public void setId(IDINSURANCE value) {
      this.id = value;
   }

   public String getMembership() {
      return this.membership;
   }

   public void setMembership(String value) {
      this.membership = value;
   }
}
