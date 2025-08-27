package be.fgov.ehealth.consultrn._1_0.core;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
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
   name = "RelationshipType",
   propOrder = {"code", "descriptions"}
)
public class RelationshipType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code"
   )
   @XmlSchemaType(
      name = "integer"
   )
   protected int code;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;

   public int getCode() {
      return this.code;
   }

   public void setCode(int value) {
      this.code = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
