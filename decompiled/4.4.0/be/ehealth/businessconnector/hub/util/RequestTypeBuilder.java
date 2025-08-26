package be.ehealth.businessconnector.hub.util;

import be.ehealth.business.intrahubcommons.helper.RequestBuilderHelper;
import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v1.RequestType;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestTypeBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(RequestTypeBuilder.class);
   private static final String PROJECT_NAME = "intrahub";
   private static final String HUB_SESSION_IS_CBE = "hub.session.is-cbe";
   private static final String HUB_REQUEST_CREATE_AUTHOR = "hub.request.create-author";
   private static final Configuration config = ConfigFactory.getConfigValidator();
   private RequestType request;

   public static RequestTypeBuilder init() throws TechnicalConnectorException {
      RequestTypeBuilder requestTypeBuilder = new RequestTypeBuilder();
      RequestType newRequest = new RequestType();
      newRequest.setDate(new DateTime());
      newRequest.setTime(new DateTime());
      newRequest.setId(RequestBuilderHelper.createKmehrId("intrahub", "hub.session.is-cbe"));
      requestTypeBuilder.request = newRequest;
      return requestTypeBuilder;
   }

   public RequestTypeBuilder addGenericAuthor() throws TechnicalConnectorException {
      if (config.getBooleanProperty("hub.request.create-author", true)) {
         this.request.setAuthor(HcPartyUtil.createAuthor("intrahub"));
      }

      return this;
   }

   public RequestTypeBuilder addAuthorWithEncryptionInformation() throws TechnicalConnectorException {
      if (config.getBooleanProperty("hub.request.create-author", true)) {
         AuthorType author = HcPartyUtil.createAuthor("intrahub");
         HcPartyUtil.addSecurityTags(author, "intrahub");
         this.request.setAuthor(author);
      }

      return this;
   }

   public RequestType build() {
      return this.request;
   }

   private RequestTypeBuilder() {
   }
}
