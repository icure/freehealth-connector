package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityInfoWithSourceType"
)
public class NationalityInfoWithSourceType extends NationalityInfoBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Source"
   )
   protected String source;

   public String getSource() {
      return this.source;
   }

   public void setSource(String value) {
      this.source = value;
   }
}
