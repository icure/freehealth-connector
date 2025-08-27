package be.apb.standards.smoa.schema.model.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TargetType",
   propOrder = {"targetType", "targetId"}
)
public class TargetType {
   @XmlElement(
      name = "TargetType",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String targetType;
   @XmlElement(
      name = "TargetId",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String targetId;

   public String getTargetType() {
      return this.targetType;
   }

   public void setTargetType(String value) {
      this.targetType = value;
   }

   public String getTargetId() {
      return this.targetId;
   }

   public void setTargetId(String value) {
      this.targetId = value;
   }
}
