package be.fgov.ehealth.samcivics.schemas.v2;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.ProfessionalAuthorizationType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetProfessionalAuthorizationsResponseType",
   propOrder = {"qualificationListId", "startDate", "endDate", "name", "exclusiveInd", "professionalAuthorizations"}
)
@XmlRootElement(
   name = "GetProfessionalAuthorizationsResponse"
)
public class GetProfessionalAuthorizationsResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "QualificationListId",
      required = true
   )
   protected String qualificationListId;
   @XmlElement(
      name = "StartDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlElement(
      name = "EndDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "ExclusiveInd"
   )
   protected String exclusiveInd;
   @XmlElement(
      name = "ProfessionalAuthorization"
   )
   protected List<ProfessionalAuthorizationType> professionalAuthorizations;

   public String getQualificationListId() {
      return this.qualificationListId;
   }

   public void setQualificationListId(String value) {
      this.qualificationListId = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getExclusiveInd() {
      return this.exclusiveInd;
   }

   public void setExclusiveInd(String value) {
      this.exclusiveInd = value;
   }

   public List<ProfessionalAuthorizationType> getProfessionalAuthorizations() {
      if (this.professionalAuthorizations == null) {
         this.professionalAuthorizations = new ArrayList();
      }

      return this.professionalAuthorizations;
   }
}
