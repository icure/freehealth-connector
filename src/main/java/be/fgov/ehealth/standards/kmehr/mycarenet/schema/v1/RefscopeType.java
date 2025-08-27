package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDREFSCOPE;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "refscopeType",
   propOrder = {"cd", "refvalues"}
)
public class RefscopeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDREFSCOPE cd;
   @XmlElement(
      name = "refvalue"
   )
   protected List<Refvalue> refvalues;

   public CDREFSCOPE getCd() {
      return this.cd;
   }

   public void setCd(CDREFSCOPE value) {
      this.cd = value;
   }

   public List<Refvalue> getRefvalues() {
      if (this.refvalues == null) {
         this.refvalues = new ArrayList();
      }

      return this.refvalues;
   }
}
