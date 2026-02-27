
package be.fgov.ehealth.mediprimaUma.protocol.v1;

import be.fgov.ehealth.mediprimaUma.core.v1.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the be.fgov.ehealth.mediprimaUma.protocol.v1 package.
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

    private final static QName _StatusDetail_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "StatusDetail");
    private final static QName _SearchUrgentMedicalAidAttestationRequest_QNAME = new QName("urn:be:fgov:ehealth:mediprima:uma:protocol:v1", "SearchUrgentMedicalAidAttestationRequest");
    private final static QName _RegistryStatus_QNAME = new QName("urn:be:fgov:ehealth:mediprima:uma:core:v1", "RegistryStatus");
    private final static QName _Id_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Id");
    private final static QName _Author_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Author");
    private final static QName _DeleteUrgentMedicalAidAttestationRequest_QNAME = new QName("urn:be:fgov:ehealth:mediprima:uma:protocol:v1", "DeleteUrgentMedicalAidAttestationRequest");
    private final static QName _SendUrgentMedicalAidAttestationRequest_QNAME = new QName("urn:be:fgov:ehealth:mediprima:uma:protocol:v1", "SendUrgentMedicalAidAttestationRequest");
    private final static QName _Status_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Status");
    private final static QName _SendUrgentMedicalAidAttestationResponse_QNAME = new QName("urn:be:fgov:ehealth:mediprima:uma:protocol:v1", "SendUrgentMedicalAidAttestationResponse");
    private final static QName _DeleteUrgentMedicalAidAttestationResponse_QNAME = new QName("urn:be:fgov:ehealth:mediprima:uma:protocol:v1", "DeleteUrgentMedicalAidAttestationResponse");
    private final static QName _Patient_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "Patient");
    private final static QName _SearchUrgentMedicalAidAttestationResponse_QNAME = new QName("urn:be:fgov:ehealth:mediprima:uma:protocol:v1", "SearchUrgentMedicalAidAttestationResponse");
    private final static QName _HcParty_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "HcParty");
    private final static QName _StatusMessage_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "StatusMessage");
    private final static QName _StatusCode_QNAME = new QName("urn:be:fgov:ehealth:commons:core:v2", "StatusCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: be.fgov.ehealth.mediprimaUma.protocol.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchUrgentMedicalAidAttestationResponseType }
     *
     */
    public SearchUrgentMedicalAidAttestationResponseType createSearchUrgentMedicalAidAttestationResponseType() {
        return new SearchUrgentMedicalAidAttestationResponseType();
    }

    /**
     * Create an instance of {@link DeleteUrgentMedicalAidAttestationResponseType }
     *
     */
    public DeleteUrgentMedicalAidAttestationResponseType createDeleteUrgentMedicalAidAttestationResponseType() {
        return new DeleteUrgentMedicalAidAttestationResponseType();
    }

    /**
     * Create an instance of {@link SendUrgentMedicalAidAttestationResponseType }
     *
     */
    public SendUrgentMedicalAidAttestationResponseType createSendUrgentMedicalAidAttestationResponseType() {
        return new SendUrgentMedicalAidAttestationResponseType();
    }

    /**
     * Create an instance of {@link SearchUrgentMedicalAidAttestationRequestType }
     *
     */
    public SearchUrgentMedicalAidAttestationRequestType createSearchUrgentMedicalAidAttestationRequestType() {
        return new SearchUrgentMedicalAidAttestationRequestType();
    }

    /**
     * Create an instance of {@link SendUrgentMedicalAidAttestationRequestType }
     *
     */
    public SendUrgentMedicalAidAttestationRequestType createSendUrgentMedicalAidAttestationRequestType() {
        return new SendUrgentMedicalAidAttestationRequestType();
    }

    /**
     * Create an instance of {@link DeleteUrgentMedicalAidAttestationRequestType }
     *
     */
    public DeleteUrgentMedicalAidAttestationRequestType createDeleteUrgentMedicalAidAttestationRequestType() {
        return new DeleteUrgentMedicalAidAttestationRequestType();
    }

    /**
     * Create an instance of {@link RegistryStatusType }
     *
     */
    public RegistryStatusType createRegistryStatusType() {
        return new RegistryStatusType();
    }

    /**
     * Create an instance of {@link PeriodType }
     *
     */
    public PeriodType createPeriodType() {
        return new PeriodType();
    }

    /**
     * Create an instance of {@link ErrorType }
     *
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link AttestationType }
     *
     */
    public AttestationType createAttestationType() {
        return new AttestationType();
    }

    /**
     * Create an instance of {@link CriteriaType }
     *
     */
    public CriteriaType createCriteriaType() {
        return new CriteriaType();
    }

    /**
     * Create an instance of {@link NameType }
     *
     */
    public NameType createNameType() {
        return new NameType();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusDetailType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:commons:core:v2", name = "StatusDetail")
    public JAXBElement<StatusDetailType> createStatusDetail(StatusDetailType value) {
        return new JAXBElement<StatusDetailType>(_StatusDetail_QNAME, StatusDetailType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUrgentMedicalAidAttestationRequestType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", name = "SearchUrgentMedicalAidAttestationRequest")
    public JAXBElement<SearchUrgentMedicalAidAttestationRequestType> createSearchUrgentMedicalAidAttestationRequest(SearchUrgentMedicalAidAttestationRequestType value) {
        return new JAXBElement<SearchUrgentMedicalAidAttestationRequestType>(_SearchUrgentMedicalAidAttestationRequest_QNAME, SearchUrgentMedicalAidAttestationRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistryStatusType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:uma:core:v1", name = "RegistryStatus")
    public JAXBElement<RegistryStatusType> createRegistryStatus(RegistryStatusType value) {
        return new JAXBElement<RegistryStatusType>(_RegistryStatus_QNAME, RegistryStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:commons:core:v2", name = "Id")
    public JAXBElement<IdType> createId(IdType value) {
        return new JAXBElement<IdType>(_Id_QNAME, IdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:commons:core:v2", name = "Author")
    public JAXBElement<AuthorType> createAuthor(AuthorType value) {
        return new JAXBElement<AuthorType>(_Author_QNAME, AuthorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUrgentMedicalAidAttestationRequestType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", name = "DeleteUrgentMedicalAidAttestationRequest")
    public JAXBElement<DeleteUrgentMedicalAidAttestationRequestType> createDeleteUrgentMedicalAidAttestationRequest(DeleteUrgentMedicalAidAttestationRequestType value) {
        return new JAXBElement<DeleteUrgentMedicalAidAttestationRequestType>(_DeleteUrgentMedicalAidAttestationRequest_QNAME, DeleteUrgentMedicalAidAttestationRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUrgentMedicalAidAttestationRequestType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", name = "SendUrgentMedicalAidAttestationRequest")
    public JAXBElement<SendUrgentMedicalAidAttestationRequestType> createSendUrgentMedicalAidAttestationRequest(SendUrgentMedicalAidAttestationRequestType value) {
        return new JAXBElement<SendUrgentMedicalAidAttestationRequestType>(_SendUrgentMedicalAidAttestationRequest_QNAME, SendUrgentMedicalAidAttestationRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:commons:core:v2", name = "Status")
    public JAXBElement<StatusType> createStatus(StatusType value) {
        return new JAXBElement<StatusType>(_Status_QNAME, StatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUrgentMedicalAidAttestationResponseType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", name = "SendUrgentMedicalAidAttestationResponse")
    public JAXBElement<SendUrgentMedicalAidAttestationResponseType> createSendUrgentMedicalAidAttestationResponse(SendUrgentMedicalAidAttestationResponseType value) {
        return new JAXBElement<SendUrgentMedicalAidAttestationResponseType>(_SendUrgentMedicalAidAttestationResponse_QNAME, SendUrgentMedicalAidAttestationResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUrgentMedicalAidAttestationResponseType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", name = "DeleteUrgentMedicalAidAttestationResponse")
    public JAXBElement<DeleteUrgentMedicalAidAttestationResponseType> createDeleteUrgentMedicalAidAttestationResponse(DeleteUrgentMedicalAidAttestationResponseType value) {
        return new JAXBElement<DeleteUrgentMedicalAidAttestationResponseType>(_DeleteUrgentMedicalAidAttestationResponse_QNAME, DeleteUrgentMedicalAidAttestationResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActorType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:commons:core:v2", name = "Patient")
    public JAXBElement<ActorType> createPatient(ActorType value) {
        return new JAXBElement<ActorType>(_Patient_QNAME, ActorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUrgentMedicalAidAttestationResponseType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1", name = "SearchUrgentMedicalAidAttestationResponse")
    public JAXBElement<SearchUrgentMedicalAidAttestationResponseType> createSearchUrgentMedicalAidAttestationResponse(SearchUrgentMedicalAidAttestationResponseType value) {
        return new JAXBElement<SearchUrgentMedicalAidAttestationResponseType>(_SearchUrgentMedicalAidAttestationResponse_QNAME, SearchUrgentMedicalAidAttestationResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActorType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:commons:core:v2", name = "HcParty")
    public JAXBElement<ActorType> createHcParty(ActorType value) {
        return new JAXBElement<ActorType>(_HcParty_QNAME, ActorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:commons:core:v2", name = "StatusMessage")
    public JAXBElement<String> createStatusMessage(String value) {
        return new JAXBElement<String>(_StatusMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusCodeType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "urn:be:fgov:ehealth:commons:core:v2", name = "StatusCode")
    public JAXBElement<StatusCodeType> createStatusCode(StatusCodeType value) {
        return new JAXBElement<StatusCodeType>(_StatusCode_QNAME, StatusCodeType.class, null, value);
    }

}
