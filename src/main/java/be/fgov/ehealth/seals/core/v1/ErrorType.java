package be.fgov.ehealth.seals.core.v1;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ErrorType",
   propOrder = {"id", "errorCode", "errorValue"}
)
public class ErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlElement(
      name = "ErrorCode",
      required = true
   )
   protected String errorCode;
   @XmlElement(
      name = "ErrorValue",
      required = true
   )
   protected String errorValue;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public void setErrorCode(String value) {
      this.errorCode = value;
   }

   public String getErrorValue() {
      return this.errorValue;
   }

   public void setErrorValue(String value) {
      this.errorValue = value;
   }
}
