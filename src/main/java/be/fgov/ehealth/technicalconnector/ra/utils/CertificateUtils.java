package be.fgov.ehealth.technicalconnector.ra.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.joda.time.DateTime;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.utils.ConnectorCryptoUtils;
import org.taktik.connector.technical.utils.ConnectorIOUtils;

import javax.security.auth.x500.X500Principal;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;

public class CertificateUtils {
   private static final String PROVIDER = "BC";
   private static final CertificateFactory CF;


   public static byte[] createCSR(String dn, KeyPair keyPair) {
      String csrSignatureAlgorithm = RaPropertiesLoader.getProperty("csr.signature.algorithm");

      try {
         X500Principal x500Principal = new X500Principal(dn);
         JcaPKCS10CertificationRequestBuilder csrBuilder = new JcaPKCS10CertificationRequestBuilder(x500Principal, keyPair.getPublic());
         PKCS10CertificationRequest csr = csrBuilder.build((new JcaContentSignerBuilder(csrSignatureAlgorithm)).setProvider(new BouncyCastleProvider()).build(keyPair.getPrivate()));
         return csr.getEncoded();
      } catch (OperatorCreationException | IOException ex) {
         throw new IllegalArgumentException(ex);
      }
   }

   public static X509Certificate generateCert(PublicKey rqPubKey, BigInteger serialNr, Credential cred) throws TechnicalConnectorException {
      try {
         X509Certificate cert = cred.getCertificate();
         X500Principal principal = cert.getSubjectX500Principal();
         Date notBefore = cert.getNotBefore();
         Date notAfter = cert.getNotAfter();
         X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(principal, serialNr, notBefore, notAfter, principal, rqPubKey);
         int keyUsageDetails = 16 + 32;
         builder.addExtension(Extension.keyUsage, true, new KeyUsage(keyUsageDetails));
         ContentSigner signer = (new JcaContentSignerBuilder(cert.getSigAlgName())).build(cred.getPrivateKey());
         X509CertificateHolder holder = builder.build(signer);
         return (new JcaX509CertificateConverter()).setProvider("BC").getCertificate(holder);
      } catch (OperatorCreationException | IOException | CertificateException ex) {
         throw new IllegalArgumentException(ex);
      }
   }

   public static X509Certificate generateCert(KeyPair pair) {
      try {
         X500Principal principal = new X500Principal(RaPropertiesLoader.getProperty("dummycert.subject"));
         Date notBefore = (new DateTime()).minusDays(1).toDate();
         Date notAfter = (new DateTime()).minusDays(1).plusMinutes(1).toDate();
         X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(principal, BigInteger.ZERO, notBefore, notAfter, principal, pair.getPublic());
         int keyUsageDetails = 16 + 32;
         builder.addExtension(Extension.keyUsage, true, new KeyUsage(keyUsageDetails));
         ContentSigner signer = (new JcaContentSignerBuilder(RaPropertiesLoader.getProperty("dummycert.signature.algorithm"))).build(pair.getPrivate());
         X509CertificateHolder holder = builder.build(signer);
         return (new JcaX509CertificateConverter()).setProvider("BC").getCertificate(holder);
      } catch (OperatorCreationException | IOException | CertificateException ex) {
         throw new IllegalArgumentException(ex);
      }
   }

   public static BigInteger obtainSerialNumber(PrivateKey key, byte[] challenge) throws TechnicalConnectorException {
      byte[] encryptedNonce = ArrayUtils.subarray(challenge, 0, challenge.length - 32);
      byte[] serialNrHashed = ArrayUtils.subarray(challenge, challenge.length - 32, challenge.length);
      byte[] decryptedNonceBytes = ConnectorCryptoUtils.decrypt(key, RaPropertiesLoader.getProperty("etk.challenge.cipher"), encryptedNonce);
      byte[] serialNr = ArrayUtils.subarray(decryptedNonceBytes, 0, 16);
      byte[] calculatedHash = ConnectorCryptoUtils.calculateDigest(RaPropertiesLoader.getProperty("etk.challenge.digest"), serialNr);
      if (!Arrays.equals(serialNrHashed, calculatedHash)) {
         throw new IllegalArgumentException("The challenge is not valid because the hash of the decrypted serial nr found inside the challenge is not equal to the hashed serial nr attached to the challenge.");
      } else {
         byte[] serialNumberUnsigned = new byte[serialNr.length + 1];
         System.arraycopy(serialNr, 0, serialNumberUnsigned, 1, serialNr.length);
         return new BigInteger(serialNumberUnsigned);
      }
   }

   public static X509Certificate toX509Certificate(byte[] cert) {
      ByteArrayInputStream baos = null;

      X509Certificate var2;
      try {
         baos = new ByteArrayInputStream(cert);
         var2 = (X509Certificate)CF.generateCertificate(baos);
      } catch (CertificateException var6) {
         throw new IllegalArgumentException(var6);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)baos);
      }

      return var2;
   }

   static {
      Security.addProvider(new BouncyCastleProvider());

      try {
         CF = CertificateFactory.getInstance("X.509");
      } catch (CertificateException var1) {
         throw new IllegalArgumentException(var1);
      }
   }
}
