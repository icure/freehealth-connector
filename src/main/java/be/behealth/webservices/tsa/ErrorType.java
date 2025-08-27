package be.behealth.webservices.tsa;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ErrorType",
   propOrder = {"type", "number"}
)
public class ErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "Number",
      required = true
   )
   protected BigInteger number;

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public BigInteger getNumber() {
      return this.number;
   }

   public void setNumber(BigInteger value) {
      this.number = value;
   }
}
