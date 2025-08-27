package oasis.names.tc.saml._1_0.assertion;

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
   propOrder = {"assertionIDReferencesAndAssertions"}
)
@XmlRootElement(
   name = "Evidence"
)
public class Evidence implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRefs({@XmlElementRef(
   name = "AssertionIDReference",
   namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
   type = JAXBElement.class
), @XmlElementRef(
   name = "Assertion",
   namespace = "urn:oasis:names:tc:SAML:1.0:assertion",
   type = Assertion.class
)})
   protected List<Serializable> assertionIDReferencesAndAssertions;

   public List<Serializable> getAssertionIDReferencesAndAssertions() {
      if (this.assertionIDReferencesAndAssertions == null) {
         this.assertionIDReferencesAndAssertions = new ArrayList();
      }

      return this.assertionIDReferencesAndAssertions;
   }
}
