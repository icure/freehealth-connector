package org.taktik.connector.business.domain.ehbox.fault;

import be.fgov.ehealth.errors.soa.v1.BusinessError;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DetailsType",
   propOrder = {"details"}
)
public class DetailsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BusinessError"
   )
   protected List<BusinessError> details;

   public List<BusinessError> getDetails() {
      if (this.details == null) {
         this.details = new ArrayList();
      }

      return this.details;
   }
}
