package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "JudgmentType",
   propOrder = {"judgmentDate", "judgmentLocation"}
)
public class JudgmentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "JudgmentDate"
   )
   protected String judgmentDate;
   @XmlElement(
      name = "JudgmentLocation"
   )
   protected LocationType judgmentLocation;

   public String getJudgmentDate() {
      return this.judgmentDate;
   }

   public void setJudgmentDate(String value) {
      this.judgmentDate = value;
   }

   public LocationType getJudgmentLocation() {
      return this.judgmentLocation;
   }

   public void setJudgmentLocation(LocationType value) {
      this.judgmentLocation = value;
   }
}
