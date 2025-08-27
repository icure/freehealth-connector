package be.fgov.ehealth.insurability.core.v2;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReturnInfoType",
   propOrder = {"returnCode", "multipleIO"}
)
public class ReturnInfoType {
   @XmlElement(
      name = "ReturnCode",
      required = true
   )
   protected ReturnCodeType returnCode;
   @XmlList
   @XmlElement(
      name = "MultipleIO"
   )
   protected List<String> multipleIO;

   public ReturnCodeType getReturnCode() {
      return this.returnCode;
   }

   public void setReturnCode(ReturnCodeType value) {
      this.returnCode = value;
   }

   public List<String> getMultipleIO() {
      if (this.multipleIO == null) {
         this.multipleIO = new ArrayList();
      }

      return this.multipleIO;
   }
}
