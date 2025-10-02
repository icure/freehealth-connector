package org.taktik.connector.business.mycarenetdomaincommons.builders.impl;

import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobAttributeValues;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import org.taktik.connector.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorExceptionValues;
import org.taktik.connector.business.mycarenetdomaincommons.util.PropertyUtil;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlobBuilderImpl implements BlobBuilder {
   private static final String PROPERTYBEGINNING = ".blobbuilder.";
   private String projectName;
   private String platformName;
   private String messageName;

   @Override
   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
      if (parameterMap != null && !parameterMap.isEmpty() && parameterMap.containsKey("projectName")) {
         this.platformName = (String)parameterMap.get("platformName") == null ? "mycarenet" : (String)parameterMap.get("platformName");
         this.projectName = (String)parameterMap.get("projectName");
         this.messageName = (String)parameterMap.get("messageName");
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, new Object[]{"missing config parameters for initialize of BlobBuilder , check factory method call"});
      }
   }

   @Override
   public Blob build(byte[] input) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      String usedProjectName = this.getProjectPropertiesValue();
      String id = this.platformName + ".blobbuilder." + usedProjectName + ".id";
      ConfigValidator validator = this.getProperties(id);
      return this.build(input, validator.getProperty(id));
   }

   @Override
   public Blob build(byte[] input, String id) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      String usedProjectName = this.getProjectPropertiesValue();
      String baseProperty = this.platformName + ".blobbuilder." + usedProjectName;
      String basePropertyWithMessageName = baseProperty + (this.messageName == null ? "" : "." + this.messageName);
      String projectDefaultEncodingTypeProperty = baseProperty + ".encodingtype";
      String projectDefaultContentTypeProperty = baseProperty + ".contenttype";
      String projectDefaultContentEncryptionProperty = baseProperty + ".contentencryption";
      String encodingTypePropertyWithMessageName = basePropertyWithMessageName + ".encodingtype";
      String contentTypePropertyWithMessageName = basePropertyWithMessageName + ".contenttype";
      String contentEncryptionPropertyWithMessageName = basePropertyWithMessageName + ".contentencryption";
      ConfigValidator validator = this.getProperties();
      String encodingType = validator.getProperty(encodingTypePropertyWithMessageName);
      String contentType = validator.getProperty(contentTypePropertyWithMessageName);
      String contentEncryption = validator.getProperty(contentEncryptionPropertyWithMessageName);
      if (encodingType == null) {
         encodingType = validator.getProperty(projectDefaultEncodingTypeProperty);
      }

      if (contentType == null) {
         contentType = validator.getProperty(projectDefaultContentTypeProperty);
      }

      if (contentEncryption == null) {
         contentEncryption = validator.getProperty(projectDefaultContentEncryptionProperty);
   }

      return this.build(input, encodingType, id, contentType, (String)null, contentEncryption);
   }

   @Override
   public Blob build(byte[] input, String encodingType, String id, String contentType) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      return this.build(input, encodingType, id, contentType, null);
   }

   @Override
   public Blob build(byte[] input, String encodingType, String id, String contentType, String messageName) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      return this.build(input, encodingType, id, contentType, messageName, null);
   }

   @Override
   public Blob build(byte[] input, String encodingType, String id, String contentType, String messageName, String contentEncryption) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      return this.build(input, BlobAttributeValues.builder().encodingType(encodingType).id(id).contentType(contentType).contentEncryption(contentEncryption).messageName(messageName).build());
   }

   @Override
   public Blob build(byte[] input, String encodingType, String id, String contentType, String messageName, String messageVersion, String contentEncryption) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      return this.build(input, BlobAttributeValues.builder().encodingType(encodingType).id(id).contentType(contentType).contentEncryption(contentEncryption).messageName(messageName).messageVersion(messageVersion).build());
   }


   @Override
   public Blob build(byte[] input, BlobAttributeValues blobAttributes) throws TechnicalConnectorException {
      if (input != null && input.length != 0) {
         if (blobAttributes.getContentType() != null && !blobAttributes.getContentType().isEmpty()) {
            Blob newBlob = new Blob();
            newBlob.setContentEncoding("none");
            byte[] buff = input;
            if (blobAttributes.getEncodingType().equals("deflate")) {
               newBlob.setContentEncoding(blobAttributes.getEncodingType());
               buff = ConnectorIOUtils.compress(input, "deflate");
            }

            newBlob.setContent(buff);
            newBlob.setContentType(blobAttributes.getContentType());
            newBlob.setId(blobAttributes.getId());
            newBlob.setMessageName(blobAttributes.getMessageName());
            newBlob.setHashValue((byte[])null);
            newBlob.setContentEncryption(blobAttributes.getContentEncryption());
            newBlob.setMessageVersion(blobAttributes.getMessageVersion());
            return newBlob;
         } else {
            throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.PARAMETER_NULL, null, "String contentType");
         }
      } else {
         throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.PARAMETER_NULL, (Blob)null, new Object[]{"byte[] input"});
      }
   }

   @Override
   public byte[] checkAndRetrieveContent(Blob blob) throws TechnicalConnectorException {
      if (blob == null) {
         throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.PARAMETER_NULL, null, "Blob blob");
      } else {
         return BuilderUtils.checkAndDecompress(blob.getContent(), blob.getContentEncoding(), blob.getHashValue(), blob.isHashTagRequired());
      }
   }

   private String getProjectPropertiesValue() {
      return PropertyUtil.retrieveProjectNameToUse(this.projectName, this.platformName + ".blobbuilder.");
   }

   private ConfigValidator getProperties(String... neededProperties) {
      List<String> expectedProps = new ArrayList();

      for(String neededPropertie : neededProperties) {
         expectedProps.add(neededPropertie);
      }

      return ConfigFactory.getConfigValidator(expectedProps);
   }
}
