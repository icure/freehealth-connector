package be.ehealth.businessconnector.mycarenet.memberdatav2.session;

import be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.MemberDataMyCareNetService;
import be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.MemberDataServiceImplementationFactory;
import be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.regional.MemberDataIrisCareNetService;
import be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.regional.MemberDataWalCareNetService;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class MemberDataSessionServiceFactory extends AbstractSessionServiceFactory {
   private MemberDataSessionServiceFactory() {
   }

   public static MemberDataService getMemberDataSyncService() throws ConnectorException {
      return (MemberDataService)getService(MemberDataMyCareNetService.class, new MemberDataServiceImplementationFactory(), new String[0]);
   }

   public static MemberDataService getMemberDataSyncIrisCareNetService() throws ConnectorException {
      return (MemberDataService)getService(MemberDataIrisCareNetService.class, new MemberDataServiceImplementationFactory(), new String[0]);
   }

   public static MemberDataService getMemberDataSyncWalCareNetService() throws ConnectorException {
      return (MemberDataService)getService(MemberDataWalCareNetService.class, new MemberDataServiceImplementationFactory(), new String[0]);
   }
}
