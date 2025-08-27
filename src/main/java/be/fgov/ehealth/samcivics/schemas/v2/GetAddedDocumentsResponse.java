package be.fgov.ehealth.samcivics.schemas.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.AddedDocumentType;
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
   name = "GetAddedDocumentsResponseType",
   propOrder = {"addedDocuments"}
)
@XmlRootElement(
   name = "GetAddedDocumentsResponse"
)
public class GetAddedDocumentsResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AddedDocument",
      required = true
   )
   protected List<AddedDocumentType> addedDocuments;

   public List<AddedDocumentType> getAddedDocuments() {
      if (this.addedDocuments == null) {
         this.addedDocuments = new ArrayList();
      }

      return this.addedDocuments;
   }
}
