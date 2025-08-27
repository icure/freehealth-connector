package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CompleteRevocationRefsType",
   propOrder = {"crlRefs", "ocspRefs", "otherRefs"}
)
public class CompleteRevocationRefsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CRLRefs"
   )
   protected CRLRefsType crlRefs;
   @XmlElement(
      name = "OCSPRefs"
   )
   protected OCSPRefsType ocspRefs;
   @XmlElement(
      name = "OtherRefs"
   )
   protected OtherCertStatusRefsType otherRefs;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public CRLRefsType getCRLRefs() {
      return this.crlRefs;
   }

   public void setCRLRefs(CRLRefsType value) {
      this.crlRefs = value;
   }

   public OCSPRefsType getOCSPRefs() {
      return this.ocspRefs;
   }

   public void setOCSPRefs(OCSPRefsType value) {
      this.ocspRefs = value;
   }

   public OtherCertStatusRefsType getOtherRefs() {
      return this.otherRefs;
   }

   public void setOtherRefs(OtherCertStatusRefsType value) {
      this.otherRefs = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
