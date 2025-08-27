package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectStatementAbstractType",
   propOrder = {"subject"}
)
@XmlSeeAlso({AttributeStatement.class, AuthorizationDecisionStatement.class, AuthenticationStatement.class})
public abstract class SubjectStatementAbstractType extends StatementAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Subject",
      required = true
   )
   protected Subject subject;

   public Subject getSubject() {
      return this.subject;
   }

   public void setSubject(Subject value) {
      this.subject = value;
   }
}
