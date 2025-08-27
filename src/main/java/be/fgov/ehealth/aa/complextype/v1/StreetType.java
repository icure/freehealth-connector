package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StreetType",
   propOrder = {"nsiCode", "descriptions"}
)
public class StreetType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NSICode"
   )
   protected String nsiCode;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected List<Description> descriptions;

   public String getNSICode() {
      return this.nsiCode;
   }

   public void setNSICode(String value) {
      this.nsiCode = value;
   }

   public List<Description> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
