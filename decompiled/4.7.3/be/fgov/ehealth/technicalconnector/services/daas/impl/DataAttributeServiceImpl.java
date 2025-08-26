package be.fgov.ehealth.technicalconnector.services.daas.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.DateUtils;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.ehealth.technicalconnector.utils.TemplateEngineUtils;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import be.fgov.ehealth.daas.complextype.v1.Actor;
import be.fgov.ehealth.technicalconnector.services.SAMLUtils;
import be.fgov.ehealth.technicalconnector.services.daas.AttributeValue;
import be.fgov.ehealth.technicalconnector.services.daas.DaasServiceException;
import be.fgov.ehealth.technicalconnector.services.daas.DataAttributeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.soap.SOAPException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataAttributeServiceImpl implements DataAttributeService {
   public static final String ENDPOINT_DAAS_V1 = "endpoint.daas.v1";
   public static final String TEMPLATE_DAAS = "be.fgov.ehealth.technicalconnector.services.daas.template";
   public static final String VALIDATE_ATTRIBUTE_VALUES = "be.fgov.ehealth.technicalconnector.services.daas.validate-attribute-values";
   private static final Logger LOG = LoggerFactory.getLogger(DataAttributeServiceImpl.class);
   private static final String SAML_ATTRIBUTE = "Attribute";
   private static final String ASSERTION_NAMESPACE = "urn:oasis:names:tc:SAML:2.0:assertion";
   private static final String SAML_ATTRIBUTE_NAME = "Name";
   protected static final String SCHEMA_FILE_LOCATION = "/ehealth-daasattribute/XSD/ehealth-daasattribute-1_1.xsd";
   private static final String TEMPLATES_DAAS_XML = "/templates/daas.attributequery.xml";
   private JAXBContext jaxbContext = JaxbContextFactory.getJaxbContextForPackage(Actor.class.getPackage());
   private Configuration config = ConfigFactory.getConfigValidator();

   public DataAttributeServiceImpl() {
   }

   public Map<String, List<AttributeValue>> invoke(Map<String, List<String>> attributes) throws TechnicalConnectorException {
      DateTime notBefore = (new DateTime()).withTimeAtStartOfDay();
      DateTime notOnOrAfter = (new DateTime()).withTimeAtStartOfDay().plusDays(1).minusSeconds(1);
      return this.invoke(attributes, notBefore, notOnOrAfter);
   }

   public Map<String, List<AttributeValue>> invoke(Map<String, List<String>> attributes, DateTime notBefore, DateTime notOnOrAfter) throws TechnicalConnectorException {
      GenericRequest request = new GenericRequest();
      request.setPayload(this.generatePayload(attributes, notBefore, notOnOrAfter));
      request.setCredentialFromSession(TokenType.SAML);
      request.addDefaulHandlerChain();
      request.setEndpoint(this.config.getProperty("endpoint.daas.v1", "$uddi{uddi:ehealth-fgov-be:business:dataattributeservice:v1}"));

      try {
         Element response = (Element)ServiceFactory.getGenericWsSender().send(request).asNode();
         this.validate(response);
         return this.map(response);
      } catch (SOAPException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{e.getMessage(), e});
      }
   }

   private String generatePayload(Map<String, List<String>> attributes, DateTime notBefore, DateTime notOnOrAfter) throws TechnicalConnectorException {
      Map<String, Object> ctx = new HashMap();
      ctx.put("attributeQueryId", "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId().substring(1));
      ctx.put("issuer", this.determineIssuer());
      ctx.put("issueInstant", DateUtils.printDateTime((new DateTime()).withZone(DateTimeZone.UTC)));
      ctx.put("transientNameId", RandomStringUtils.random(37, false, true));
      ctx.put("notBefore", DateUtils.printDateTime(notBefore.withZone(DateTimeZone.UTC)));
      ctx.put("notOnOrAfter", DateUtils.printDateTime(notOnOrAfter.withZone(DateTimeZone.UTC)));
      ctx.put("attrMap", attributes);
      return ConnectorXmlUtils.flatten(TemplateEngineUtils.generate(ctx, this.config.getProperty("be.fgov.ehealth.technicalconnector.services.daas.template", "/templates/daas.attributequery.xml")));
   }

   private void validate(Element response) throws TechnicalConnectorException {
      this.validateResponse(response);
      SAMLUtils.validateAssertions(response);
   }

   private void validateResponse(Element response) throws DaasServiceException {
      List<String> status = SAMLUtils.getStatusCodes(response);
      if (!StringUtils.endsWithIgnoreCase((CharSequence)status.get(0), ":success")) {
         throw new DaasServiceException(status, new Object[]{SAMLUtils.getStatusMessage(response)});
      }
   }

   private Map<String, List<AttributeValue>> map(Element response) {
      NodeList attributeList = response.getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Attribute");
      Map<String, List<AttributeValue>> result = new HashMap();

      for(int i = 0; i < attributeList.getLength(); ++i) {
         Node node = attributeList.item(i);
         if ("AttributeStatement".equals(node.getParentNode().getLocalName())) {
            String attributeName = node.getAttributes().getNamedItem("Name").getTextContent();
            if (!node.hasChildNodes()) {
               result.put(attributeName, (Object)null);
            } else {
               NodeList attributeValueNodeList = node.getChildNodes();
               List<AttributeValue> values = new ArrayList();

               for(int index = 0; index < attributeValueNodeList.getLength(); ++index) {
                  NodeList attributeValues = attributeValueNodeList.item(index).getChildNodes();

                  for(int j = 0; j < attributeValues.getLength(); ++j) {
                     AttributeValue attrValue = this.convert(attributeValues.item(j));
                     if (attrValue != null) {
                        values.add(attrValue);
                     }
                  }
               }

               result.put(attributeName, values);
            }
         }
      }

      return result;
   }

   private AttributeValue convert(Node value) {
      if (value.getNodeType() == 3) {
         return new AttributeValue(value.getTextContent().trim());
      } else if (value.getNodeType() == 1) {
         try {
            Object object = this.jaxbContext.createUnmarshaller().unmarshal(value);
            this.validateAttributeValue(object);
            return new AttributeValue(object);
         } catch (Exception e) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Unrecognised object [{}], adding as node", toString(value), e);
            }

            return new AttributeValue(value);
         }
      } else {
         LOG.debug("Ignoring node. Unsupported node type {}", value.getNodeType());
         return null;
      }
   }

   private void validateAttributeValue(Object object) throws Exception {
      if (this.config.getBooleanProperty("be.fgov.ehealth.technicalconnector.services.daas.validate-attribute-values", true)) {
         ValidatorHelper.validate(object, "/ehealth-daasattribute/XSD/ehealth-daasattribute-1_1.xsd");
      }

   }

   private String determineIssuer() {
      try {
         Map<String, List<String>> matchingAttributes = SessionUtil.getMatchingAttributes("urn:be:fgov:identification-namespace", ".*:certificateholder:.*");
         if (matchingAttributes.size() == 1) {
            Map.Entry<String, List<String>> firstEntry = (Map.Entry)matchingAttributes.entrySet().iterator().next();
            List<String> attributeValues = (List)firstEntry.getValue();
            if (attributeValues.size() == 1) {
               return (String)firstEntry.getKey() + ":" + (String)((List)firstEntry.getValue()).get(0);
            }
         }
      } catch (TechnicalConnectorException e) {
         LOG.warn("Exception while extracting issuer from session.", e);
      }

      LOG.info("Returning default issuer.");
      return "urn:be:fgov:ehealth:unknown";
   }

   private static String toString(Node value) {
      String nodeAsString = null;

      try {
         nodeAsString = ConnectorXmlUtils.toString(value);
      } catch (TechnicalConnectorException var3) {
         nodeAsString = "unknown";
      }

      return nodeAsString;
   }
}
