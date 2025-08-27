package be.apb.standards.smoa.schema.model.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DataLocationType",
   propOrder = {"abstractPatient", "location"}
)
public class DataLocationType extends AbstractDataLocationType {
   @XmlElementRef(
      name = "abstract-Patient",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractPatientType> abstractPatient;
   protected List<Location> location;

   public JAXBElement<? extends AbstractPatientType> getAbstractPatient() {
      return this.abstractPatient;
   }

   public void setAbstractPatient(JAXBElement<? extends AbstractPatientType> value) {
      this.abstractPatient = value;
   }

   public List<Location> getLocation() {
      if (this.location == null) {
         this.location = new ArrayList();
      }

      return this.location;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"system", "careDataType"}
   )
   public static class Location {
      @XmlElement(
         required = true
      )
      @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
      @XmlSchemaType(
         name = "token"
      )
      protected String system;
      @XmlElement(
         required = true
      )
      @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
      @XmlSchemaType(
         name = "token"
      )
      protected List<String> careDataType;

      public String getSystem() {
         return this.system;
      }

      public void setSystem(String value) {
         this.system = value;
      }

      public List<String> getCareDataType() {
         if (this.careDataType == null) {
            this.careDataType = new ArrayList();
         }

         return this.careDataType;
      }
   }
}
