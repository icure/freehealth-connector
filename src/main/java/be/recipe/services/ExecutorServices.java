package be.recipe.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(
   targetNamespace = "http://services.recipe.be",
   name = "ExecutorServices"
)
@XmlSeeAlso({ObjectFactory.class})
public interface ExecutorServices {
   @WebResult(
      name = "ListNotificationsResultSealed",
      targetNamespace = ""
   )
   @RequestWrapper(
      localName = "listNotifications",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.ListNotifications"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "listNotificationsResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.ListNotificationsResponse"
   )
   byte[] listNotifications(@WebParam(name = "ListNotificationsParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @RequestWrapper(
      localName = "markAsDelivered",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.MarkAsDelivered"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "markAsDeliveredResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.MarkAsDeliveredResponse"
   )
   void markAsDelivered(@WebParam(name = "MarkAsDeliveredParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @RequestWrapper(
      localName = "createFeedback",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.CreateFeedback"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "createFeedbackResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.CreateFeedbackResponse"
   )
   void createFeedback(@WebParam(name = "CreateFeedbackParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @WebResult(
      name = "PingResult",
      targetNamespace = ""
   )
   @RequestWrapper(
      localName = "ping",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.Ping"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "pingResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.PingResponse"
   )
   String ping();

   @WebResult(
      name = "GetPrescriptionForExecutorResultSealed",
      targetNamespace = ""
   )
   @RequestWrapper(
      localName = "getPrescriptionForExecutor",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.GetPrescriptionForExecutor"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "getPrescriptionForExecutorResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.GetPrescriptionForExecutorResponse"
   )
   byte[] getPrescriptionForExecutor(@WebParam(name = "GetPrescriptionForExecutorParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @RequestWrapper(
      localName = "markAsArchived",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.MarkAsArchived"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "markAsArchivedResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.MarkAsArchivedResponse"
   )
   void markAsArchived(@WebParam(name = "MarkAsArchivedParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @RequestWrapper(
      localName = "markAsUnDelivered",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.MarkAsUnDelivered"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "markAsUnDeliveredResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.MarkAsUnDeliveredResponse"
   )
   void markAsUnDelivered(@WebParam(name = "MarkAsUndeliveredParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @RequestWrapper(
      localName = "revokePrescription",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.RevokePrescription"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "revokePrescriptionResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.RevokePrescriptionResponse"
   )
   void revokePrescription(@WebParam(name = "RevokePrescriptionParamSealed", targetNamespace = "") byte[] var1, @WebParam(name = "PartyIdentificationParam", targetNamespace = "") PartyIdentification var2) throws RecipeException;

   @RequestWrapper(
      localName = "uploadFile",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.UploadFile"
   )
   @WebMethod
   @ResponseWrapper(
      localName = "uploadFileResponse",
      targetNamespace = "http://services.recipe.be",
      className = "be.recipe.services.UploadFileResponse"
   )
   void uploadFile(@WebParam(name = "UploadFileParam", targetNamespace = "") byte[] var1);
}
