package be.fgov.ehealth.services.schematron.impl;

import be.fgov.ehealth.schematron.SchematronSession;
import be.fgov.ehealth.schematron.SchematronSessionFactory;
import be.fgov.ehealth.schematron.domain.SchematronConfig;
import be.fgov.ehealth.schematron.domain.SchematronResult;
import be.fgov.ehealth.schematron.exception.InitialisationException;
import java.io.InputStream;
import jakarta.xml.bind.JAXBException;

import be.fgov.ehealth.services.schematron.SchematronValidationResult;
import be.fgov.ehealth.services.schematron.SchematronValidator;
import org.oclc.purl.dsdl.svrl.SchematronOutput;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorIOUtils;

public class SchematronValidatorImpl implements SchematronValidator {
   public SchematronValidatorImpl() {
   }

   public SchematronValidationResult validate(String inputLocation, String schematronLocation) throws TechnicalConnectorException, InitialisationException {
      return this.validate(ConnectorIOUtils.getResourceAsStream(inputLocation), ConnectorIOUtils.getResourceAsStream(schematronLocation));
   }

   public SchematronValidationResult validate(InputStream input, InputStream schematron) throws TechnicalConnectorException, InitialisationException {
      SchematronConfig config = new SchematronConfig();
      config.setQueryLanguageBinding("xslt2");
      config.setSchema(schematron);
      config.setDebugMode(true);
      SchematronSession session = SchematronSessionFactory.newInstance(config);

      try {
         SchematronSessionFactory.initSaxon();
         SchematronResult result = session.validate(input);
         SchematronSessionFactory.stopSaxon();
         return new SchematronValidationResultImpl(result);
      } catch (Exception var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var6, new Object[0]);
      }
   }

   private static class SchematronValidationResultImpl implements SchematronValidationResult {
      private SchematronResult result;

      public SchematronValidationResultImpl(SchematronResult result) {
         this.result = result;
      }

      public boolean isValid() {
         return this.result.isValid();
      }

      public String[] getReportMessages() {
         return this.result.getReportMessages();
      }

      public String[] getFailedMessages() {
         return this.result.getFailedMessages();
      }

      public SchematronOutput getSVRL() throws TechnicalConnectorException {
         try {
            return this.result.getSVRL();
         } catch (JAXBException var2) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var2, new Object[0]);
         }
      }

      public String getSVRLAsString() {
         return this.result.getSVRLAsString();
      }
   }
}
