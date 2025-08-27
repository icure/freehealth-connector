package be.fgov.ehealth.technicalconnector.ra.mapper;

import be.fgov.ehealth.certra.core.v2.CertificateIdentifierType;
import be.fgov.ehealth.certra.core.v2.ContactDataType;
import be.fgov.ehealth.certra.core.v2.ContractType;
import be.fgov.ehealth.certra.core.v2.OrganizationType;
import be.fgov.ehealth.certra.core.v2.RevocationContractType;
import be.fgov.ehealth.certra.core.v2.TextType;
import be.fgov.ehealth.certra.protocol.v2.GenerateCertificateResponse;
import be.fgov.ehealth.certra.protocol.v2.GetActorQualitiesResponse;
import be.fgov.ehealth.certra.protocol.v2.SubmitCSRForForeignerResponse;
import be.fgov.ehealth.commons.core.v2.ActorType;
import be.fgov.ehealth.commons.core.v2.Id;
import be.fgov.ehealth.technicalconnector.ra.domain.Actor;
import be.fgov.ehealth.technicalconnector.ra.domain.ActorId;
import be.fgov.ehealth.technicalconnector.ra.domain.ActorQualities;
import be.fgov.ehealth.technicalconnector.ra.domain.Certificate;
import be.fgov.ehealth.technicalconnector.ra.domain.CertificateIdentifier;
import be.fgov.ehealth.technicalconnector.ra.domain.ContactData;
import be.fgov.ehealth.technicalconnector.ra.domain.GeneratedContract;
import be.fgov.ehealth.technicalconnector.ra.domain.GeneratedRevocationContract;
import be.fgov.ehealth.technicalconnector.ra.domain.LocalizedString;
import be.fgov.ehealth.technicalconnector.ra.domain.LocalizedText;
import be.fgov.ehealth.technicalconnector.ra.domain.Organization;
import be.fgov.ehealth.technicalconnector.ra.domain.SubmitCSRForForeignerResponseInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RaMapperSelmaGeneratedClass implements RaMapper {
   public final Organization asOrganization(OrganizationType inOrganizationType) {
      Organization out = null;
      if (inOrganizationType != null) {
         out = new Organization();
         out.setIdentifier(this.asActorId(inOrganizationType.getIdentifier()));
         out.setLocalizedName(this.asLocalizedText(inOrganizationType.getName()));
      }

      return out;
   }

   public final LocalizedText asLocalizedText(TextType inTextType) {
      LocalizedText out = null;
      if (inTextType != null) {
         out = new LocalizedText();
         if (inTextType.getValues() != null) {
            ArrayList<LocalizedString> avaluesTmpCollection = new ArrayList(inTextType.getValues().size());
            Iterator i$ = inTextType.getValues().iterator();

            while(i$.hasNext()) {
               be.fgov.ehealth.certra.core.v2.LocalizedString avaluesItem = (be.fgov.ehealth.certra.core.v2.LocalizedString)i$.next();
               avaluesTmpCollection.add(this.asLocalizedString(avaluesItem));
            }

            out.setValues(avaluesTmpCollection);
         } else {
            out.setValues((List)null);
         }
      }

      return out;
   }

   public final LocalizedString asLocalizedString(be.fgov.ehealth.certra.core.v2.LocalizedString inLocalizedString) {
      LocalizedString out = null;
      if (inLocalizedString != null) {
         out = new LocalizedString();
         out.setLang(inLocalizedString.getLang());
         out.setValue(inLocalizedString.getValue());
      }

      return out;
   }

   public final ActorId asActorId(Id inId) {
      ActorId out = null;
      if (inId != null) {
         out = new ActorId();
         out.setType(inId.getType());
         out.setValue(inId.getValue());
      }

      return out;
   }

   public final TextType asTextType(LocalizedText inLocalizedText) {
      TextType out = null;
      if (inLocalizedText != null) {
         out = new TextType();
         if (inLocalizedText.getValues() != null) {
            List avaluesTmpCollection = out.getValues();
            Iterator i$ = inLocalizedText.getValues().iterator();

            while(i$.hasNext()) {
               LocalizedString avaluesItem = (LocalizedString)i$.next();
               avaluesTmpCollection.add(this.asLocalizedString(avaluesItem));
            }
         }
      }

      return out;
   }

   public final be.fgov.ehealth.certra.core.v2.LocalizedString asLocalizedString(LocalizedString inLocalizedString) {
      be.fgov.ehealth.certra.core.v2.LocalizedString out = null;
      if (inLocalizedString != null) {
         out = new be.fgov.ehealth.certra.core.v2.LocalizedString();
         out.setLang(inLocalizedString.getLang());
         out.setValue(inLocalizedString.getValue());
      }

      return out;
   }

   public final ActorType asActorType(Actor inActor) {
      ActorType out = null;
      if (inActor != null) {
         out = new ActorType();
         List aidsTmpCollection;
         Iterator i$;
         if (inActor.getFirstNames() != null) {
            aidsTmpCollection = out.getFirstNames();
            i$ = inActor.getFirstNames().iterator();

            while(i$.hasNext()) {
               String afirstnamesItem = (String)i$.next();
               aidsTmpCollection.add(afirstnamesItem);
            }
         }

         if (inActor.getIds() != null) {
            aidsTmpCollection = out.getIds();
            i$ = inActor.getIds().iterator();

            while(i$.hasNext()) {
               ActorId aidsItem = (ActorId)i$.next();
               aidsTmpCollection.add(this.asId(aidsItem));
            }
         }

         out.setName(inActor.getName());
      }

      return out;
   }

   public final Id asId(ActorId inActorId) {
      Id out = null;
      if (inActorId != null) {
         out = new Id();
         out.setType(inActorId.getType());
         out.setValue(inActorId.getValue());
      }

      return out;
   }

   public final CertificateIdentifierType asCertificateIdentifierType(CertificateIdentifier inCertificateIdentifier) {
      CertificateIdentifierType out = null;
      if (inCertificateIdentifier != null) {
         out = new CertificateIdentifierType();
         out.setActor(this.asActorType(inCertificateIdentifier.getActor()));
         out.setApplicationId(inCertificateIdentifier.getApplicationId());
      }

      return out;
   }

   public final Certificate asCertificate(GenerateCertificateResponse inGenerateCertificateResponse) {
      Certificate out = null;
      if (inGenerateCertificateResponse != null) {
         out = new Certificate();
         out.setAutomaticallyValidated(inGenerateCertificateResponse.isAutomaticallyValidated());
         if (inGenerateCertificateResponse.getPublicKeyIdentifier() != null) {
            out.setPublicKeyIdentifier(new byte[inGenerateCertificateResponse.getPublicKeyIdentifier().length]);
            System.arraycopy(inGenerateCertificateResponse.getPublicKeyIdentifier(), 0, out.getPublicKeyIdentifier(), 0, inGenerateCertificateResponse.getPublicKeyIdentifier().length);
         } else {
            out.setPublicKeyIdentifier((byte[])null);
         }

         if (inGenerateCertificateResponse.getReplacesCertificate() != null) {
            out.setReplacesCertificate(new byte[inGenerateCertificateResponse.getReplacesCertificate().length]);
            System.arraycopy(inGenerateCertificateResponse.getReplacesCertificate(), 0, out.getReplacesCertificate(), 0, inGenerateCertificateResponse.getReplacesCertificate().length);
         } else {
            out.setReplacesCertificate((byte[])null);
         }
      }

      return out;
   }

   public final GeneratedContract asGeneratedContract(ContractType inContractType) {
      GeneratedContract out = null;
      if (inContractType != null) {
         out = new GeneratedContract();
         out.setDN(inContractType.getDN());
         out.setSigner(this.asActor(inContractType.getSigner()));
         out.setText(this.asLocalizedText(inContractType.getText()));
      }

      return out;
   }

   public final Actor asActor(ActorType inActorType) {
      Actor out = null;
      if (inActorType != null) {
         out = new Actor();
         ArrayList aidsTmpCollection;
         Iterator i$;
         if (inActorType.getFirstNames() == null) {
            out.setFirstNames((List)null);
         } else {
            aidsTmpCollection = new ArrayList(inActorType.getFirstNames().size());
            i$ = inActorType.getFirstNames().iterator();

            while(i$.hasNext()) {
               String afirstnamesItem = (String)i$.next();
               aidsTmpCollection.add(afirstnamesItem);
            }

            out.setFirstNames(aidsTmpCollection);
         }

         if (inActorType.getIds() == null) {
            out.setIds((List)null);
         } else {
            aidsTmpCollection = new ArrayList(inActorType.getIds().size());
            i$ = inActorType.getIds().iterator();

            while(i$.hasNext()) {
               Id aidsItem = (Id)i$.next();
               aidsTmpCollection.add(this.asActorId(aidsItem));
            }

            out.setIds(aidsTmpCollection);
         }

         out.setName(inActorType.getName());
      }

      return out;
   }

   public final ContactDataType asContactDataType(ContactData inContactData) {
      ContactDataType out = null;
      if (inContactData != null) {
         out = new ContactDataType();
         out.setEmailGeneral(inContactData.getEmailGeneral());
         out.setEmailPrivate(inContactData.getEmailPrivate());
         out.setPhoneGeneral(inContactData.getPhoneGeneral());
         out.setPhonePrivate(inContactData.getPhonePrivate());
      }

      return out;
   }

   public final GeneratedRevocationContract asRevocationContract(RevocationContractType inRevocationContractType) {
      GeneratedRevocationContract out = null;
      if (inRevocationContractType != null) {
         out = new GeneratedRevocationContract();
         out.setDn(inRevocationContractType.getDN());
         out.setRevocationReason(inRevocationContractType.getRevocationReason());
         out.setSigner(this.asActor(inRevocationContractType.getSigner()));
         out.setText(this.asLocalizedText(inRevocationContractType.getText()));
      }

      return out;
   }

   public final SubmitCSRForForeignerResponseInfo asSubmitCSRForForeignerResponseInfo(SubmitCSRForForeignerResponse inSubmitCSRForForeignerResponse) {
      SubmitCSRForForeignerResponseInfo out = null;
      if (inSubmitCSRForForeignerResponse != null) {
         out = new SubmitCSRForForeignerResponseInfo();
         out.setExpirationDate(inSubmitCSRForForeignerResponse.getExpirationDate());
         out.setValidationUrl(inSubmitCSRForForeignerResponse.getValidationUrl());
      }

      return out;
   }

   public final ActorQualities asActorQualities(GetActorQualitiesResponse inGetActorQualitiesResponse) {
      ActorQualities out = null;
      if (inGetActorQualitiesResponse != null) {
         out = new ActorQualities();
         out.setNaturalPersonAuthorization(inGetActorQualitiesResponse.isNaturalPersonAuthorization());
         if (inGetActorQualitiesResponse.getOrganizationAuthorizations() != null) {
            ArrayList<Organization> aorganizationauthorizationsTmpCollection = new ArrayList(inGetActorQualitiesResponse.getOrganizationAuthorizations().size());
            Iterator i$ = inGetActorQualitiesResponse.getOrganizationAuthorizations().iterator();

            while(i$.hasNext()) {
               OrganizationType aorganizationauthorizationsItem = (OrganizationType)i$.next();
               aorganizationauthorizationsTmpCollection.add(this.asOrganization(aorganizationauthorizationsItem));
            }

            out.setOrganizationAuthorizations(aorganizationauthorizationsTmpCollection);
         } else {
            out.setOrganizationAuthorizations((List)null);
         }
      }

      return out;
   }


   public final RevocationContractType asRevocationContractType(GeneratedRevocationContract inGeneratedRevocationContract) {
      RevocationContractType out = null;
      if (inGeneratedRevocationContract != null) {
         out = new RevocationContractType();
         out.setDN(inGeneratedRevocationContract.getDn());
         out.setRevocationReason(inGeneratedRevocationContract.getRevocationReason());
         out.setSigner(this.asActorType(inGeneratedRevocationContract.getSigner()));
         out.setText(this.asTextType(inGeneratedRevocationContract.getText()));
      }

      return out;
   }
}
