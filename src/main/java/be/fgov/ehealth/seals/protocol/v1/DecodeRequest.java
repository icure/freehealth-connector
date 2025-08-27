package be.fgov.ehealth.seals.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.seals.core.v1.EncodedDataType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DecodeRequestType",
   propOrder = {"applicationName", "encodedDatas"}
)
@XmlRootElement(
   name = "DecodeRequest"
)
public class DecodeRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationName",
      required = true
   )
   protected String applicationName;
   @XmlElement(
      name = "EncodedData",
      required = true
   )
   protected List<EncodedDataType> encodedDatas;

   public String getApplicationName() {
      return this.applicationName;
   }

   public void setApplicationName(String value) {
      this.applicationName = value;
   }

   public List<EncodedDataType> getEncodedDatas() {
      if (this.encodedDatas == null) {
         this.encodedDatas = new ArrayList();
      }

      return this.encodedDatas;
   }
}
