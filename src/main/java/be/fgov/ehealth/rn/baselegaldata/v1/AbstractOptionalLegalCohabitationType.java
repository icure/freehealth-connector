package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractOptionalLegalCohabitationType",
   propOrder = {"partner", "registration", "inceptionDate"}
)
@XmlSeeAlso({LegalCohabitationBaseType.class, LegalCohabitationWithStatusAndSourceType.class})
public abstract class AbstractOptionalLegalCohabitationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Partner"
   )
   protected PartnerType partner;
   @XmlElement(
      name = "Registration"
   )
   protected LegalCohabitationRegistrationType registration;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public PartnerType getPartner() {
      return this.partner;
   }

   public void setPartner(PartnerType value) {
      this.partner = value;
   }

   public LegalCohabitationRegistrationType getRegistration() {
      return this.registration;
   }

   public void setRegistration(LegalCohabitationRegistrationType value) {
      this.registration = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
