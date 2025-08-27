package be.fgov.ehealth.globalmedicalfile.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"isTest"}
)
public class RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IsTest"
   )
   protected boolean isTest;

   public boolean isIsTest() {
      return this.isTest;
   }

   public void setIsTest(boolean value) {
      this.isTest = value;
   }
}
