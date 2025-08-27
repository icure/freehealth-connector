package be.fgov.ehealth.consultrn.ssinhistory.protocol.v1;

import be.fgov.ehealth.consultrn.ssinhistory.core.v1.RelatedSsinsType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultRelatedSsinsResponseType",
   propOrder = {"relatedSsins"}
)
@XmlRootElement(
   name = "ConsultRelatedSsinsResponse"
)
public class ConsultRelatedSsinsResponse extends ConsultCurrentSsinResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RelatedSsins"
   )
   protected RelatedSsinsType relatedSsins;

   public RelatedSsinsType getRelatedSsins() {
      return this.relatedSsins;
   }

   public void setRelatedSsins(RelatedSsinsType value) {
      this.relatedSsins = value;
   }
}
