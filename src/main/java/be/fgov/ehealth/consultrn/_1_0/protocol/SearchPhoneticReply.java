package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.PersonType;
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
   name = "SearchPhoneticReplyType",
   propOrder = {"persons"}
)
@XmlRootElement(
   name = "SearchPhoneticReply"
)
public class SearchPhoneticReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Person"
   )
   protected List<PersonType> persons;

   public List<PersonType> getPersons() {
      if (this.persons == null) {
         this.persons = new ArrayList();
      }

      return this.persons;
   }
}
