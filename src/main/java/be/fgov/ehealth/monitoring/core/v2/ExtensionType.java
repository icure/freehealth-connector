package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExtensionType",
   propOrder = {"elements"}
)
public class ExtensionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Element",
      required = true
   )
   protected List<ElementType> elements;

   public List<ElementType> getElements() {
      if (this.elements == null) {
         this.elements = new ArrayList();
      }

      return this.elements;
   }
}
