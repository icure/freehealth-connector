package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SchemasType",
   propOrder = {"schemas"}
)
@XmlRootElement(
   name = "Schemas"
)
public class Schemas implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Schema",
      required = true
   )
   protected List<DocumentType> schemas;

   public List<DocumentType> getSchemas() {
      if (this.schemas == null) {
         this.schemas = new ArrayList();
      }

      return this.schemas;
   }
}
