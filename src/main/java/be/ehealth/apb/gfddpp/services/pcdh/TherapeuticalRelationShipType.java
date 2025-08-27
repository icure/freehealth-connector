package be.ehealth.apb.gfddpp.services.pcdh;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapeuticalRelationShipType",
   namespace = "urn:be:fgov:ehealth:gfddpp:core:v1",
   propOrder = {"therapeuticalRelationShipFlag"}
)
public class TherapeuticalRelationShipType {
   @XmlElement(
      name = "TherapeuticalRelationShipFlag"
   )
   protected boolean therapeuticalRelationShipFlag;

   public boolean isTherapeuticalRelationShipFlag() {
      return this.therapeuticalRelationShipFlag;
   }

   public void setTherapeuticalRelationShipFlag(boolean var1) {
      this.therapeuticalRelationShipFlag = var1;
   }
}
