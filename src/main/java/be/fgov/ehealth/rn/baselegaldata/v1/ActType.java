package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ActType",
   propOrder = {"actTypeCode", "actTypeDescriptions"}
)
public class ActType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ActTypeCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer actTypeCode;
   @XmlElement(
      name = "ActTypeDescription"
   )
   protected List<ActType> actTypeDescriptions;

   public Integer getActTypeCode() {
      return this.actTypeCode;
   }

   public void setActTypeCode(Integer value) {
      this.actTypeCode = value;
   }

   public List<ActType> getActTypeDescriptions() {
      if (this.actTypeDescriptions == null) {
         this.actTypeDescriptions = new ArrayList();
      }

      return this.actTypeDescriptions;
   }
}
