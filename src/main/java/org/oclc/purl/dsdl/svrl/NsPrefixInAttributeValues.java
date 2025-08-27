package org.oclc.purl.dsdl.svrl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = ""
)
@XmlRootElement(
   name = "ns-prefix-in-attribute-values"
)
public class NsPrefixInAttributeValues {
   @XmlAttribute(
      name = "prefix",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String prefix;
   @XmlAttribute(
      name = "uri",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String uri;

   public NsPrefixInAttributeValues() {
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String value) {
      this.prefix = value;
   }

   public String getUri() {
      return this.uri;
   }

   public void setUri(String value) {
      this.uri = value;
   }
}
