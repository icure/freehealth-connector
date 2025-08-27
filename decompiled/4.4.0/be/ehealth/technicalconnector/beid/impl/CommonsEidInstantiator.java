package be.ehealth.technicalconnector.beid.impl;

import be.ehealth.technicalconnector.beid.BeIDCardAdaptor;
import be.ehealth.technicalconnector.beid.BeIDInstantiator;
import be.ehealth.technicalconnector.beid.domain.BeIDInfo;
import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.fedict.commons.eid.client.BeIDCard;
import be.fedict.commons.eid.client.FileType;
import be.fedict.commons.eid.consumer.Address;
import be.fedict.commons.eid.consumer.Identity;
import be.fedict.commons.eid.consumer.tlv.TlvParser;
import be.fedict.commons.eid.jca.BeIDKeyStoreParameter;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.security.KeyStore;
import org.joda.time.Duration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonsEidInstantiator implements BeIDInstantiator {
   public static final String PROP_AUTO_RECOVERY = "be.ehealth.technicalconnector.beid.impl.commons-eid.autorecovery";
   public static final String PROP_LOGOFF = "be.ehealth.technicalconnector.beid.impl.commons-eid.logoff";
   public static final String PROP_CARDREADERSTICKINESS = "be.ehealth.technicalconnector.beid.impl.commons-eid.cardreaderstickiness";
   private static final Logger LOG = LoggerFactory.getLogger(CommonsEidInstantiator.class);
   public static final String PROP_BEID_ADAPTOR = "be.ehealth.technicalconnector.beid.beidcardadaptor.class";
   private static ConfigurableFactoryHelper<BeIDCardAdaptor> adaptorHelper = new ConfigurableFactoryHelper<BeIDCardAdaptor>("be.ehealth.technicalconnector.beid.beidcardadaptor.class", CommonsEidAdaptor.class.getName());
   private static Cache<String, BeIDInfo> cacheBeIDInfo;

   public CommonsEidInstantiator() {
      Session.getInstance().registerSessionService(this);
   }

   public boolean worksWithDotNet() {
      return false;
   }

   public BeIDInfo instantiateBeIDInfo(String scope, boolean useCache) throws TechnicalConnectorException {
      if (useCache && cacheBeIDInfo.containsKey(scope)) {
         return cacheBeIDInfo.get(scope);
      } else {
         BeIDInfo result = new BeIDInfo();
         BeIDCard beIDCard = null;

         try {
            EidMapper mapper = (EidMapper)Mappers.getMapper(EidMapper.class);
            beIDCard = this.getBeIDCard();
            LOG.debug("processing identity file");
            byte[] identityFile = beIDCard.readFile(FileType.Identity);
            Identity identity = (Identity)TlvParser.parse(identityFile, Identity.class);
            result.setIdentity(mapper.map(identity));
            LOG.debug("processing address file");
            byte[] addressFile = beIDCard.readFile(FileType.Address);
            Address address = (Address)TlvParser.parse(addressFile, Address.class);
            result.setAddress(mapper.map(address));
            LOG.debug("processing photo file");
            result.setPhoto(beIDCard.readFile(FileType.Photo));
         } catch (Exception e) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.BEID_ERROR, e, new Object[]{e.getMessage()});
         } finally {
            ConnectorIOUtils.closeQuietly((Object)beIDCard);
         }

         if (useCache) {
            cacheBeIDInfo.put(scope, result);
         }

         return result;
      }
   }

   public KeyStore instantiateKeyStore() throws TechnicalConnectorException {
      ConfigValidator conf = ConfigFactory.getConfigValidator();
      KeyStore keyStore = null;

      try {
         BeIDCard beIDCard = this.getBeIDCard();
         keyStore = KeyStore.getInstance("BeID");
         BeIDKeyStoreParameter keyStoreParameter = new BeIDKeyStoreParameter();
         keyStoreParameter.setBeIDCard(beIDCard);
         keyStoreParameter.setAutoRecovery(conf.getBooleanProperty("be.ehealth.technicalconnector.beid.impl.commons-eid.autorecovery", true));
         keyStoreParameter.setLogoff(conf.getBooleanProperty("be.ehealth.technicalconnector.beid.impl.commons-eid.logoff", false));
         keyStoreParameter.setCardReaderStickiness(conf.getBooleanProperty("be.ehealth.technicalconnector.beid.impl.commons-eid.cardreaderstickiness", false));
         keyStore.load(keyStoreParameter);
         return keyStore;
      } catch (Exception e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.BEID_ERROR, e, new Object[0]);
      }
   }

   public KeyStore instantiateKeyStore(String scope, boolean useCache) throws TechnicalConnectorException {
      return this.instantiateKeyStore();
   }

   private BeIDCard getBeIDCard() throws TechnicalConnectorException {
      return ((BeIDCardAdaptor)adaptorHelper.getImplementation()).getBeIDCard();
   }

   public void verifyPin(char[] pin) throws TechnicalConnectorException {
      PincodeVerifierAPDUBased.verifyPin(pin);
   }

   public void flushCache() {
      cacheBeIDInfo.clear();
   }

   static {
      cacheBeIDInfo = CacheFactory.<String, BeIDInfo>newInstance(CacheFactory.CacheType.MEMORY, "beid-info", CacheInformation.ExpiryType.NONE, (Duration)null);
   }

   @Mapper
   public interface EidMapper {
      be.ehealth.technicalconnector.beid.domain.Identity map(Identity var1);

      be.ehealth.technicalconnector.beid.domain.Address map(Address var1);
   }
}
