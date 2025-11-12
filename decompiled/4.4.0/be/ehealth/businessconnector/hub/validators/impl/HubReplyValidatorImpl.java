package be.ehealth.businessconnector.hub.validators.impl;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorExceptionValues;
import be.ehealth.business.intrahubcommons.exception.KmehrBusinessConnectorException;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.fgov.ehealth.hubservices.core.v1.AcknowledgeType;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDERROR;
import be.fgov.ehealth.standards.kmehr.schema.v1.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HubReplyValidatorImpl implements HubReplyValidator {
   private static final Logger LOG = LoggerFactory.getLogger(HubReplyValidatorImpl.class);
   private static final String LF = System.getProperty("line.separator");

   public HubReplyValidatorImpl() {
   }

   public void validate(AcknowledgeType acknowledge) throws KmehrBusinessConnectorException {
      if (!acknowledge.getErrors().isEmpty() && !acknowledge.isIscomplete()) {
         StringBuilder errorMsgBuilder = new StringBuilder("Reply contains errors: ");
         LOG.error("Acknowledge contains following errorcd:");

         for(ErrorType errorType : acknowledge.getErrors()) {
            for(CDERROR cdError : errorType.getCds()) {
               LOG.error(" . cd: " + cdError.getValue());
            }

            errorMsgBuilder.append(LF);
            if (errorType.getDescription() != null) {
               errorMsgBuilder.append(" . " + errorType.getDescription().getValue());
            }
         }

         throw new KmehrBusinessConnectorException(IntraHubBusinessConnectorExceptionValues.VALIDATION_ERROR, acknowledge.getErrors(), new Object[]{errorMsgBuilder.toString()});
      }
   }
}
