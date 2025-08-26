package be.ehealth.technicalconnector.service.keydepot.impl;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.ServiceFactory;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotService;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import be.ehealth.technicalconnector.utils.CertificateParser;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KeyDepotManagerImpl implements KeyDepotManager, SessionServiceWithCache {
   private static final Logger LOG = LoggerFactory.getLogger(KeyDepotManagerImpl.class);
   private KeyDepotService service;
   private Cache<X509Certificate, EncryptionToken> cache;

   private KeyDepotManagerImpl() {
      this.cache = CacheFactory.<X509Certificate, EncryptionToken>newInstance(CacheFactory.CacheType.MEMORY, "etkdepot-manager", CacheInformation.ExpiryType.NONE, (Duration)null);

      try {
         this.service = ServiceFactory.getKeyDepotService();
      } catch (TechnicalConnectorException e) {
         LOG.error(e.getMessage(), e);
         throw new IllegalArgumentException(e);
      }

      Session.getInstance().registerSessionService(this);
   }

   public static KeyDepotManager getInstance() {
      return KeyDepotManagerImpl.KeyDepotManagerImplSingleton.INSTANCE.getKeyDepotManager();
   }

   public EncryptionToken getETK(KeyDepotManager.EncryptionTokenType type) throws TechnicalConnectorException {
      SessionItem session = Session.getInstance().getSession();
      switch (type) {
         case ENCRYPTION:
            return this.getEncryptionToken(session.getEncryptionCredential());
         case HOLDER_OF_KEY:
            return this.getEncryptionToken(session.getHolderOfKeyCredential());
         default:
            throw new IllegalArgumentException("Unsupported EncryptionTokenType.");
      }
   }

   private EncryptionToken getEncryptionToken(Credential cred) throws TechnicalConnectorException {
      if (cred != null) {
         X509Certificate cert = cred.getCertificate();
         if (!this.cache.containsKey(cert)) {
            this.cache.put(cert, this.getEtkBasedOnX509(cert));
         }

         return this.cache.get(cert);
      } else {
         LOG.error(TechnicalConnectorExceptionValues.NO_VALID_SESSION_WITH_ENCRYPTION.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION_WITH_ENCRYPTION, new Object[0]);
      }
   }

   private EncryptionToken getEtkBasedOnX509(X509Certificate cert) throws TechnicalConnectorException {
      CertificateParser parser = new CertificateParser(cert);
      IdentifierType identifierType = parser.getIdentifier();
      String identifierValue = parser.getId();
      String application = parser.getApplication();
      if (identifierType != null && !StringUtils.isEmpty(identifierValue) && StringUtils.isNumeric(identifierValue)) {
         try {
            return this.getETK(identifierType, Long.parseLong(identifierValue), application);
         } catch (NumberFormatException e) {
            LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, e, new Object[0]);
         }
      } else {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, new Object[0]);
      }
   }

   public EncryptionToken getETK(IdentifierType identifierType, Long identifierValue, String application) throws TechnicalConnectorException {
      Set<EncryptionToken> etkSet = this.getETKs(identifierType, identifierValue, application);
      if (etkSet.size() != 1) {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, new Object[0]);
      } else {
         return (EncryptionToken)etkSet.iterator().next();
      }
   }

   public Set<EncryptionToken> getETKs(IdentifierType identifierType, Long identifierValue, String application) throws TechnicalConnectorException {
      String identifier = identifierType.formatIdentifierValue(identifierValue);
      Set<EncryptionToken> result = new HashSet();
      result.addAll(this.service.getETKSet(identifierType, identifier, application));
      if (LOG.isDebugEnabled()) {
         StringBuilder keyBuilder = new StringBuilder();
         keyBuilder.append(identifierType).append("/").append(identifierValue).append("/").append(application).append(" size [").append(result.size()).append("] with serialnr [");
         String delim = "";

         for(EncryptionToken etk : result) {
            keyBuilder.append(delim).append(etk.getCertificate().getSerialNumber().toString(10));
            delim = ",";
         }

         keyBuilder.append("]");
         LOG.debug("Retrieved ETK from eHealth Key Depot Web Service: {}", keyBuilder.toString());
      }

      return result;
   }

   public void setKeyDepotService(KeyDepotService service) {
      this.service = service;
      this.flushCache();
   }

   public void flushCache() {
      this.cache.clear();
   }

   private static enum KeyDepotManagerImplSingleton {
      INSTANCE;

      private transient KeyDepotManager instance = new KeyDepotManagerImpl();

      private KeyDepotManagerImplSingleton() {
      }

      public KeyDepotManager getKeyDepotManager() {
         return this.instance;
      }
   }
}
