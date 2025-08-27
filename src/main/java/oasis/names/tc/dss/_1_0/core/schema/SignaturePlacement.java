package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"xPathFirstChildOf", "xPathAfter"}
)
@XmlRootElement(
   name = "SignaturePlacement"
)
public class SignaturePlacement implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "XPathFirstChildOf"
   )
   protected String xPathFirstChildOf;
   @XmlElement(
      name = "XPathAfter"
   )
   protected String xPathAfter;
   @XmlAttribute(
      name = "WhichDocument"
   )
   @XmlIDREF
   @XmlSchemaType(
      name = "IDREF"
   )
   protected Object whichDocument;
   @XmlAttribute(
      name = "CreateEnvelopedSignature"
   )
   protected Boolean createEnvelopedSignature;

   public String getXPathFirstChildOf() {
      return this.xPathFirstChildOf;
   }

   public void setXPathFirstChildOf(String value) {
      this.xPathFirstChildOf = value;
   }

   public String getXPathAfter() {
      return this.xPathAfter;
   }

   public void setXPathAfter(String value) {
      this.xPathAfter = value;
   }

   public Object getWhichDocument() {
      return this.whichDocument;
   }

   public void setWhichDocument(Object value) {
      this.whichDocument = value;
   }

   public boolean isCreateEnvelopedSignature() {
      return this.createEnvelopedSignature == null ? true : this.createEnvelopedSignature;
   }

   public void setCreateEnvelopedSignature(Boolean value) {
      this.createEnvelopedSignature = value;
   }
}
