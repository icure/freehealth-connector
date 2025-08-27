package be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.regional;

import be.ehealth.businessconnector.mycarenet.memberdatav2.service.impl.MemberDataServiceImpl;
import be.ehealth.businessconnector.mycarenet.memberdatav2.session.MemberDataService;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest;
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse;

public class MemberDataWalCareNetService implements MemberDataService {
   private be.ehealth.businessconnector.mycarenet.memberdatav2.service.MemberDataService service;
   private SessionValidator sessionValidator;

   public MemberDataWalCareNetService(SessionValidator sessionValidator) throws TechnicalConnectorException {
      this.service = new MemberDataServiceImpl(sessionValidator, "$uddi{uddi:ehealth-fgov-be:business:walcarenetmemberdata:v1}");
      this.sessionValidator = sessionValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public MemberDataConsultationResponse consultMemberData(MemberDataConsultationRequest request) throws TechnicalConnectorException {
      this.sessionValidator.validateSession();
      return this.service.consultMemberData(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
