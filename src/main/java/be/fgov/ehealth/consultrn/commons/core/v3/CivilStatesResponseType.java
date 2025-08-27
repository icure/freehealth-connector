package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilStatesResponseType",
   propOrder = {"civilStates"}
)
public class CivilStatesResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilState",
      required = true
   )
   protected List<CivilStateResponseType> civilStates;

   public List<CivilStateResponseType> getCivilStates() {
      if (this.civilStates == null) {
         this.civilStates = new ArrayList();
      }

      return this.civilStates;
   }
}
