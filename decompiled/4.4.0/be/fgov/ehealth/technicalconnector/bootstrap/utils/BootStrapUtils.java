package be.fgov.ehealth.technicalconnector.bootstrap.utils;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BootStrapUtils {
   public static final String EXT_SHA2 = ".sha2";
   public static final String EXT_XML = ".xml";
   private static final String PROP_STORETYPE_JKS = "JKS";
   private static final Logger LOG = LoggerFactory.getLogger(BootStrapUtils.class);
   private static final String CERTIFICATE_BEGIN = "-----BEGIN CERTIFICATE-----";
   private static final String CERTIFICATE_END = "-----END CERTIFICATE-----";
   private static final String CERTIFICATE_TYPE = "X.509";
   private static final String LF = System.getProperty("line.separator");

   private BootStrapUtils() {
      throw new UnsupportedOperationException();
   }

   public static X509Certificate generateX509Cert(String input) throws TechnicalConnectorException {
      StringBuilder x509base64 = new StringBuilder();
      if (!input.contains("-----BEGIN CERTIFICATE-----")) {
         x509base64.append("-----BEGIN CERTIFICATE-----").append(LF);
      }

      x509base64.append(input);
      if (!input.contains("-----END CERTIFICATE-----")) {
         x509base64.append(LF).append("-----END CERTIFICATE-----");
      }

      byte[] bytes = x509base64.toString().trim().getBytes();
      ByteArrayInputStream bais = null;

      X509Certificate var5;
      try {
         CertificateFactory cf = CertificateFactory.getInstance("X.509");
         bais = new ByteArrayInputStream(bytes);
         var5 = (X509Certificate)cf.generateCertificate(bais);
      } catch (CertificateException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, e, new Object[0]);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)bais);
      }

      return var5;
   }

   public static void writeKeyStore(List<X509Certificate> certList, String location, char[] pwd) throws TechnicalConnectorException {
      OutputStream fos = null;

      try {
         File file = new File(location);
         if (!file.exists()) {
            LOG.debug("Create new file [" + location + "]");
            if (!file.createNewFile()) {
               throw new IOException("Unable to create file");
            }
         }

         if (file.canWrite()) {
            KeyStore store = KeyStore.getInstance("JKS");
            store.load((InputStream)null, pwd);

            for(int i = 0; i < certList.size(); ++i) {
               X509Certificate cert = (X509Certificate)certList.get(i);
               String alias = getAlias(i, cert);
               store.setCertificateEntry(alias, cert);
            }

            fos = new FileOutputStream(file);
            store.store(fos, pwd);
            return;
         }

         LOG.info("Unable to write on file [" + location + "], skipping write keystore.");
      } catch (Exception e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, e, new Object[]{location});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)fos);
      }

   }

   private static String getAlias(int i, X509Certificate cert) {
      return StringUtils.leftPad(Integer.toString(i), 3, "0") + " " + cert.getSubjectX500Principal().getName("RFC2253");
   }

   public static void merge(String inputLocation, char[] inputPwd, String targetLocation, char[] targetPwd) throws TechnicalConnectorException {
      OutputStream fos = null;

      try {
         File file = new File(targetLocation);
         if (!file.exists()) {
            LOG.debug("Create new file [" + targetLocation + "]");
            if (!file.createNewFile()) {
               LOG.info("Unable to create file [" + targetLocation + "], skipping write keystore.");
               return;
            }
         }

         if (!file.canWrite()) {
            LOG.info("Unable to write on file [" + targetLocation + "], skipping write keystore.");
         } else {
            KeyStore source = KeyStore.getInstance("JKS");
            source.load(ConnectorIOUtils.getResourceAsStream(inputLocation), inputPwd);
            KeyStore target = KeyStore.getInstance("JKS");
            target.load(ConnectorIOUtils.getResourceAsStream(targetLocation), targetPwd);
            Enumeration<String> aliases = source.aliases();

            while(aliases.hasMoreElements()) {
               String alias = (String)aliases.nextElement();
               X509Certificate cert = (X509Certificate)source.getCertificate(alias);
               if (isSelfSigned(cert) && !exists(cert, target)) {
                  LOG.debug("Adding self signed cert to store: " + cert.getSubjectX500Principal().getName("RFC1779"));
                  target.setCertificateEntry(getAlias(target.size() + 1, cert), cert);
               }
            }

            fos = new FileOutputStream(file);
            target.store(fos, targetPwd);
         }
      } catch (Exception e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, e, new Object[]{"while merging keystore [" + inputLocation + "] into [" + targetLocation + "]"});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)fos);
      }
   }

   public static boolean exists(X509Certificate inCert, KeyStore store) throws KeyStoreException {
      Enumeration<String> aliases = store.aliases();

      while(aliases.hasMoreElements()) {
         String alias = (String)aliases.nextElement();
         X509Certificate cert = (X509Certificate)store.getCertificate(alias);
         if (cert.equals(inCert)) {
            return true;
         }
      }

      return false;
   }

   private static boolean isSelfSigned(X509Certificate cert) {
      try {
         cert.verify(cert.getPublicKey());
         return true;
      } catch (Exception e) {
         LOG.debug("Certificate is not selfsigned.", e);
         return false;
      }
   }
}
