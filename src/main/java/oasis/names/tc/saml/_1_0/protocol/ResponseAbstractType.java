package oasis.names.tc.saml._1_0.protocol;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseAbstractType",
   propOrder = {"signature"}
)
@XmlSeeAlso({Response.class})
public abstract class ResponseAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Signature signature;
   @XmlAttribute(
      name = "ResponseID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String responseID;
   @XmlAttribute(
      name = "InResponseTo"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NCName"
   )
   protected String inResponseTo;
   @XmlAttribute(
      name = "MajorVersion",
      required = true
   )
   protected BigInteger majorVersion;
   @XmlAttribute(
      name = "MinorVersion",
      required = true
   )
   protected BigInteger minorVersion;
   @XmlAttribute(
      name = "IssueInstant",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime issueInstant;
   @XmlAttribute(
      name = "Recipient"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String recipient;

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }

   public String getResponseID() {
      return this.responseID;
   }

   public void setResponseID(String value) {
      this.responseID = value;
   }

   public String getInResponseTo() {
      return this.inResponseTo;
   }

   public void setInResponseTo(String value) {
      this.inResponseTo = value;
   }

   public BigInteger getMajorVersion() {
      return this.majorVersion;
   }

   public void setMajorVersion(BigInteger value) {
      this.majorVersion = value;
   }

   public BigInteger getMinorVersion() {
      return this.minorVersion;
   }

   public void setMinorVersion(BigInteger value) {
      this.minorVersion = value;
   }

   public DateTime getIssueInstant() {
      return this.issueInstant;
   }

   public void setIssueInstant(DateTime value) {
      this.issueInstant = value;
   }

   public String getRecipient() {
      return this.recipient;
   }

   public void setRecipient(String value) {
      this.recipient = value;
   }
}
