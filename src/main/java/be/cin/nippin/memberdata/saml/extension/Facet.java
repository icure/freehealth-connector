package be.cin.nippin.memberdata.saml.extension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"dimensions"}
)
@XmlRootElement(
   name = "Facet"
)
public class Facet implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Dimension",
      namespace = ""
   )
   protected List<Dimension> dimensions;
   @XmlAttribute(
      name = "id",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String id;

   public List<Dimension> getDimensions() {
      if (this.dimensions == null) {
         this.dimensions = new ArrayList();
      }

      return this.dimensions;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
