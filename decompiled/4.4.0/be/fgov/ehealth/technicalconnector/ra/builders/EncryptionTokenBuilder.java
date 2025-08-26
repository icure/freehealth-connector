package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.ra.utils.CertificateUtils;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public final class EncryptionTokenBuilder {
   private EncryptionTokenBuilder() {
      throw new UnsupportedOperationException();
   }

   static class EncryptionTokenBuilderSteps implements EncryptionTokenBuilderStep {
      private Credential cred;

      public EncryptionTokenBuilderSteps(Credential cred) {
         this.cred = cred;
      }

      public KeyPairStep create() {
         return new Steps(this.cred);
      }
   }

   static class Steps implements KeyPairStep, ChallengeStep, BuildStep {
      private KeyPair pair;
      private byte[] challenge;
      private Credential cred;

      public Steps(Credential cred) {
         this.cred = cred;
      }

      public ChallengeStep withKeyPair(KeyPair pair) {
         Validate.notNull(pair);
         this.pair = pair;
         return this;
      }

      public BuildStep withChallenge(byte[] challenge) {
         Validate.isTrue(ArrayUtils.isNotEmpty(challenge));
         this.challenge = ArrayUtils.clone(challenge);
         return this;
      }

      public X509Certificate build() throws TechnicalConnectorException {
         return CertificateUtils.generateCert(this.pair.getPublic(), CertificateUtils.obtainSerialNumber(this.pair.getPrivate(), this.challenge), this.cred);
      }
   }

   public interface BuildStep {
      X509Certificate build() throws TechnicalConnectorException;
   }

   public interface ChallengeStep {
      BuildStep withChallenge(byte[] var1);
   }

   public interface EncryptionTokenBuilderStep {
      KeyPairStep create();
   }

   public interface KeyPairStep {
      ChallengeStep withKeyPair(KeyPair var1);
   }
}
