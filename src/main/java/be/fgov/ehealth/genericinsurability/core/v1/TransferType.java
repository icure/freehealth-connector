package be.fgov.ehealth.genericinsurability.core.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TransferType",
   propOrder = {"insuranceOrg", "transferDate"}
)
public class TransferType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InsuranceOrg"
   )
   protected String insuranceOrg;
   @XmlElement(
      name = "TransferDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime transferDate;
   @XmlAttribute(
      name = "Direction",
      required = true
   )
   protected TransferDirectionType direction;

   public String getInsuranceOrg() {
      return this.insuranceOrg;
   }

   public void setInsuranceOrg(String value) {
      this.insuranceOrg = value;
   }

   public DateTime getTransferDate() {
      return this.transferDate;
   }

   public void setTransferDate(DateTime value) {
      this.transferDate = value;
   }

   public TransferDirectionType getDirection() {
      return this.direction;
   }

   public void setDirection(TransferDirectionType value) {
      this.direction = value;
   }
}
