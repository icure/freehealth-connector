package org.taktik.connector.business.genericasync.builders;

import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.PostResponse;
import org.taktik.connector.business.genericasync.domain.ProcessedGetResponse;
import org.taktik.connector.business.genericasync.domain.ProcessedMsgResponse;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

import java.security.PrivateKey;
import java.util.Map;

public interface ResponseObjectBuilder extends ConfigurableImplementation {
   boolean handlePostResponse(PostResponse var1) throws GenAsyncBusinessConnectorException;

   Map<Object, SignatureVerificationResult> handleGetResponse(GetResponse var1) throws GenAsyncBusinessConnectorException;
   <T> ProcessedGetResponse processResponse(GetResponse var1, Class<T> var2, String var3, KeyStoreCredential var4, Map<String, PrivateKey> var5) throws GenAsyncBusinessConnectorException, TechnicalConnectorException;

   <T> ProcessedMsgResponse<T> processEncryptedResponse(MsgResponse var1, String var2, Class<T> var3, KeyStoreCredential var4, Map<String, PrivateKey> var5) throws TechnicalConnectorException, GenAsyncBusinessConnectorException;

   SignatureVerificationResult validateTAckXadesT(Object var1, byte[] var2, String var3) throws GenAsyncBusinessConnectorException;

   SignatureVerificationResult validateMsgXadesT(Object var1, byte[] var2, String var3) throws GenAsyncBusinessConnectorException;
}
