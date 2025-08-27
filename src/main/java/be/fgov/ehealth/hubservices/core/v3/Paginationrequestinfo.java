package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Paginationrequestinfo",
   propOrder = {"index"}
)
public class Paginationrequestinfo implements Serializable {
   private static final long serialVersionUID = 1L;
   protected int index;

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int value) {
      this.index = value;
   }
}
