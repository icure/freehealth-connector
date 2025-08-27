package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SsinWithCanceledAndReplacedByStatusType",
   propOrder = {"value"}
)
public class SsinWithCanceledAndReplacedByStatusType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Canceled"
   )
   protected Boolean canceled;
   @XmlAttribute(
      name = "ReplacedBy"
   )
   protected String replacedBy;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public Boolean isCanceled() {
      return this.canceled;
   }

   public void setCanceled(Boolean value) {
      this.canceled = value;
   }

   public String getReplacedBy() {
      return this.replacedBy;
   }

   public void setReplacedBy(String value) {
      this.replacedBy = value;
   }
}
