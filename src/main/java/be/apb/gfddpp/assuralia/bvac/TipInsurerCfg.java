package be.apb.gfddpp.assuralia.bvac;

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
   propOrder = {"insurer"}
)
@XmlRootElement(
   name = "tip-insurer-cfg"
)
public class TipInsurerCfg {
   protected List<Insurer> insurer;

   public List<Insurer> getInsurer() {
      if (this.insurer == null) {
         this.insurer = new ArrayList();
      }

      return this.insurer;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"name", "cbfa", "requestorId", "endPointType", "encryptionKey", "transportKey", "parties"}
   )
   public static class Insurer {
      @XmlElement(
         required = true
      )
      protected String name;
      @XmlElement(
         name = "CBFA"
      )
      protected String cbfa;
      @XmlElement(
         name = "requestor-id"
      )
      protected String requestorId;
      @XmlElement(
         name = "end-point-type",
         required = true
      )
      protected String endPointType;
      @XmlElement(
         name = "encryption-key"
      )
      protected String encryptionKey;
      @XmlElement(
         name = "transport-key"
      )
      protected String transportKey;
      protected Parties parties;

      public String getName() {
         return this.name;
      }

      public void setName(String value) {
         this.name = value;
      }

      public String getCBFA() {
         return this.cbfa;
      }

      public void setCBFA(String value) {
         this.cbfa = value;
      }

      public String getRequestorId() {
         return this.requestorId;
      }

      public void setRequestorId(String value) {
         this.requestorId = value;
      }

      public String getEndPointType() {
         return this.endPointType;
      }

      public void setEndPointType(String value) {
         this.endPointType = value;
      }

      public String getEncryptionKey() {
         return this.encryptionKey;
      }

      public void setEncryptionKey(String value) {
         this.encryptionKey = value;
      }

      public String getTransportKey() {
         return this.transportKey;
      }

      public void setTransportKey(String value) {
         this.transportKey = value;
      }

      public Parties getParties() {
         return this.parties;
      }

      public void setParties(Parties value) {
         this.parties = value;
      }
   }
}
