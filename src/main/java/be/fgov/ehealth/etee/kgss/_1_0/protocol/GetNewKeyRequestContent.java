package be.fgov.ehealth.etee.kgss._1_0.protocol;

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
   name = "",
   propOrder = {"allowedReaders", "exludedReaders", "etk", "deletionStrategy"}
)
@XmlRootElement(
   name = "GetNewKeyRequestContent"
)
public class GetNewKeyRequestContent implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AllowedReader",
      required = true
   )
   protected List<CredentialType> allowedReaders;
   @XmlElement(
      name = "ExludedReader"
   )
   protected List<CredentialType> exludedReaders;
   @XmlElement(
      name = "ETK",
      required = true
   )
   protected byte[] etk;
   @XmlElement(
      name = "DeletionStrategy"
   )
   protected DeletionStrategyType deletionStrategy;

   public List<CredentialType> getAllowedReaders() {
      if (this.allowedReaders == null) {
         this.allowedReaders = new ArrayList();
      }

      return this.allowedReaders;
   }

   public List<CredentialType> getExludedReaders() {
      if (this.exludedReaders == null) {
         this.exludedReaders = new ArrayList();
      }

      return this.exludedReaders;
   }

   public byte[] getETK() {
      return this.etk;
   }

   public void setETK(byte[] value) {
      this.etk = value;
   }

   public DeletionStrategyType getDeletionStrategy() {
      return this.deletionStrategy;
   }

   public void setDeletionStrategy(DeletionStrategyType value) {
      this.deletionStrategy = value;
   }
}
