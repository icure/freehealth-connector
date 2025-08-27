package be.gfddpp.services.systemservices.v2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"version", "systemServicesList"}
)
@XmlRootElement(
   name = "systemServices"
)
public class SystemServices {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar version;
   @XmlElement(
      required = true
   )
   protected SystemServicesList systemServicesList;

   public XMLGregorianCalendar getVersion() {
      return this.version;
   }

   public void setVersion(XMLGregorianCalendar value) {
      this.version = value;
   }

   public SystemServicesList getSystemServicesList() {
      return this.systemServicesList;
   }

   public void setSystemServicesList(SystemServicesList value) {
      this.systemServicesList = value;
   }
}
