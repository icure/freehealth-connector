package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalDeceaseType",
   propOrder = {"deceaseDate", "deceasePlace"}
)
@XmlSeeAlso({DeceaseInfoBaseType.class, DeceaseInfoWithUpdateStatusType.class, DeceaseInfoWithStatusAndSourceType.class})
public abstract class AbstractOptionalDeceaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DeceaseDate"
   )
   protected String deceaseDate;
   @XmlElement(
      name = "DeceasePlace"
   )
   protected LocationType deceasePlace;

   public String getDeceaseDate() {
      return this.deceaseDate;
   }

   public void setDeceaseDate(String value) {
      this.deceaseDate = value;
   }

   public LocationType getDeceasePlace() {
      return this.deceasePlace;
   }

   public void setDeceasePlace(LocationType value) {
      this.deceasePlace = value;
   }
}
