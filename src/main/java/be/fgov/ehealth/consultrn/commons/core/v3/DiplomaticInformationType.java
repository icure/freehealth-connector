package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DiplomaticInformationType",
   propOrder = {"diplomaticPost", "diplomaticAddress", "postAddress"}
)
public class DiplomaticInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DiplomaticPost"
   )
   protected DiplomaticPostType diplomaticPost;
   @XmlElement(
      name = "DiplomaticAddress"
   )
   protected TemporaryAddressType diplomaticAddress;
   @XmlElement(
      name = "PostAddress"
   )
   protected TemporaryAddressType postAddress;

   public DiplomaticPostType getDiplomaticPost() {
      return this.diplomaticPost;
   }

   public void setDiplomaticPost(DiplomaticPostType value) {
      this.diplomaticPost = value;
   }

   public TemporaryAddressType getDiplomaticAddress() {
      return this.diplomaticAddress;
   }

   public void setDiplomaticAddress(TemporaryAddressType value) {
      this.diplomaticAddress = value;
   }

   public TemporaryAddressType getPostAddress() {
      return this.postAddress;
   }

   public void setPostAddress(TemporaryAddressType value) {
      this.postAddress = value;
   }
}
