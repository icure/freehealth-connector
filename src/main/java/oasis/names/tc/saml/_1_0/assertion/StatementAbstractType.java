package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatementAbstractType"
)
@XmlSeeAlso({SubjectStatementAbstractType.class})
public abstract class StatementAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
}
