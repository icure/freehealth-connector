package be.cin.mycarenet._1_0.carenet.types;

import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TransferType",
   propOrder = {"insuranceOrg", "transferDate"}
)
@XmlRootElement(
   name = "Transfer"
)
public class Transfer implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InsuranceOrg"
   )
   protected String insuranceOrg;
   @XmlElement(
      name = "TransferDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
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
