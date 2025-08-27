package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.Assertion;
import oasis.names.tc.saml._2_0.assertion.EncryptedElementType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseType",
   propOrder = {"assertionsAndEncryptedAssertions"}
)
@XmlRootElement(
        namespace = "urn:oasis:names:tc:SAML:2.0:protocol",
        name = "Response"
)
public class Response extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "Assertion",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = Assertion.class
), @XmlElement(
   name = "EncryptedAssertion",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = EncryptedElementType.class
)})
   protected List<Object> assertionsAndEncryptedAssertions;

   public List<Object> getAssertionsAndEncryptedAssertions() {
      if (this.assertionsAndEncryptedAssertions == null) {
         this.assertionsAndEncryptedAssertions = new ArrayList();
      }

      return this.assertionsAndEncryptedAssertions;
   }
}
