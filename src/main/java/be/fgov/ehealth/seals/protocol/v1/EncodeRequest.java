package be.fgov.ehealth.seals.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.seals.core.v1.OriginalDataType;
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
   name = "EncodeRequestType",
   propOrder = {"applicationName", "originalDatas"}
)
@XmlRootElement(
   name = "EncodeRequest"
)
public class EncodeRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationName",
      required = true
   )
   protected String applicationName;
   @XmlElement(
      name = "OriginalData",
      required = true
   )
   protected List<OriginalDataType> originalDatas;

   public String getApplicationName() {
      return this.applicationName;
   }

   public void setApplicationName(String value) {
      this.applicationName = value;
   }

   public List<OriginalDataType> getOriginalDatas() {
      if (this.originalDatas == null) {
         this.originalDatas = new ArrayList();
      }

      return this.originalDatas;
   }
}
