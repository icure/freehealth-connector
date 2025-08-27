package be.fgov.ehealth.dics.protocol.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AnomalyType",
   propOrder = {"code", "description", "targetObject", "targetReference"}
)
@XmlRootElement(
   name = "Anomaly"
)
public class Anomaly implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected String description;
   @XmlElement(
      name = "TargetObject",
      required = true
   )
   protected String targetObject;
   @XmlElement(
      name = "TargetReference",
      required = true
   )
   protected String targetReference;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }

   public String getTargetObject() {
      return this.targetObject;
   }

   public void setTargetObject(String value) {
      this.targetObject = value;
   }

   public String getTargetReference() {
      return this.targetReference;
   }

   public void setTargetReference(String value) {
      this.targetReference = value;
   }
}
