package be.fgov.ehealth.recipe.protocol.v4;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType"
)
@XmlSeeAlso({GetOpenPrescriptionListRequestType.class, ListPrescriptionHistoryRequestType.class, PutFeedbackFlagRequest.class, UploadFileRequest.class, GetPrescriptionForExecutorRequest.class, ListRelationsRequest.class, RevokePrescriptionRequest.class, GetPrescriptionStatusRequest.class, GetVisionRequest.class, ListRidsHistoryRequest.class, CreateFeedbackRequest.class, MarkAsUnDeliveredRequest.class, GetValidationPropertiesRequest.class, MarkAsDeliveredRequest.class, ListRidsInProcessRequest.class, GetPrescriptionRequest.class, RevokeRelationRequest.class, ListMandatesRequest.class, ListReservationsRequest.class, CreatePrescriptionRequest.class, PutVisionForPrescriberRequest.class, CreateReservationRequest.class, ListFeedbacksRequest.class, ListOpenPrescriptionsRequest.class, PutRidsInProcessRequest.class, ListNotificationsRequest.class, PutVisionForPatientRequest.class, SendNotificationRequest.class, MarkAsArchivedRequest.class, CreateRelationRequest.class, ListOpenRidsRequest.class})
public class RequestType extends be.fgov.ehealth.commons.protocol.v2.RequestType {
   @XmlAttribute(
      name = "ProgramId",
      required = true
   )
   protected String programId;

   public String getProgramId() {
      return this.programId;
   }

   public void setProgramId(String value) {
      this.programId = value;
   }
}
