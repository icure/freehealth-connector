package be.fgov.ehealth.technicalconnector.ra.service;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import be.fgov.ehealth.certra.core.v2.CertificateInfoType;
import be.fgov.ehealth.certra.protocol.v2.GetGenericOrganizationTypesResponse;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import be.fgov.ehealth.technicalconnector.ra.domain.Result;
import java.security.cert.X509Certificate;
import java.util.List;

public interface AuthenticationCertificateRegistrationService {
   Result<X509Certificate[]> getCertificate(byte[] var1) throws TechnicalConnectorException;

   Result<GetGenericOrganizationTypesResponse> getOrganizationList() throws TechnicalConnectorException;

   Result<List<String>> getApplicationIdList(Organization var1) throws TechnicalConnectorException;

   Result<CertificateInfoType> getCertificateInfoForAuthenticationCertificate(Credential var1) throws TechnicalConnectorException;

   Result<List<CertificateInfoType>> getCertificateInfoForCitizen() throws TechnicalConnectorException;

}
