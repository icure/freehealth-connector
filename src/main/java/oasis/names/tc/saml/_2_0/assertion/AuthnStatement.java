package oasis.names.tc.saml._2_0.assertion;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthnStatementType",
   propOrder = {"subjectLocality", "authnContext"}
)
@XmlRootElement(
   name = "AuthnStatement"
)
public class AuthnStatement extends StatementAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SubjectLocality"
   )
   protected SubjectLocality subjectLocality;
   @XmlElement(
      name = "AuthnContext",
      required = true
   )
   protected AuthnContext authnContext;
   @XmlAttribute(
      name = "AuthnInstant",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime authnInstant;
   @XmlAttribute(
      name = "SessionIndex"
   )
   protected String sessionIndex;
   @XmlAttribute(
      name = "SessionNotOnOrAfter"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime sessionNotOnOrAfter;

   public SubjectLocality getSubjectLocality() {
      return this.subjectLocality;
   }

   public void setSubjectLocality(SubjectLocality value) {
      this.subjectLocality = value;
   }

   public AuthnContext getAuthnContext() {
      return this.authnContext;
   }

   public void setAuthnContext(AuthnContext value) {
      this.authnContext = value;
   }

   public DateTime getAuthnInstant() {
      return this.authnInstant;
   }

   public void setAuthnInstant(DateTime value) {
      this.authnInstant = value;
   }

   public String getSessionIndex() {
      return this.sessionIndex;
   }

   public void setSessionIndex(String value) {
      this.sessionIndex = value;
   }

   public DateTime getSessionNotOnOrAfter() {
      return this.sessionNotOnOrAfter;
   }

   public void setSessionNotOnOrAfter(DateTime value) {
      this.sessionNotOnOrAfter = value;
   }
}
