package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KmehrHeaderDeclareTransactionType",
   propOrder = {"folder"}
)
@XmlRootElement(
   name = "KmehrHeaderDeclareTransaction"
)
public class KmehrHeaderDeclareTransaction implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected FolderType folder;

   public FolderType getFolder() {
      return this.folder;
   }

   public void setFolder(FolderType value) {
      this.folder = value;
   }
}
