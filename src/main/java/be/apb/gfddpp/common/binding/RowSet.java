package be.apb.gfddpp.common.binding;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "rowSet",
   propOrder = {"row"}
)
public class RowSet {
   @XmlElement(
      required = true
   )
   protected List<TipSystemAudit> row;

   public List<TipSystemAudit> getRow() {
      if (this.row == null) {
         this.row = new ArrayList();
      }

      return this.row;
   }
}
