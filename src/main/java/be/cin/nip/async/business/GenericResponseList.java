package be.cin.nip.async.business;

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
   name = "GenericResponseListType",
   propOrder = {"genericResponses"}
)
@XmlRootElement(
   name = "GenericResponseList"
)
public class GenericResponseList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenericResponse",
      required = true
   )
   protected List<GenericResponse> genericResponses;

   public List<GenericResponse> getGenericResponses() {
      if (this.genericResponses == null) {
         this.genericResponses = new ArrayList();
      }

      return this.genericResponses;
   }
}
