package be.ehealth.businessconnector.hubv3.util;

import be.ehealth.business.intrahubcommons.helper.RequestBuilderHelper;
import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v3.RequestType;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import java.math.BigDecimal;
import org.joda.time.DateTime;

public class RequestTypeBuilder {
   public static final String PROJECT_NAME = "hubservicev3";
   private static final String HUBV_3_SESSION_IS_CBE = "hubv3.session.is-cbe";
   private static final String HUBV_3_REQUEST_CREATE_AUTHOR = "hubv3.request.create-author";
   private static final Configuration config = ConfigFactory.getConfigValidator();
   private RequestType request;

   public static RequestTypeBuilder init() throws TechnicalConnectorException {
      RequestTypeBuilder requestTypeBuilder = new RequestTypeBuilder();
      RequestType newRequest = new RequestType();
      newRequest.setDate(new DateTime());
      newRequest.setTime(new DateTime());
      newRequest.setId(RequestBuilderHelper.createKmehrId("hubservicev3", "hubv3.session.is-cbe"));
      requestTypeBuilder.request = newRequest;
      return requestTypeBuilder;
   }

   public RequestTypeBuilder addGenericAuthor() throws TechnicalConnectorException {
      if (config.getBooleanProperty("hubv3.request.create-author", true)) {
         this.request.setAuthor(HcPartyUtil.createAuthor("hubservicev3"));
      }

      return this;
   }

   public RequestTypeBuilder addAuthorWithEncryptionInformation() throws TechnicalConnectorException {
      if (config.getBooleanProperty("hubv3.request.create-author", true)) {
         AuthorType author = HcPartyUtil.createAuthor("hubservicev3");
         HcPartyUtil.addSecurityTags(author, "hubservicev3");
         this.request.setAuthor(author);
      }

      return this;
   }

   public RequestTypeBuilder addBreakTheGlass(String breakTheGlass) {
      this.request.setBreaktheglass(breakTheGlass);
      return this;
   }

   public RequestTypeBuilder addMaxRows(Integer maxRows) {
      this.request.setMaxrows(maxRows == null ? null : new BigDecimal(maxRows));
      return this;
   }

   public RequestType build() {
      return this.request;
   }

   private RequestTypeBuilder() {
   }
}
