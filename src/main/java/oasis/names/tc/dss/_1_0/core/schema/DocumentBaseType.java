package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DocumentBaseType"
)
@XmlSeeAlso({DocumentType.class, DocumentHash.class, TransformedData.class})
public abstract class DocumentBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "ID"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "RefURI"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String refURI;
   @XmlAttribute(
      name = "RefType"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String refType;
   @XmlAttribute(
      name = "SchemaRefs"
   )
   @XmlIDREF
   @XmlSchemaType(
      name = "IDREFS"
   )
   protected List<Object> schemaRefs;

   public String getID() {
      return this.id;
   }

   public void setID(String value) {
      this.id = value;
   }

   public String getRefURI() {
      return this.refURI;
   }

   public void setRefURI(String value) {
      this.refURI = value;
   }

   public String getRefType() {
      return this.refType;
   }

   public void setRefType(String value) {
      this.refType = value;
   }

   public List<Object> getSchemaRefs() {
      if (this.schemaRefs == null) {
         this.schemaRefs = new ArrayList();
      }

      return this.schemaRefs;
   }
}
