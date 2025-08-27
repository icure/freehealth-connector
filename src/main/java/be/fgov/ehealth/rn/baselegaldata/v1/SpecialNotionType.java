package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SpecialNotionType",
   propOrder = {"specialNotionCode", "specialNotionDescriptions"}
)
public class SpecialNotionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SpecialNotionCode"
   )
   protected String specialNotionCode;
   @XmlElement(
      name = "SpecialNotionDescription"
   )
   protected List<SpecialNotionType> specialNotionDescriptions;

   public String getSpecialNotionCode() {
      return this.specialNotionCode;
   }

   public void setSpecialNotionCode(String value) {
      this.specialNotionCode = value;
   }

   public List<SpecialNotionType> getSpecialNotionDescriptions() {
      if (this.specialNotionDescriptions == null) {
         this.specialNotionDescriptions = new ArrayList();
      }

      return this.specialNotionDescriptions;
   }
}
