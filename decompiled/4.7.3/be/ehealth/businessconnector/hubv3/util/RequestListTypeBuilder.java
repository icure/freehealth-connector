package be.ehealth.businessconnector.hubv3.util;

import be.ehealth.business.intrahubcommons.helper.RequestBuilderHelper;
import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v3.Paginationrequestinfo;
import be.fgov.ehealth.hubservices.core.v3.RequestList;
import org.joda.time.DateTime;

public class RequestListTypeBuilder {
   public static final String PROJECT_NAME = "hubservicev3";
   private static final String HUBV_3_SESSION_IS_CBE = "hubv3.session.is-cbe";
   private static final String HUBV_3_REQUEST_CREATE_AUTHOR = "hubv3.request.create-author";
   private static final Configuration config = ConfigFactory.getConfigValidator();
   private RequestList request;

   public static RequestListTypeBuilder init() throws TechnicalConnectorException {
      RequestListTypeBuilder requestTypeBuilder = new RequestListTypeBuilder();
      RequestList newRequest = new RequestList();
      newRequest.setDate(new DateTime());
      newRequest.setTime(new DateTime());
      newRequest.setId(RequestBuilderHelper.createKmehrId("hubservicev3", "hubv3.session.is-cbe"));
      requestTypeBuilder.request = newRequest;
      return requestTypeBuilder;
   }

   public RequestListTypeBuilder addGenericAuthor() throws TechnicalConnectorException {
      if (config.getBooleanProperty("hubv3.request.create-author", true)) {
         this.request.setAuthor(HcPartyUtil.createAuthor("hubservicev3"));
      }

      return this;
   }

   public RequestListTypeBuilder addBreakTheGlass(String breakTheGlass) {
      this.request.setBreaktheglass(breakTheGlass);
      return this;
   }

   public RequestListTypeBuilder addPaginationInfo(Paginationrequestinfo pagReqInfo) {
      this.request.setPaginationinfo(pagReqInfo);
      return this;
   }

   public RequestList build() {
      return this.request;
   }

   private RequestListTypeBuilder() {
   }
}
