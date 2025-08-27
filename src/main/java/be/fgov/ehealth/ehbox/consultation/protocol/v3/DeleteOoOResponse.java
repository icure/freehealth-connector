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
   name = "DeleteOoOResponseType",
   propOrder = {"ooOIds"}
)
@XmlRootElement(
   name = "DeleteOoOResponse"
)
public class DeleteOoOResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OoOId"
   )
   protected List<String> ooOIds;

   public List<String> getOoOIds() {
      if (this.ooOIds == null) {
         this.ooOIds = new ArrayList();
      }

      return this.ooOIds;
   }
}
