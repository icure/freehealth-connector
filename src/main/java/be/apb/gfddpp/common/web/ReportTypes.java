package be.apb.gfddpp.common.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReportTypes"
)
public class ReportTypes {
   @XmlElement(
      name = "ReportType"
   )
   private List<String> reportTypes;

   public ReportTypes() {
      this.reportTypes = new ArrayList();
   }

   public ReportTypes(List<String> reportTypes) {
      this();
      this.reportTypes = reportTypes;
   }

   public List<String> getReportTypes() {
      return this.reportTypes;
   }

   public byte[] toXmlByteArray() throws IOException, JAXBException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      JAXBContext jaxbContext = JAXBContext.newInstance(ReportTypes.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(this, (OutputStream)bos);
      bos.close();
      return bos.toByteArray();
   }
}
