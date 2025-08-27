package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ArtifactResponseType",
   propOrder = {"any"}
)
@XmlRootElement(
   name = "ArtifactResponse"
)
public class ArtifactResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAnyElement
   protected Element any;

   public Element getAny() {
      return this.any;
   }

   public void setAny(Element value) {
      this.any = value;
   }
}
