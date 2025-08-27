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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReportingBlobObj"
)
public class ReportingBlobObj {
   private String name;
   private List<Object[]> members = new ArrayList();

   public ReportingBlobObj() {
   }

   public ReportingBlobObj(List<Object[]> members, String name) {
      this.members = members;
      this.name = name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Object[]> getMembers() {
      return this.members;
   }

   public void setMembers(List<Object[]> members) {
      this.members = members;
   }

   public byte[] toXmlByteArray(List<Object[]> data) throws IOException, JAXBException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      JAXBContext jaxbContext = JAXBContext.newInstance(ReportingBlobObj.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(this, (OutputStream)bos);
      bos.close();
      return bos.toByteArray();
   }
}
