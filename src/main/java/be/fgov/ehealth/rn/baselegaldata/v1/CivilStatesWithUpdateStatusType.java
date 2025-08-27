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
   name = "CivilStatesWithUpdateStatusType",
   propOrder = {"civilStates"}
)
public class CivilStatesWithUpdateStatusType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilState"
   )
   protected List<CivilStateInfoBaseType> civilStates;
   @XmlAttribute(
      name = "Status"
   )
   protected String status;

   public List<CivilStateInfoBaseType> getCivilStates() {
      if (this.civilStates == null) {
         this.civilStates = new ArrayList();
      }

      return this.civilStates;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }
}
