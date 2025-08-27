package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EvidenceType",
   propOrder = {"assertionIDRevesAndAssertionURIRevesAndAssertions"}
)
@XmlRootElement(
   name = "Evidence"
)
public class Evidence implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRefs({@XmlElementRef(
   name = "AssertionURIRef",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = JAXBElement.class
), @XmlElementRef(
   name = "AssertionIDRef",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = JAXBElement.class
), @XmlElementRef(
   name = "EncryptedAssertion",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = JAXBElement.class
), @XmlElementRef(
   name = "Assertion",
   namespace = "urn:oasis:names:tc:SAML:2.0:assertion",
   type = Assertion.class
)})
   protected List<Serializable> assertionIDRevesAndAssertionURIRevesAndAssertions;

   public List<Serializable> getAssertionIDRevesAndAssertionURIRevesAndAssertions() {
      if (this.assertionIDRevesAndAssertionURIRevesAndAssertions == null) {
         this.assertionIDRevesAndAssertionURIRevesAndAssertions = new ArrayList();
      }

      return this.assertionIDRevesAndAssertionURIRevesAndAssertions;
   }
}
