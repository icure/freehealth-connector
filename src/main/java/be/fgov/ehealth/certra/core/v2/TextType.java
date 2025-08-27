package be.fgov.ehealth.certra.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TextType",
   propOrder = {"values"}
)
public class TextType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Value",
      required = true
   )
   protected List<LocalizedString> values;

   public List<LocalizedString> getValues() {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      return this.values;
   }
}
