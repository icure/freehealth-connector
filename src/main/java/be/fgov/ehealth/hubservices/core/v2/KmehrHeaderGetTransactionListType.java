package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KmehrHeaderGetTransactionListType",
   propOrder = {"folder"}
)
public class KmehrHeaderGetTransactionListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected FolderTypeUnbounded folder;

   public FolderTypeUnbounded getFolder() {
      return this.folder;
   }

   public void setFolder(FolderTypeUnbounded value) {
      this.folder = value;
   }
}
