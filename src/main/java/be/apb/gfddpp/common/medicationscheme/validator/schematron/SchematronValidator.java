package be.apb.gfddpp.common.medicationscheme.validator.schematron;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SchematronValidator {
   private static final Logger LOG = LogManager.getLogger(SchematronValidator.class);
   private static volatile Cache<String, Templates> cachedTemplates = Caffeine.newBuilder()
           .expireAfterWrite(1, TimeUnit.MINUTES)
           .maximumSize(100)
           .build();;
   private static volatile TransformerFactory transformerFactory = TransformerFactory.newInstance();
   private Transformer transformer;
   private SchematronResult validationResult;

   private SchematronValidator(Templates templates) throws TransformerException {
      this.transformer = templates.newTransformer();
      this.transformer.setOutputProperty("encoding", "UTF-8");
      this.transformer.setOutputProperty("indent", "yes");
   }

   public static synchronized SchematronValidator getInstance(String schematronLocation) throws TransformerException {
      Validate.notEmpty(schematronLocation, "Location for the Schematron schema needs to be specified.");
      LOG.info(String.format("Retrieving validator for schematron file [%s]", schematronLocation));

      Templates template = cachedTemplates.get(schematronLocation, key -> {
         LOG.debug(String.format("Template for schematron file [%s] not in cache, creating it", schematronLocation));
         Source dataSource = new StreamSource(SchematronValidator.class.getResource(schematronLocation).toString());
         Templates templates = null;
         try {
            templates = transformerFactory.newTemplates(dataSource);
         } catch (TransformerException e) {
            LOG.error(String.format("Error creating template for schematron file [%s]", schematronLocation), e);
         }
         LOG.debug(String.format("Template for schematron file [%s] created and cached", schematronLocation));
         return templates;
      });
      return new SchematronValidator(template);
   }

   public void validate(Source businessData) throws TransformerException, IOException, SAXException, ParserConfigurationException {
      StringWriter writer = new StringWriter();
      this.transformer.transform(businessData, new StreamResult(writer));
      this.validationResult = new SchematronResult(businessData.getSystemId());
      this.validationResult.setSVRL(writer.toString());
   }

   public List<String> getErrors() {
      List<String> errors = this.validationResult == null ? new ArrayList() : this.validationResult.getErrors();

      for (Object o : errors) {
         String error = (String) o;
         LOG.error("Schematron Error :" + error);
      }

      return errors;
   }
}
