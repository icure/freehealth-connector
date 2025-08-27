package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatementAbstractType"
)
@XmlSeeAlso({AttributeStatement.class, AuthzDecisionStatement.class, AuthnStatement.class})
public abstract class StatementAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
}
