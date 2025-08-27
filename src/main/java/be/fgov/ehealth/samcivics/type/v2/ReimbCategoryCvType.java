package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbCategoryCvType",
   propOrder = {"reimbCategoryCv", "name"}
)
public class ReimbCategoryCvType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReimbCategoryCv",
      required = true
   )
   protected String reimbCategoryCv;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;

   public String getReimbCategoryCv() {
      return this.reimbCategoryCv;
   }

   public void setReimbCategoryCv(String value) {
      this.reimbCategoryCv = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
