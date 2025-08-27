package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SignedPropertiesType",
   propOrder = {"signedSignatureProperties", "signedDataObjectProperties"}
)
@XmlRootElement(
   name = "SignedProperties"
)
public class SignedProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedSignatureProperties"
   )
   protected SignedSignatureProperties signedSignatureProperties;
   @XmlElement(
      name = "SignedDataObjectProperties"
   )
   protected SignedDataObjectProperties signedDataObjectProperties;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public SignedSignatureProperties getSignedSignatureProperties() {
      return this.signedSignatureProperties;
   }

   public void setSignedSignatureProperties(SignedSignatureProperties value) {
      this.signedSignatureProperties = value;
   }

   public SignedDataObjectProperties getSignedDataObjectProperties() {
      return this.signedDataObjectProperties;
   }

   public void setSignedDataObjectProperties(SignedDataObjectProperties value) {
      this.signedDataObjectProperties = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
