package be.fgov.ehealth.technicalconnector.ra.mapper;

import be.fgov.ehealth.certra.core.v2.ContactDataType;
import be.fgov.ehealth.certra.core.v2.ContractType;
import be.fgov.ehealth.certra.core.v2.OrganizationType;
import be.fgov.ehealth.certra.core.v2.RevocationContractType;
import be.fgov.ehealth.certra.protocol.v2.GenerateCertificateResponse;
import be.fgov.ehealth.certra.protocol.v2.GenerateContractRequest;
import be.fgov.ehealth.certra.protocol.v2.GenerateRevocationContractRequest;
import be.fgov.ehealth.certra.protocol.v2.GetActorQualitiesResponse;
import be.fgov.ehealth.certra.protocol.v2.RevokeRequest;
import be.fgov.ehealth.certra.protocol.v2.SubmitCSRForForeignerRequest;
import be.fgov.ehealth.certra.protocol.v2.SubmitCSRForForeignerResponse;
import be.fgov.ehealth.technicalconnector.ra.domain.ActorQualities;
import be.fgov.ehealth.technicalconnector.ra.domain.Certificate;
import be.fgov.ehealth.technicalconnector.ra.domain.ContactData;
import be.fgov.ehealth.technicalconnector.ra.domain.GeneratedContract;
import be.fgov.ehealth.technicalconnector.ra.domain.GeneratedRevocationContract;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import be.fgov.ehealth.technicalconnector.ra.domain.SubmitCSRForForeignerResponseInfo;

public interface RaMapper {
   Organization asOrganization(OrganizationType var1);

   Certificate asCertificate(GenerateCertificateResponse var1);

   GeneratedContract asGeneratedContract(ContractType var1);

   ContactDataType asContactDataType(ContactData var1);

   GeneratedRevocationContract asRevocationContract(RevocationContractType var1);

   SubmitCSRForForeignerResponseInfo asSubmitCSRForForeignerResponseInfo(SubmitCSRForForeignerResponse var1);

   ActorQualities asActorQualities(GetActorQualitiesResponse var1);

}
