package be.ehealth.businessconnector.therlink.builders;

import be.ehealth.businessconnector.therlink.domain.Proof;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;

public interface ProofBuilder {
   Proof createProofForEidSigning(Credential var1) throws TechnicalConnectorException, TherLinkBusinessConnectorException;

   Proof createProofForEidSigning(Credential var1, String var2) throws TherLinkBusinessConnectorException;

   Proof createProofForEidReading();

   Proof createProofForIsiReading();

   Proof createSimpleProof(String var1);
}
