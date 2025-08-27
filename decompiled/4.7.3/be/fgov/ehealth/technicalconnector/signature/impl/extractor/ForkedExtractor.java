package be.fgov.ehealth.technicalconnector.signature.impl.extractor;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;

public class ForkedExtractor implements Extractor {
   private Extractor[] extractors;

   public ForkedExtractor(Extractor... extractors) {
      this.extractors = extractors;
   }

   public boolean canExtract(KeyInfo keyinfo) {
      for(Extractor extractor : this.extractors) {
         if (extractor.canExtract(keyinfo)) {
            return true;
         }
      }

      return false;
   }

   public List<X509Certificate> extract(KeyInfo keyinfo) throws XMLSecurityException {
      for(Extractor extractor : this.extractors) {
         if (extractor.canExtract(keyinfo)) {
            return extractor.extract(keyinfo);
         }
      }

      return new ArrayList();
   }
}
