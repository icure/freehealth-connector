package be.ehealth.technicalconnector.handler.wss4j;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;

public interface AlgorithmHelper {
   boolean canHandle(Credential var1);

   String determineDigestAlgo(Credential var1) throws TechnicalConnectorException;

   String determineSignatureAlgorithm(Credential var1) throws TechnicalConnectorException;
}
