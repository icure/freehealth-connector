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
   name = "UnsignedPropertiesType",
   propOrder = {"unsignedSignatureProperties", "unsignedDataObjectProperties"}
)
@XmlRootElement(
   name = "UnsignedProperties"
)
public class UnsignedProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "UnsignedSignatureProperties"
   )
   protected UnsignedSignatureProperties unsignedSignatureProperties;
   @XmlElement(
      name = "UnsignedDataObjectProperties"
   )
   protected UnsignedDataObjectProperties unsignedDataObjectProperties;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public UnsignedSignatureProperties getUnsignedSignatureProperties() {
      return this.unsignedSignatureProperties;
   }

   public void setUnsignedSignatureProperties(UnsignedSignatureProperties value) {
      this.unsignedSignatureProperties = value;
   }

   public UnsignedDataObjectProperties getUnsignedDataObjectProperties() {
      return this.unsignedDataObjectProperties;
   }

   public void setUnsignedDataObjectProperties(UnsignedDataObjectProperties value) {
      this.unsignedDataObjectProperties = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
