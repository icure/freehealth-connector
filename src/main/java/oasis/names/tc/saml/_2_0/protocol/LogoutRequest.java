package oasis.names.tc.saml._2_0.protocol;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.saml._2_0.assertion.BaseID;
import oasis.names.tc.saml._2_0.assertion.EncryptedElementType;
import oasis.names.tc.saml._2_0.assertion.NameIDType;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "LogoutRequestType",
   propOrder = {"encryptedID", "nameID", "baseID", "sessionIndices"}
)
@XmlRootElement(
   name = "LogoutRequest"
)
public class LogoutRequest extends RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptedID",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected EncryptedElementType encryptedID;
   @XmlElement(
      name = "NameID",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected NameIDType nameID;
   @XmlElement(
      name = "BaseID",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected BaseID baseID;
   @XmlElement(
      name = "SessionIndex"
   )
   protected List<String> sessionIndices;
   @XmlAttribute(
      name = "Reason"
   )
   protected String reason;
   @XmlAttribute(
      name = "NotOnOrAfter"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime notOnOrAfter;

   public EncryptedElementType getEncryptedID() {
      return this.encryptedID;
   }

   public void setEncryptedID(EncryptedElementType value) {
      this.encryptedID = value;
   }

   public NameIDType getNameID() {
      return this.nameID;
   }

   public void setNameID(NameIDType value) {
      this.nameID = value;
   }

   public BaseID getBaseID() {
      return this.baseID;
   }

   public void setBaseID(BaseID value) {
      this.baseID = value;
   }

   public List<String> getSessionIndices() {
      if (this.sessionIndices == null) {
         this.sessionIndices = new ArrayList();
      }

      return this.sessionIndices;
   }

   public String getReason() {
      return this.reason;
   }

   public void setReason(String value) {
      this.reason = value;
   }

   public DateTime getNotOnOrAfter() {
      return this.notOnOrAfter;
   }

   public void setNotOnOrAfter(DateTime value) {
      this.notOnOrAfter = value;
   }
}
