package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExtCareReceiverStrictIdType",
   propOrder = {"sex"}
)
public class ExtCareReceiverStrictIdType extends CareReceiverStrictIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Sex",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected SexType sex;

   public SexType getSex() {
      return this.sex;
   }

   public void setSex(SexType value) {
      this.sex = value;
   }
}
