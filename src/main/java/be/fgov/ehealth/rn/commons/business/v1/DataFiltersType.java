package be.fgov.ehealth.rn.commons.business.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DataFiltersType",
   propOrder = {"filteredElements"}
)
public class DataFiltersType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FilteredElement",
      required = true
   )
   protected List filteredElements;

   public List getFilteredElements() {
      if (this.filteredElements == null) {
         this.filteredElements = new ArrayList();
      }

      return this.filteredElements;
   }
}
