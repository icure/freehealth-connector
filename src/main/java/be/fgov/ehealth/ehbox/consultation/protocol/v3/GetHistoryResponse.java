package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
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
   name = "GetHistoryResponseType",
   propOrder = {"messageIds"}
)
@XmlRootElement(
   name = "GetHistoryResponse"
)
public class GetHistoryResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MessageId"
   )
   protected List<String> messageIds;

   public List<String> getMessageIds() {
      if (this.messageIds == null) {
         this.messageIds = new ArrayList();
      }

      return this.messageIds;
   }
}
