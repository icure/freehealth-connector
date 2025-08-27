package be.ehealth.apb.gfddpp.services.pcdh;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UploadPerformanceMetricRequestType",
   propOrder = {"uploadPerformanceMetricParamSealed"}
)
public class UploadPerformanceMetricRequestType extends RequestType {
   @XmlElement(
      name = "UploadPerformanceMetricParamSealed",
      required = true
   )
   protected byte[] uploadPerformanceMetricParamSealed;

   public byte[] getUploadPerformanceMetricParamSealed() {
      return this.uploadPerformanceMetricParamSealed;
   }

   public void setUploadPerformanceMetricParamSealed(byte[] var1) {
      this.uploadPerformanceMetricParamSealed = (byte[])var1;
   }
}
