package oasis.names.tc.saml._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthenticationQueryType"
)
@XmlRootElement(
   name = "AuthenticationQuery"
)
public class AuthenticationQuery extends SubjectQueryAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "AuthenticationMethod"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String authenticationMethod;

   public String getAuthenticationMethod() {
      return this.authenticationMethod;
   }

   public void setAuthenticationMethod(String value) {
      this.authenticationMethod = value;
   }
}
