package be.ehealth.technicalconnector.utils.impl;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JaxbContextFactory {
   private static final Logger LOG = LoggerFactory.getLogger(JaxbContextFactory.class);
   private static final Map<String, JAXBContext> CACHE = new HashMap();

   private JaxbContextFactory() {
      throw new UnsupportedOperationException();
   }

   public static void initJaxbContext(Package packageInstance) {
      try {
         getJaxbContextForPackage(packageInstance);
      } catch (Exception e) {
         LOG.warn("Unable to load JaxbContext for {}", packageInstance, e);
      }

   }

   public static void initJaxbContext(Class<?> keyClass, boolean override, Class<?>... classesToBeBound) {
      try {
         String key = calculateKey(keyClass);
         getJaxbContextForClass(key, override, classesToBeBound);
      } catch (JAXBException e) {
         LOG.warn("Unable to load JaxbContext for {}", ArrayUtils.toString(classesToBeBound), e);
      }

   }

   public static void initJaxbContext(Class<?>... classesToBeBound) {
      try {
         getJaxbContextForClass(classesToBeBound);
      } catch (JAXBException e) {
         LOG.warn("Unable to load JaxbContext for {}", ArrayUtils.toString(classesToBeBound), e);
      }

   }

   public static JAXBContext getJaxbContextForPackage(Package pack) {
      Validate.notNull(pack);

      try {
         String key = pack.getName();
         JAXBContext context = (JAXBContext)CACHE.get(key);
         if (context == null) {
            context = JAXBContext.newInstance(key);
            CACHE.put(key, context);
         }

         return context;
      } catch (JAXBException var3) {
         throw new IllegalArgumentException("Unable to create jaxbContext for package" + pack.getName());
      }
   }

   public static JAXBContext getJaxbContextForClass(Class<?>... classesToBeBound) throws JAXBException {
      String key = calculateKey(classesToBeBound);
      return getJaxbContextForClass(key, false, classesToBeBound);
   }

   private static JAXBContext getJaxbContextForClass(String key, boolean override, Class<?>... classesToBeBound) throws JAXBException {
      JAXBContext context = null;
      if (!override) {
         context = (JAXBContext)CACHE.get(key);
      }

      if (context == null) {
         context = JAXBContext.newInstance(classesToBeBound);
         CACHE.put(key, context);
      }

      return context;
   }

   private static String calculateKey(Class<?>... classesToBeBound) {
      int arrayLength = ArrayUtils.getLength(classesToBeBound);
      if (arrayLength == 0) {
         return null;
      } else if (arrayLength == 1) {
         return classesToBeBound[0].getName();
      } else {
         Set<String> keyList = new TreeSet();

         for(Class<?> classToBeBound : classesToBeBound) {
            keyList.add(classToBeBound.getName());
         }

         MessageDigest complete = DigestUtils.getMd5Digest();

         for(String clazz : keyList) {
            complete.update(clazz.getBytes());
         }

         return new String(Base64.encode(complete.digest()));
      }
   }

   public static void reset() {
      CACHE.clear();
   }
}
