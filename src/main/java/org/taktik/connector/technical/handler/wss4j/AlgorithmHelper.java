package org.taktik.connector.technical.handler.wss4j;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;

public interface AlgorithmHelper {
   boolean canHandle(Credential var1);

   String determineDigestAlgo(Credential var1) throws TechnicalConnectorException;

   String determineSignatureAlgorithm(Credential var1) throws TechnicalConnectorException;
}
