package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.NationalityHistoryType;
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
   name = "PersonHistoryNationalityReplyType",
   propOrder = {"ssin", "nationalityHistories"}
)
@XmlRootElement(
   name = "PersonHistoryNationalityReply"
)
public class PersonHistoryNationalityReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "NationalityHistory"
   )
   protected List<NationalityHistoryType> nationalityHistories;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<NationalityHistoryType> getNationalityHistories() {
      if (this.nationalityHistories == null) {
         this.nationalityHistories = new ArrayList();
      }

      return this.nationalityHistories;
   }
}
