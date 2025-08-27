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
   name = "GetOoOListResponseType",
   propOrder = {"ooOs"}
)
@XmlRootElement(
   name = "GetOoOListResponse"
)
public class GetOoOListResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OoO"
   )
   protected List<OoO> ooOs;

   public List<OoO> getOoOs() {
      if (this.ooOs == null) {
         this.ooOs = new ArrayList();
      }

      return this.ooOs;
   }
}
