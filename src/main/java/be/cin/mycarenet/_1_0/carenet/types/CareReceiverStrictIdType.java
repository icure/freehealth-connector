package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CareReceiverStrictIdType",
   propOrder = {"inss", "regNrWithMut", "mutuality"}
)
@XmlSeeAlso({ExtCareReceiverStrictIdType.class})
public class CareReceiverStrictIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Inss"
   )
   protected String inss;
   @XmlElement(
      name = "RegNrWithMut"
   )
   protected String regNrWithMut;
   @XmlElement(
      name = "Mutuality",
      required = true
   )
   protected String mutuality;

   public String getInss() {
      return this.inss;
   }

   public void setInss(String value) {
      this.inss = value;
   }

   public String getRegNrWithMut() {
      return this.regNrWithMut;
   }

   public void setRegNrWithMut(String value) {
      this.regNrWithMut = value;
   }

   public String getMutuality() {
      return this.mutuality;
   }

   public void setMutuality(String value) {
      this.mutuality = value;
   }
}
