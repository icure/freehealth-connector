package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalitiesWithUpdateStatusType",
   propOrder = {"nationalities"}
)
public class NationalitiesWithUpdateStatusType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Nationality"
   )
   protected List<NationalityInfoBaseType> nationalities;
   @XmlAttribute(
      name = "Status"
   )
   protected String status;

   public List<NationalityInfoBaseType> getNationalities() {
      if (this.nationalities == null) {
         this.nationalities = new ArrayList();
      }

      return this.nationalities;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }
}
