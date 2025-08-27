package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestBaseType",
   propOrder = {"optionalInputs", "inputDocuments"}
)
@XmlSeeAlso({VerifyRequest.class, SignRequest.class})
public class RequestBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OptionalInputs"
   )
   protected AnyType optionalInputs;
   @XmlElement(
      name = "InputDocuments"
   )
   protected InputDocuments inputDocuments;
   @XmlAttribute(
      name = "RequestID"
   )
   protected String requestID;
   @XmlAttribute(
      name = "Profile"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String profile;

   public AnyType getOptionalInputs() {
      return this.optionalInputs;
   }

   public void setOptionalInputs(AnyType value) {
      this.optionalInputs = value;
   }

   public InputDocuments getInputDocuments() {
      return this.inputDocuments;
   }

   public void setInputDocuments(InputDocuments value) {
      this.inputDocuments = value;
   }

   public String getRequestID() {
      return this.requestID;
   }

   public void setRequestID(String value) {
      this.requestID = value;
   }

   public String getProfile() {
      return this.profile;
   }

   public void setProfile(String value) {
      this.profile = value;
   }
}
