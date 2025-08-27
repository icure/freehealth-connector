package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthnContextType",
   propOrder = {"authnContextClassRef", "authnContextDeclRef", "authnContextDecl", "authenticatingAuthorities"}
)
@XmlRootElement(
   name = "AuthnContext"
)
public class AuthnContext implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AuthnContextClassRef"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String authnContextClassRef;
   @XmlElement(
      name = "AuthnContextDeclRef"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String authnContextDeclRef;
   @XmlElement(
      name = "AuthnContextDecl"
   )
   protected Object authnContextDecl;
   @XmlElement(
      name = "AuthenticatingAuthority"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> authenticatingAuthorities;

   public String getAuthnContextClassRef() {
      return this.authnContextClassRef;
   }

   public void setAuthnContextClassRef(String value) {
      this.authnContextClassRef = value;
   }

   public String getAuthnContextDeclRef() {
      return this.authnContextDeclRef;
   }

   public void setAuthnContextDeclRef(String value) {
      this.authnContextDeclRef = value;
   }

   public Object getAuthnContextDecl() {
      return this.authnContextDecl;
   }

   public void setAuthnContextDecl(Object value) {
      this.authnContextDecl = value;
   }

   public List<String> getAuthenticatingAuthorities() {
      if (this.authenticatingAuthorities == null) {
         this.authenticatingAuthorities = new ArrayList();
      }

      return this.authenticatingAuthorities;
   }
}
