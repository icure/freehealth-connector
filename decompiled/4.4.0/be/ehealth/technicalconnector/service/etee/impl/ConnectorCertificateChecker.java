package be.ehealth.technicalconnector.service.etee.impl;

import be.ehealth.technicalconnector.exception.CertificateVerificationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.CertificateChecker;
import be.ehealth.technicalconnector.service.etee.CryptoFactory;
import be.ehealth.technicalconnector.service.etee.RevocationStatusCheckerFactory;
import be.fgov.ehealth.etee.crypto.cert.CertPathCheckerBuilder;
import be.fgov.ehealth.etee.crypto.cert.CertificateStatus;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectorCertificateChecker implements CertificateChecker {
   private static final Logger log = LoggerFactory.getLogger(ConnectorCertificateChecker.class);

   public ConnectorCertificateChecker() {
   }

   public boolean isCertificateRevoked(File certFile) throws TechnicalConnectorException {
      return this.isCertificateRevoked(certFile, new DateTime());
   }

   public boolean isCertificateRevoked(File certFile, DateTime validOn) throws TechnicalConnectorException {
      try {
         CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
         X509Certificate cert = (X509Certificate)certFactory.generateCertificate(new FileInputStream(certFile));
         return this.isCertificateRevoked(cert, validOn);
      } catch (FileNotFoundException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, e, new Object[]{e.getMessage()});
      } catch (CertificateException e) {
         throw new CertificateVerificationException(e.getMessage(), e);
      }
   }

   public boolean isCertificateRevoked(X509Certificate cert) throws TechnicalConnectorException {
      return this.isCertificateRevoked(cert, new DateTime());
   }

   public boolean isCertificateRevoked(X509Certificate cert, DateTime validOn) throws TechnicalConnectorException {
      try {
         return RevocationStatusCheckerFactory.getStatusChecker().isRevoked(cert, validOn);
      } catch (CertificateException e) {
         throw new CertificateVerificationException(e.getMessage(), e);
      }
   }

   public boolean isValidCertificateChain(List<X509Certificate> certificateChain) throws TechnicalConnectorException {
      return this.isValidCertificateChain(certificateChain, new DateTime());
   }

   public boolean isValidCertificateChain(List<X509Certificate> certificateChain, DateTime validOn) throws TechnicalConnectorException {
      if (certificateChain.size() == 1 && this.isSelfSigned((X509Certificate)certificateChain.get(0))) {
         log.debug("certificate is self signed detected. skipping chain validation");
         return true;
      } else {
         CryptoResult<CertificateStatus> result = CertPathCheckerBuilder.newBuilder().addTrustStore(CryptoFactory.getCaCertificateStore()).build().validate(certificateChain, validOn.toDate());
         return !result.hasErrors() && ((CertificateStatus)result.getData()).equals(CertificateStatus.VALID);
      }
   }

   private boolean isSelfSigned(X509Certificate cert) {
      try {
         cert.verify(cert.getPublicKey());
         return true;
      } catch (Exception var3) {
         return false;
      }
   }
}
