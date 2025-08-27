package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   name = "UnsignedDataObjectPropertiesType",
   propOrder = {"unsignedDataObjectProperties"}
)
@XmlRootElement(
   name = "UnsignedDataObjectProperties"
)
public class UnsignedDataObjectProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "UnsignedDataObjectProperty",
      required = true
   )
   protected List<Any> unsignedDataObjectProperties;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public List<Any> getUnsignedDataObjectProperties() {
      if (this.unsignedDataObjectProperties == null) {
         this.unsignedDataObjectProperties = new ArrayList();
      }

      return this.unsignedDataObjectProperties;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
