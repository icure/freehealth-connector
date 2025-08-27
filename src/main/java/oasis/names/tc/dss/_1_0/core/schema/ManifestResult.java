package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"referenceXpath", "status"}
)
@XmlRootElement(
   name = "ManifestResult"
)
public class ManifestResult implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReferenceXpath",
      required = true
   )
   protected String referenceXpath;
   @XmlElement(
      name = "Status",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String status;

   public String getReferenceXpath() {
      return this.referenceXpath;
   }

   public void setReferenceXpath(String value) {
      this.referenceXpath = value;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }
}
