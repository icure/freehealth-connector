package oasis.names.tc.saml._2_0.assertion;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConditionsType",
   propOrder = {"conditionsAndAudienceRestrictionsAndOneTimeUses"}
)
@XmlRootElement(
   name = "Conditions"
)
public class Conditions implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "Condition"
), @XmlElement(
   name = "AudienceRestriction",
   type = AudienceRestriction.class
), @XmlElement(
   name = "OneTimeUse",
   type = OneTimeUse.class
), @XmlElement(
   name = "ProxyRestriction",
   type = ProxyRestriction.class
)})
   protected List<ConditionAbstractType> conditionsAndAudienceRestrictionsAndOneTimeUses;
   @XmlAttribute(
      name = "NotBefore"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime notBefore;
   @XmlAttribute(
      name = "NotOnOrAfter"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime notOnOrAfter;

   public List<ConditionAbstractType> getConditionsAndAudienceRestrictionsAndOneTimeUses() {
      if (this.conditionsAndAudienceRestrictionsAndOneTimeUses == null) {
         this.conditionsAndAudienceRestrictionsAndOneTimeUses = new ArrayList();
      }

      return this.conditionsAndAudienceRestrictionsAndOneTimeUses;
   }

   public DateTime getNotBefore() {
      return this.notBefore;
   }

   public void setNotBefore(DateTime value) {
      this.notBefore = value;
   }

   public DateTime getNotOnOrAfter() {
      return this.notOnOrAfter;
   }

   public void setNotOnOrAfter(DateTime value) {
      this.notOnOrAfter = value;
   }
}
