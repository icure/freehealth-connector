package be.cin.nip.async.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "QueryParameters",
        propOrder = {"includeIOs", "excludeIOs", "tackMessageNames"}
)
public class QueryParameters implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlList
   @XmlElement(
           required = true
   )
   @XmlSchemaType(
           name = "anySimpleType"
   )
   protected List<String> includeIOs;
   @XmlList
   @XmlElement(
           required = true
   )
   @XmlSchemaType(
           name = "anySimpleType"
   )
   protected List<String> excludeIOs;
   @XmlList
   @XmlSchemaType(
           name = "anySimpleType"
   )
   protected List<String> tackMessageNames;
   @XmlAttribute(
           name = "Reference"
   )
   protected String reference;

   public List<String> getIncludeIOs() {
      if (this.includeIOs == null) {
         this.includeIOs = new ArrayList();
      }

      return this.includeIOs;
   }

   public List<String> getExcludeIOs() {
      if (this.excludeIOs == null) {
         this.excludeIOs = new ArrayList();
      }

      return this.excludeIOs;
   }

   public List<String> getTackMessageNames() {
      if (this.tackMessageNames == null) {
         this.tackMessageNames = new ArrayList();
      }

      return this.tackMessageNames;
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }
}
