package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.KeyInfo;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"keyInfo"}
)
@XmlRootElement(
   name = "AdditionalKeyInfo"
)
public class AdditionalKeyInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "KeyInfo",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected KeyInfo keyInfo;

   public KeyInfo getKeyInfo() {
      return this.keyInfo;
   }

   public void setKeyInfo(KeyInfo value) {
      this.keyInfo = value;
   }
}
