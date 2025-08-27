package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"document", "transformedData", "documentHash", "other"}
)
@XmlRootElement(
   name = "InputDocuments"
)
public class InputDocuments implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Document"
   )
   protected List<DocumentType> document;
   @XmlElement(
      name = "TransformedData"
   )
   protected List<TransformedData> transformedData;
   @XmlElement(
      name = "DocumentHash"
   )
   protected List<DocumentHash> documentHash;
   @XmlElement(
      name = "Other"
   )
   protected List<AnyType> other;

   public List<DocumentType> getDocument() {
      if (this.document == null) {
         this.document = new ArrayList();
      }

      return this.document;
   }

   public List<TransformedData> getTransformedData() {
      if (this.transformedData == null) {
         this.transformedData = new ArrayList();
      }

      return this.transformedData;
   }

   public List<DocumentHash> getDocumentHash() {
      if (this.documentHash == null) {
         this.documentHash = new ArrayList();
      }

      return this.documentHash;
   }

   public List<AnyType> getOther() {
      if (this.other == null) {
         this.other = new ArrayList();
      }

      return this.other;
   }
}
