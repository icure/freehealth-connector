package be.apb.gfddpp.servicerouter;

import java.util.ArrayList;
import java.util.List;
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
   propOrder = {"contractType"}
)
@XmlRootElement(
   name = "contract-types"
)
public class ContractTypes {
   @XmlElement(
      name = "contract-type"
   )
   protected List<ContractType> contractType;

   public List<ContractType> getContractType() {
      if (this.contractType == null) {
         this.contractType = new ArrayList();
      }

      return this.contractType;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"name", "description", "serviceName", "eventName", "serviceRoutings"}
   )
   public static class ContractType {
      @XmlElement(
         required = true
      )
      protected String name;
      @XmlElement(
         required = true
      )
      protected String description;
      @XmlElement(
         name = "service-name",
         required = true
      )
      protected String serviceName;
      @XmlElement(
         name = "event-name",
         required = true
      )
      protected String eventName;
      @XmlElement(
         name = "service-routings",
         required = true
      )
      protected ServiceRoutings serviceRoutings;

      public String getName() {
         return this.name;
      }

      public void setName(String value) {
         this.name = value;
      }

      public String getDescription() {
         return this.description;
      }

      public void setDescription(String value) {
         this.description = value;
      }

      public String getServiceName() {
         return this.serviceName;
      }

      public void setServiceName(String value) {
         this.serviceName = value;
      }

      public String getEventName() {
         return this.eventName;
      }

      public void setEventName(String value) {
         this.eventName = value;
      }

      public ServiceRoutings getServiceRoutings() {
         return this.serviceRoutings;
      }

      public void setServiceRoutings(ServiceRoutings value) {
         this.serviceRoutings = value;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"serviceRouting"}
      )
      public static class ServiceRoutings {
         @XmlElement(
            name = "service-routing"
         )
         protected List<ServiceRouting> serviceRouting;

         public List<ServiceRouting> getServiceRouting() {
            if (this.serviceRouting == null) {
               this.serviceRouting = new ArrayList();
            }

            return this.serviceRouting;
         }

         @XmlAccessorType(XmlAccessType.FIELD)
         @XmlType(
            name = "",
            propOrder = {"pharmacyId", "startDate", "endDate"}
         )
         public static class ServiceRouting {
            @XmlElement(
               name = "pharmacy-id",
               required = true
            )
            protected String pharmacyId;
            @XmlElement(
               name = "start-date",
               required = true
            )
            @XmlSchemaType(
               name = "dateTime"
            )
            protected XMLGregorianCalendar startDate;
            @XmlElement(
               name = "end-date",
               required = true
            )
            @XmlSchemaType(
               name = "dateTime"
            )
            protected XMLGregorianCalendar endDate;

            public String getPharmacyId() {
               return this.pharmacyId;
            }

            public void setPharmacyId(String value) {
               this.pharmacyId = value;
            }

            public XMLGregorianCalendar getStartDate() {
               return this.startDate;
            }

            public void setStartDate(XMLGregorianCalendar value) {
               this.startDate = value;
            }

            public XMLGregorianCalendar getEndDate() {
               return this.endDate;
            }

            public void setEndDate(XMLGregorianCalendar value) {
               this.endDate = value;
            }
         }
      }
   }
}
