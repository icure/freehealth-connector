package be.apb.standards.smoa.schema.id.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RizivIdType",
   propOrder = {"riziv"}
)
public class RizivIdType extends AbstractCareProviderIdType {
   @XmlElement(
      required = true
   )
   protected String riziv;

   public String getRiziv() {
      return this.riziv;
   }

   public void setRiziv(String value) {
      this.riziv = value;
   }
}
