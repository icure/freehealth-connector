package be.fgov.ehealth.technicalconnector.signature.domain;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.timestamp.TimestampUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.tsp.TimeStampToken;
import org.joda.time.DateTime;

public class SignatureVerificationResult implements Serializable {
   private static final long serialVersionUID = 1L;
   private transient List<TimeStampToken> tsTokens = new ArrayList();
   private List<Byte[]> serTsTokens;
   private X509Certificate signingCert;
   private List<X509Certificate> certChain = new ArrayList();
   private DateTime signingTime;
   private List<DateTime> timestampGenTime = new ArrayList();
   private Set<SignatureVerificationError> errors = new TreeSet();

   public SignatureVerificationResult() {
   }

   public boolean isValid() {
      return this.errors.isEmpty();
   }

   public Set<SignatureVerificationError> getErrors() {
      return this.errors;
   }

   public X509Certificate getSigningCert() {
      return this.signingCert;
   }

   public void setSigningCert(X509Certificate signingCert) {
      this.signingCert = signingCert;
   }

   public DateTime getSigningTime() {
      return this.signingTime;
   }

   public void setSigningTime(DateTime signingTime) {
      this.signingTime = signingTime;
   }

   public List<DateTime> getTimestampGenTimes() {
      return this.timestampGenTime;
   }

   public DateTime getVerifiedSigningTime(int amount, TimeUnit unit) {
      for(DateTime genTime : this.timestampGenTime) {
         DateTime start = genTime.minus(unit.toMillis((long)amount));
         DateTime end = genTime.plus(unit.toMillis((long)amount));
         if (!this.signingTime.isBefore(start) && !this.signingTime.isAfter(end)) {
            return this.signingTime;
         }
      }

      return new DateTime();
   }

   public List<TimeStampToken> getTsTokens() {
      return this.tsTokens;
   }

   public List<X509Certificate> getCertChain() {
      return this.certChain;
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      this.serTsTokens = new ArrayList();

      for(TimeStampToken tsToken : this.tsTokens) {
         this.serTsTokens.add(ArrayUtils.toObject(tsToken.getEncoded()));
      }

      out.defaultWriteObject();
      this.serTsTokens = null;
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.tsTokens = new ArrayList();
      if (this.serTsTokens != null) {
         for(Byte[] serToken : this.serTsTokens) {
            try {
               this.tsTokens.add(TimestampUtil.getTimeStampToken(ArrayUtils.toPrimitive(serToken)));
            } catch (TechnicalConnectorException e) {
               throw new IOException(e);
            }
         }
      }

      this.serTsTokens = null;
   }
}
