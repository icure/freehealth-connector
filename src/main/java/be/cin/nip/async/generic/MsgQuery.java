package be.cin.nip.async.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MsgQuery",
   propOrder = {"messageNames"}
)
public class MsgQuery extends Query implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlList
   @XmlElement(
      name = "MessageNames",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected List<String> messageNames;

   public List<String> getMessageNames() {
      if (this.messageNames == null) {
         this.messageNames = new ArrayList();
      }

      return this.messageNames;
   }
}
