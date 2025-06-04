
package be.fgov.ehealth.mediprima.protocol.v2;

import be.fgov.ehealth.commons.core.v2.*;
import be.fgov.ehealth.commons.protocol.v2.*;
import be.fgov.ehealth.mediprima.core.v2.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the be.fgov.ehealth.mediprima.protocol.v2 package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConsultCarmedInterventionResponse_QNAME = new QName("urn:be:fgov:ehealth:mediprima:protocol:v2", "ConsultCarmedInterventionResponse");
    private final static QName _Status_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Status");
    private final static QName _StatusDetail_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "StatusDetail");
    private final static QName _MedicalCardRegistryStatus_QNAME = new QName("urn:be:fgov:ehealth:mediprima:core:v2", "MedicalCardRegistryStatus");
    private final static QName _Id_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Id");
    private final static QName _Author_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Author");
    private final static QName _Patient_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Patient");
    private final static QName _CbssStatus_QNAME = new QName("urn:be:fgov:ehealth:mediprima:core:v2", "CbssStatus");
    private final static QName _HcParty_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "HcParty");
    private final static QName _StatusMessage_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "StatusMessage");
    private final static QName _ConsultCarmedInterventionRequest_QNAME = new QName("urn:be:fgov:ehealth:mediprima:protocol:v2", "ConsultCarmedInterventionRequest");
    private final static QName _StatusCode_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "StatusCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: be.fgov.ehealth.mediprima.protocol.v2
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ParamedicType }
     *
     */
    public ParamedicType createParamedicType() {
        return new ParamedicType();
    }

    /**
     * Create an instance of {@link ConsultCarmedInterventionRequestType }
     *
     */
    public ConsultCarmedInterventionRequestType createConsultCarmedInterventionRequestType() {
        return new ConsultCarmedInterventionRequestType();
    }

    /**
     * Create an instance of {@link ConsultCarmedInterventionResponseType }
     *
     */
    public ConsultCarmedInterventionResponseType createConsultCarmedInterventionResponseType() {
        return new ConsultCarmedInterventionResponseType();
    }

    /**
     * Create an instance of {@link CbssStatusType }
     *
     */
    public CbssStatusType createCbssStatusType() {
        return new CbssStatusType();
    }

    /**
     * Create an instance of {@link MedicalCardRegistryStatusType }
     *
     */
    public MedicalCardRegistryStatusType createMedicalCardRegistryStatusType() {
        return new MedicalCardRegistryStatusType();
    }

    /**
     * Create an instance of {@link ProsthesisType }
     *
     */
    public ProsthesisType createProsthesisType() {
        return new ProsthesisType();
    }

    /**
     * Create an instance of {@link RefundPodmiSppisType }
     *
     */
    public RefundPodmiSppisType createRefundPodmiSppisType() {
        return new RefundPodmiSppisType();
    }

    /**
     * Create an instance of {@link MiscellaneousType }
     *
     */
    public MiscellaneousType createMiscellaneousType() {
        return new MiscellaneousType();
    }

    /**
     * Create an instance of {@link PeriodType }
     *
     */
    public PeriodType createPeriodType() {
        return new PeriodType();
    }

    /**
     * Create an instance of {@link CompanyListType }
     *
     */
    public CompanyListType createCompanyListType() {
        return new CompanyListType();
    }

    /**
     * Create an instance of {@link BeneficiaryType }
     *
     */
    public BeneficiaryType createBeneficiaryType() {
        return new BeneficiaryType();
    }

    /**
     * Create an instance of {@link AllowedSupplementsType }
     *
     */
    public AllowedSupplementsType createAllowedSupplementsType() {
        return new AllowedSupplementsType();
    }

    /**
     * Create an instance of {@link CarmedAttributedListType }
     *
     */
    public CarmedAttributedListType createCarmedAttributedListType() {
        return new CarmedAttributedListType();
    }

    /**
     * Create an instance of {@link NameType }
     *
     */
    public NameType createNameType() {
        return new NameType();
    }

    /**
     * Create an instance of {@link HospitalizationType }
     *
     */
    public HospitalizationType createHospitalizationType() {
        return new HospitalizationType();
    }

    /**
     * Create an instance of {@link SupplementTypeListType }
     *
     */
    public SupplementTypeListType createSupplementTypeListType() {
        return new SupplementTypeListType();
    }

    /**
     * Create an instance of {@link InformationType }
     *
     */
    public InformationType createInformationType() {
        return new InformationType();
    }

    /**
     * Create an instance of {@link PharmaceuticalDrugType }
     *
     */
    public PharmaceuticalDrugType createPharmaceuticalDrugType() {
        return new PharmaceuticalDrugType();
    }

    /**
     * Create an instance of {@link PodmiSppisPartType }
     *
     */
    public PodmiSppisPartType createPodmiSppisPartType() {
        return new PodmiSppisPartType();
    }

    /**
     * Create an instance of {@link ConsultCarmedDataType }
     *
     */
    public ConsultCarmedDataType createConsultCarmedDataType() {
        return new ConsultCarmedDataType();
    }

    /**
     * Create an instance of {@link CarmedContentCareType }
     *
     */
    public CarmedContentCareType createCarmedContentCareType() {
        return new CarmedContentCareType();
    }

    /**
     * Create an instance of {@link PswcSupportType }
     *
     */
    public PswcSupportType createPswcSupportType() {
        return new PswcSupportType();
    }

    /**
     * Create an instance of {@link MedicalCardRegistryMessageType }
     *
     */
    public MedicalCardRegistryMessageType createMedicalCardRegistryMessageType() {
        return new MedicalCardRegistryMessageType();
    }

    /**
     * Create an instance of {@link ConsultCarmedInterventionResultType }
     *
     */
    public ConsultCarmedInterventionResultType createConsultCarmedInterventionResultType() {
        return new ConsultCarmedInterventionResultType();
    }

    /**
     * Create an instance of {@link ZivAmiPatientPartType }
     *
     */
    public ZivAmiPatientPartType createZivAmiPatientPartType() {
        return new ZivAmiPatientPartType();
    }

    /**
     * Create an instance of {@link BySsinType }
     *
     */
    public BySsinType createBySsinType() {
        return new BySsinType();
    }

    /**
     * Create an instance of {@link MedicalTransportationType }
     *
     */
    public MedicalTransportationType createMedicalTransportationType() {
        return new MedicalTransportationType();
    }

    /**
     * Create an instance of {@link NihiiNumberListType }
     *
     */
    public NihiiNumberListType createNihiiNumberListType() {
        return new NihiiNumberListType();
    }

    /**
     * Create an instance of {@link DoctorType }
     *
     */
    public DoctorType createDoctorType() {
        return new DoctorType();
    }

    /**
     * Create an instance of {@link OcmwCpasType }
     *
     */
    public OcmwCpasType createOcmwCpasType() {
        return new OcmwCpasType();
    }

    /**
     * Create an instance of {@link MedicalCoverType }
     *
     */
    public MedicalCoverType createMedicalCoverType() {
        return new MedicalCoverType();
    }

    /**
     * Create an instance of {@link MedicalCoverCommonInformationType }
     *
     */
    public MedicalCoverCommonInformationType createMedicalCoverCommonInformationType() {
        return new MedicalCoverCommonInformationType();
    }

    /**
     * Create an instance of {@link CarmedIdentifierType }
     *
     */
    public CarmedIdentifierType createCarmedIdentifierType() {
        return new CarmedIdentifierType();
    }

    /**
     * Create an instance of {@link StatusType }
     *
     */
    public StatusType createStatusType() {
        return new StatusType();
    }

    /**
     * Create an instance of {@link StatusDetailType }
     *
     */
    public StatusDetailType createStatusDetailType() {
        return new StatusDetailType();
    }

    /**
     * Create an instance of {@link AuthorType }
     *
     */
    public AuthorType createAuthorType() {
        return new AuthorType();
    }

    /**
     * Create an instance of {@link ActorType }
     *
     */
    public ActorType createActorType() {
        return new ActorType();
    }

    /**
     * Create an instance of {@link IdType }
     *
     */
    public IdType createIdType() {
        return new IdType();
    }

    /**
     * Create an instance of {@link StatusCodeType }
     *
     */
    public StatusCodeType createStatusCodeType() {
        return new StatusCodeType();
    }

    /**
     * Create an instance of {@link PaginationResponseType }
     *
     */
    public PaginationResponseType createPaginationResponseType() {
        return new PaginationResponseType();
    }

    /**
     * Create an instance of {@link AuthorRequestType }
     *
     */
    public AuthorRequestType createAuthorRequestType() {
        return new AuthorRequestType();
    }

    /**
     * Create an instance of {@link AuthorPaginationRequestType }
     *
     */
    public AuthorPaginationRequestType createAuthorPaginationRequestType() {
        return new AuthorPaginationRequestType();
    }

    /**
     * Create an instance of {@link PaginationRequestType }
     *
     */
    public PaginationRequestType createPaginationRequestType() {
        return new PaginationRequestType();
    }

    /**
     * Create an instance of {@link PaginationStatusResponseType }
     *
     */
    public PaginationStatusResponseType createPaginationStatusResponseType() {
        return new PaginationStatusResponseType();
    }

    /**
     * Create an instance of {@link StatusResponseType }
     *
     */
    public StatusResponseType createStatusResponseType() {
        return new StatusResponseType();
    }

    /**
     * Create an instance of {@link RequestType }
     *
     */
    public RequestType createRequestType() {
        return new RequestType();
    }

    /**
     * Create an instance of {@link ResponseType }
     *
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link ParamedicType.ProviderList }
     *
     */
    public ParamedicType.ProviderList createParamedicTypeProviderList() {
        return new ParamedicType.ProviderList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultCarmedInterventionResponseType }{@code >}}
     *
     */
    @XmlElementDecl(name = "ConsultCarmedInterventionResponse")
    public JAXBElement<ConsultCarmedInterventionResponseType> createConsultCarmedInterventionResponse(ConsultCarmedInterventionResponseType value) {
        return new JAXBElement<ConsultCarmedInterventionResponseType>(_ConsultCarmedInterventionResponse_QNAME, ConsultCarmedInterventionResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusType }{@code >}}
     *
     */
    @XmlElementDecl(name = "Status")
    public JAXBElement<StatusType> createStatus(StatusType value) {
        return new JAXBElement<StatusType>(_Status_QNAME, StatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusDetailType }{@code >}}
     *
     */
    @XmlElementDecl(name = "StatusDetail")
    public JAXBElement<StatusDetailType> createStatusDetail(StatusDetailType value) {
        return new JAXBElement<StatusDetailType>(_StatusDetail_QNAME, StatusDetailType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MedicalCardRegistryStatusType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:core:v2", name = "MedicalCardRegistryStatus")
    public JAXBElement<MedicalCardRegistryStatusType> createMedicalCardRegistryStatus(MedicalCardRegistryStatusType value) {
        return new JAXBElement<MedicalCardRegistryStatusType>(_MedicalCardRegistryStatus_QNAME, MedicalCardRegistryStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdType }{@code >}}
     *
     */
    @XmlElementDecl(name = "Id")
    public JAXBElement<IdType> createId(IdType value) {
        return new JAXBElement<IdType>(_Id_QNAME, IdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorType }{@code >}}
     *
     */
    @XmlElementDecl(name = "Author")
    public JAXBElement<AuthorType> createAuthor(AuthorType value) {
        return new JAXBElement<AuthorType>(_Author_QNAME, AuthorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActorType }{@code >}}
     *
     */
    @XmlElementDecl(name = "Patient")
    public JAXBElement<ActorType> createPatient(ActorType value) {
        return new JAXBElement<ActorType>(_Patient_QNAME, ActorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CbssStatusType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:core:v2", name = "CbssStatus")
    public JAXBElement<CbssStatusType> createCbssStatus(CbssStatusType value) {
        return new JAXBElement<CbssStatusType>(_CbssStatus_QNAME, CbssStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActorType }{@code >}}
     *
     */
    @XmlElementDecl(name = "HcParty")
    public JAXBElement<ActorType> createHcParty(ActorType value) {
        return new JAXBElement<ActorType>(_HcParty_QNAME, ActorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(name = "StatusMessage")
    public JAXBElement<String> createStatusMessage(String value) {
        return new JAXBElement<String>(_StatusMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultCarmedInterventionRequestType }{@code >}}
     *
     */
    @XmlElementDecl(name = "ConsultCarmedInterventionRequest")
    public JAXBElement<ConsultCarmedInterventionRequestType> createConsultCarmedInterventionRequest(ConsultCarmedInterventionRequestType value) {
        return new JAXBElement<ConsultCarmedInterventionRequestType>(_ConsultCarmedInterventionRequest_QNAME, ConsultCarmedInterventionRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusCodeType }{@code >}}
     *
     */
    @XmlElementDecl(name = "StatusCode")
    public JAXBElement<StatusCodeType> createStatusCode(StatusCodeType value) {
        return new JAXBElement<StatusCodeType>(_StatusCode_QNAME, StatusCodeType.class, null, value);
    }

}
