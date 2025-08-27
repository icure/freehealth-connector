package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"rows"}
)
public class Table implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Row",
      required = true
   )
   protected List<Row> rows;
   @XmlAttribute(
      name = "Title"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String title;

   public List<Row> getRows() {
      if (this.rows == null) {
         this.rows = new ArrayList();
      }

      return this.rows;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String value) {
      this.title = value;
   }
}
