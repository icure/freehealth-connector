package org.etsi.uri._01903.v1_4;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.etsi.uri._01903.v1_3.XAdESTimeStampType;

@XmlRegistry
public class ObjectFactory {
   private static final QName _ArchiveTimeStampV2_QNAME = new QName("http://uri.etsi.org/01903/v1.4.1#", "ArchiveTimeStampV2");

   public TimeStampValidationData createTimeStampValidationData() {
      return new TimeStampValidationData();
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.4.1#",
      name = "ArchiveTimeStampV2"
   )
   public JAXBElement<XAdESTimeStampType> createArchiveTimeStampV2(XAdESTimeStampType value) {
      return new JAXBElement(_ArchiveTimeStampV2_QNAME, XAdESTimeStampType.class, (Class)null, value);
   }
}
