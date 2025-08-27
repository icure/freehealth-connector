package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.GenderHistoryType;
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
   name = "PersonHistoryGenderReplyType",
   propOrder = {"ssin", "genderHistories"}
)
@XmlRootElement(
   name = "PersonHistoryGenderReply"
)
public class PersonHistoryGenderReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "GenderHistory"
   )
   protected List<GenderHistoryType> genderHistories;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<GenderHistoryType> getGenderHistories() {
      if (this.genderHistories == null) {
         this.genderHistories = new ArrayList();
      }

      return this.genderHistories;
   }
}
