package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"daynumbersAndQuantitiesAndDates"}
)
public class KmehrRegimen implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRefs({@XmlElementRef(
   name = "daytime",
   namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
   type = JAXBElement.class
), @XmlElementRef(
   name = "quantity",
   namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
   type = JAXBElement.class
), @XmlElementRef(
   name = "weekday",
   namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
   type = JAXBElement.class
), @XmlElementRef(
   name = "date",
   namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
   type = JAXBElement.class
), @XmlElementRef(
   name = "daynumber",
   namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
   type = JAXBElement.class
)})
   protected List<JAXBElement<?>> daynumbersAndQuantitiesAndDates;

   public List<JAXBElement<?>> getDaynumbersAndQuantitiesAndDates() {
      if (this.daynumbersAndQuantitiesAndDates == null) {
         this.daynumbersAndQuantitiesAndDates = new ArrayList();
      }

      return this.daynumbersAndQuantitiesAndDates;
   }
}
