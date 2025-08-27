package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ScopingType",
   propOrder = {"idpList", "requesterIDs"}
)
@XmlRootElement(
   name = "Scoping"
)
public class Scoping implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IDPList"
   )
   protected IDPList idpList;
   @XmlElement(
      name = "RequesterID"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> requesterIDs;
   @XmlAttribute(
      name = "ProxyCount"
   )
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   protected BigInteger proxyCount;

   public IDPList getIDPList() {
      return this.idpList;
   }

   public void setIDPList(IDPList value) {
      this.idpList = value;
   }

   public List<String> getRequesterIDs() {
      if (this.requesterIDs == null) {
         this.requesterIDs = new ArrayList();
      }

      return this.requesterIDs;
   }

   public BigInteger getProxyCount() {
      return this.proxyCount;
   }

   public void setProxyCount(BigInteger value) {
      this.proxyCount = value;
   }
}
