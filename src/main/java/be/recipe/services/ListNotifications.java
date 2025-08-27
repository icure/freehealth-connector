package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listNotifications",
   propOrder = {"listNotificationsParamSealed", "partyIdentificationParam"}
)
@XmlRootElement(
   name = "listNotifications"
)
public class ListNotifications implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ListNotificationsParamSealed"
   )
   protected byte[] listNotificationsParamSealed;
   @XmlElement(
      name = "PartyIdentificationParam"
   )
   protected PartyIdentification partyIdentificationParam;

   public byte[] getListNotificationsParamSealed() {
      return this.listNotificationsParamSealed;
   }

   public void setListNotificationsParamSealed(byte[] value) {
      this.listNotificationsParamSealed = (byte[])value;
   }

   public PartyIdentification getPartyIdentificationParam() {
      return this.partyIdentificationParam;
   }

   public void setPartyIdentificationParam(PartyIdentification value) {
      this.partyIdentificationParam = value;
   }
}
