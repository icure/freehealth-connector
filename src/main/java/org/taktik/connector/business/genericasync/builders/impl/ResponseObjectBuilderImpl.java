package org.taktik.connector.business.genericasync.builders.impl;

import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.PostResponse;
import be.cin.nip.async.generic.TAck;
import be.cin.nip.async.generic.TAckResponse;
import org.taktik.connector.business.genericasync.builders.ResponseObjectBuilder;
import org.taktik.connector.business.genericasync.domain.ProcessedGetResponse;
import org.taktik.connector.business.genericasync.domain.ProcessedMsgResponse;
import org.taktik.connector.business.genericasync.domain.ProcessedTAckResponse;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorExceptionValues;
import org.taktik.connector.business.genericasync.exception.GenAsyncSignatureValidationConnectorException;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.etee.CryptoFactory;
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import be.cin.encrypted.EncryptedKnownContent;

public class ResponseObjectBuilderImpl implements ResponseObjectBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseObjectBuilderImpl.class);
   private static Configuration config = ConfigFactory.getConfigValidator();

   public final boolean handlePostResponse(PostResponse postResponse) throws GenAsyncBusinessConnectorException {
      if (postResponse != null && postResponse.getReturn() != null) {
         TAck tack = postResponse.getReturn();
         if (!tack.getResultMajor().equals("urn:nip:tack:result:major:success")) {
            throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.SEND_REQUEST_FAILED, new Object[]{"message from tack -> " + tack.getResultMinor()});
         } else {
            boolean hasWarning = false;
            if (tack.getResultMinor() != null && !tack.getResultMinor().isEmpty()) {
               hasWarning = true;
               LOG.info("handlePostResponse : warning : " + tack.getResultMinor());
               LOG.info("handlePostResponse : resultMessage  : " + tack.getResultMessage());
            }

            return hasWarning;
         }
      } else {
         throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{"the postResponse or postResponse.return was null"});
      }
   }

   public final Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse getResponse) throws GenAsyncBusinessConnectorException {
      Map<Object, SignatureVerificationResult> validationResult = new HashMap();
      Iterator i$ = getResponse.getReturn().getTAckResponses().iterator();

      while(i$.hasNext()) {
         TAckResponse value = (TAckResponse)i$.next();
         LOG.debug("handleGetResponse : tackResponse : xades : " + value.getXadesT() + ", tack : " + value.getTAck());
         validationResult.putAll(this.validateXadesT(value, value.getXadesT().getValue()));
      }

      i$ = getResponse.getReturn().getMsgResponses().iterator();

      while(i$.hasNext()) {
         MsgResponse msgResponse = (MsgResponse)i$.next();
         if (msgResponse.getXadesT() != null) {
            validationResult.putAll(this.validateXadesT(msgResponse, msgResponse.getXadesT().getValue()));
         }
      }

      if (!validationResult.isEmpty()) {
         if (LOG.isDebugEnabled()) {
            this.logValidationResult(validationResult);
         }

         throw new GenAsyncSignatureValidationConnectorException(GenAsyncBusinessConnectorExceptionValues.SIGNATURE_VALIDATION_ERROR, validationResult);
      } else {
         return validationResult;
      }
   }
   public <T> ProcessedGetResponse processResponse(GetResponse getResponse, Class<T> clazz, String projectName, KeyStoreCredential credential, Map<String, PrivateKey> hokPrivateKeys) throws GenAsyncBusinessConnectorException, TechnicalConnectorException {
      ProcessedGetResponse processedGetResponse = new ProcessedGetResponse();
      Iterator var6 = getResponse.getReturn().getTAckResponses().iterator();

      while(var6.hasNext()) {
         TAckResponse value = (TAckResponse)var6.next();
         LOG.debug("handleGetResponse : tackResponse : xades : " + value.getXadesT() + ", tack : " + value.getTAck());
         ProcessedTAckResponse tAckResponse = new ProcessedTAckResponse(value, this.validateTAckXadesT(value, value.getXadesT().getValue(), projectName));
         processedGetResponse.getTAckResponses().add(tAckResponse);
      }

      var6 = getResponse.getReturn().getMsgResponses().iterator();

      while(var6.hasNext()) {
         MsgResponse msgResponse = (MsgResponse)var6.next();
         ProcessedMsgResponse processedMsgResponse;
         if (msgResponse.getDetail().getContentEncryption() == null) {
            byte[] unwrappedMessageByteArray = this.getContent(msgResponse, projectName);
            LOG.debug("Content of decrypted business message:[{}]", ConnectorIOUtils.toString(unwrappedMessageByteArray, Charset.UTF_8));
            processedMsgResponse = new ProcessedMsgResponse(msgResponse, this.toBusinessResponse(clazz, unwrappedMessageByteArray), this.validateMsgXadesT(msgResponse, msgResponse.getXadesT().getValue(), projectName), ArrayUtils.isEmpty(msgResponse.getXadesT().getValue()) ? null : unwrappedMessageByteArray);
         } else {
            processedMsgResponse = this.processEncryptedResponse(msgResponse, projectName, clazz, credential, hokPrivateKeys);
         }

         processedGetResponse.getMsgResponses().add(processedMsgResponse);
      }

      return processedGetResponse;
   }

   public <T> ProcessedMsgResponse<T> processEncryptedResponse(MsgResponse msgResponse, String projectName, Class<T> clazz, KeyStoreCredential credential, Map<String, PrivateKey> hokPrivateKeys) throws TechnicalConnectorException, GenAsyncBusinessConnectorException {
      LOG.debug("Analysing msgResponse {}", ConnectorXmlUtils.toString((Object)msgResponse));
      byte[] unwrappedMessageByteArray = this.getContent(msgResponse, projectName);
      Crypto crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys);
      byte[] unsealedData = crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, unwrappedMessageByteArray).getContentAsByte();
      LOG.debug("Unsealed data [{}].", new String(unsealedData));
      EncryptedKnownContent encryptedKnownContent = (EncryptedKnownContent)ConnectorXmlUtils.toObject(unsealedData, EncryptedKnownContent.class);
      byte[] decryptedMessageByteArray;
      if ("deflate".equals(encryptedKnownContent.getBusinessContent().getContentEncoding())) {
         decryptedMessageByteArray = ConnectorIOUtils.decompress(encryptedKnownContent.getBusinessContent().getValue());
      } else {
         decryptedMessageByteArray = encryptedKnownContent.getBusinessContent().getValue();
      }

      LOG.debug("Content of decrypted business message:[{}]", ConnectorIOUtils.toString(decryptedMessageByteArray, Charset.UTF_8));
      ProcessedMsgResponse<T> processedMsgResponse = new ProcessedMsgResponse(msgResponse, this.toBusinessResponse(clazz, decryptedMessageByteArray), encryptedKnownContent, this.validateMsgXadesT(encryptedKnownContent, encryptedKnownContent.getXades(), projectName), ArrayUtils.isEmpty(encryptedKnownContent.getXades()) ? null : unsealedData);
      return processedMsgResponse;
   }

   private <T> T toBusinessResponse(Class<T> clazz, byte[] unwrappedMessageByteArray) {
      return clazz.equals(byte[].class) ? (T) unwrappedMessageByteArray : ConnectorXmlUtils.toObject(unwrappedMessageByteArray, clazz);
   }

   public byte[] getContent(MsgResponse msgResponse, String projectName) throws TechnicalConnectorException {
      Blob mappedBlob = DomainBlobMapper.mapToBlob(msgResponse.getDetail());
      mappedBlob.setHashTagRequired(config.getBooleanProperty("genericasync" + projectName + ".hashtagrequired", false));
      return BlobBuilderFactory.getBlobBuilder("genericasync").checkAndRetrieveContent(mappedBlob);
   }

   public SignatureVerificationResult validateTAckXadesT(Object value, byte[] xadesT, String projectName) throws GenAsyncBusinessConnectorException {
      return this.validateXadesT(value, xadesT, projectName == null ? false : config.getBooleanProperty("GENERICASYNC" + projectName + ".validation.xades.tack.follownestedmanifest", false));
   }

   public SignatureVerificationResult validateMsgXadesT(Object value, byte[] xadesT, String projectName) throws GenAsyncBusinessConnectorException {
      return this.validateXadesT(value, xadesT, projectName == null ? false : config.getBooleanProperty("GENERICASYNC" + projectName + ".validation.xades.msg.follownestedmanifest", false));
   }

   private void logValidationResult(Map<Object, SignatureVerificationResult> validationResults) {
      LOG.debug("validationResults : -------------------------");
      Iterator i$ = validationResults.keySet().iterator();

      while(i$.hasNext()) {
         Object key = i$.next();
         SignatureVerificationResult signatureVerificationResult = (SignatureVerificationResult)validationResults.get(key);
         StringBuilder errorsSb = new StringBuilder();
         Iterator ii$ = signatureVerificationResult.getErrors().iterator();

         while(ii$.hasNext()) {
            SignatureVerificationError error = (SignatureVerificationError)ii$.next();
            errorsSb.append(error).append(" ");
         }

         LOG.debug("key : " + key + "\t" + " validationResult errors : " + errorsSb.toString());
      }

      LOG.debug("--------------------------------------");
   }

   private Map<Object, SignatureVerificationResult> validateXadesT(Object value, byte[] xadesT) throws GenAsyncBusinessConnectorException {
      Map<Object, SignatureVerificationResult> vResult = new HashMap();
      if (!ArrayUtils.isEmpty(xadesT)) {
         byte[] signedByteArray = ConnectorXmlUtils.toByteArray(value);
         HashMap options = new HashMap();

         try {
            SignatureVerificationResult result = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T).verify(signedByteArray, xadesT, options);
            if (!result.isValid()) {
               vResult.put(value, result);
            }
         } catch (TechnicalConnectorException var7) {
            throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.SIGNATURE_VALIDATION_ERROR, var7, new Object[]{var7.getMessage()});
         }
      }

      return vResult;
   }

   private SignatureVerificationResult validateXadesT(Object value, byte[] xadesT, boolean followNestedManifest) throws GenAsyncBusinessConnectorException {
      if (!ArrayUtils.isEmpty(xadesT)) {
         byte[] signedByteArray = ConnectorXmlUtils.toByteArray(value);
         Map<String, Object> options = new HashMap();
         options.put("followNestedManifest", followNestedManifest);

         try {
            return SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T).verify((byte[])signedByteArray, (byte[])xadesT, options);
         } catch (TechnicalConnectorException var7) {
            throw new GenAsyncBusinessConnectorException(GenAsyncBusinessConnectorExceptionValues.SIGNATURE_VALIDATION_ERROR, var7, new Object[]{var7.getMessage()});
         }
      } else {
         return null;
      }
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
   }
}
