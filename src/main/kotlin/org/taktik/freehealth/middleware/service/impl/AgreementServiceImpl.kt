package org.taktik.freehealth.middleware.service.impl

import be.cin.encrypted.BusinessContent
import be.cin.encrypted.EncryptedKnownContent
import be.fgov.ehealth.aa.complextype.v1.Speciality
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionRequest
import be.fgov.ehealth.mycarenet.commons.core.v4.CareProviderType
import be.fgov.ehealth.mycarenet.commons.core.v4.CareReceiverIdType
import be.fgov.ehealth.mycarenet.commons.core.v4.CommonInputType
import be.fgov.ehealth.mycarenet.commons.core.v4.IdType
import be.fgov.ehealth.mycarenet.commons.core.v4.LicenseType
import be.fgov.ehealth.mycarenet.commons.core.v4.NihiiType
import be.fgov.ehealth.mycarenet.commons.core.v4.OriginType
import be.fgov.ehealth.mycarenet.commons.core.v4.PackageType
import be.fgov.ehealth.mycarenet.commons.core.v4.RequestType
import be.fgov.ehealth.mycarenet.commons.core.v4.RoutingType
import be.fgov.ehealth.mycarenet.commons.core.v4.ValueRefString
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory
import be.fgov.ehealth.technicalconnector.signature.transformers.EncapsulationTransformer
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.agreement.exception.AgreementBusinessConnectorException
import org.taktik.connector.business.agreement.service.impl.AgreementServiceImpl
import org.taktik.connector.business.agreement.validator.AgreementXmlValidatorImpl
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.connector.business.mycarenet.attest.domain.InputReference
import org.taktik.connector.business.mycarenet.attestv3.mappers.BlobMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.CertificateParser
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.connector.technical.validator.XMLValidator
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.AgreementService
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import java.io.StringWriter
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

@Service
class AgreementServiceImpl(private val stsService: STSService, private val keyDepotService: KeyDepotService) : AgreementService {
    private val agreementServiceUtils = AgreementServiceUtils();
    private val freehealthAgreementService: org.taktik.connector.business.agreement.service.AgreementService =
        AgreementServiceImpl()

    private val agreementXmlValidator: XMLValidator = AgreementXmlValidatorImpl()
    private val config = ConfigFactory.getConfigValidator(emptyList())
    private var configValidator = ConfigFactory.getConfigValidator(listOf(
        "chapterIV.keydepot.application",
        "chapterIV.keydepot.identifiertype",
        "chapterIV.keydepot.identifiersubtype",
        "chapterIV.keydepot.identifiervalue"))

    enum class RequestTypeEnum {
        ASK,
        EXTEND,
        ARGUE,
        COMPLETE_AGREEMENT,
        CANCEL,
        CONSULT_LIST
    }

    private val log = LoggerFactory.getLogger(this.javaClass)

    private fun generateError(e: AgreementBusinessConnectorException, co: CommonOutput): AgreementResponse {
        val error = AgreementResponse()
        error.isAcknowledged = false
        error.errors = Arrays.asList(MycarenetError(code = e.errorCode, msgFr = e.message, msgNl = e.message))
        error.commonOutput = co
        return error
    }

    private fun generateError(e: SoaErrorException): org.taktik.connector.business.domain.agreement.AgreementResponse {
        val error = AgreementResponse()
        error.isAcknowledged = false
        error.errors = Arrays.asList(MycarenetError(code = e.errorCode, msgFr = e.message, msgNl = e.message))
        return error
    }

    override fun consultSynchronousAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        requestType: RequestTypeEnum,
        messageEventSystem: String,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        pathologyStartDate: DateTime,
        pathologyCode: String,
        insuranceRef: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        parameterNames: Array<String>?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?
    ): AgreementResponse? {
        val isTest = config.getProperty("endpoint.agreement").contains("-acpt")
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Agreement operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User
        val packageInfo = McnConfigUtil.retrievePackageInfo("eagreement", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)
        val inputReference = InputReference().inputReference

        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId()

        val instant = DateTime.now()
        val now = DateTime(0).withYear(instant.year).withMonthOfYear(instant.monthOfYear).withDayOfMonth(instant.dayOfMonth).withHourOfDay(instant.hourOfDay).withMinuteOfHour(instant.minuteOfHour).withSecondOfMinute(instant.secondOfMinute).withMillisOfSecond(0)
        val calendar = GregorianCalendar()
        calendar.time = now.toDate()
        val refDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar)
        refDateTime.timezone = DatatypeConstants.FIELD_UNDEFINED

        return extractEtk(credential)?.let {
            val synchronousAgreementRequest = createSynchronousAgreementRequest(
                requestType,
                messageEventSystem,
                messageEventCode,
                patientFirstName,
                patientLastName,
                patientGender,
                patientSsin,
                patientIo,
                patientIoMembership,
                pathologyStartDate,
                pathologyCode,
                insuranceRef,
                hcpNihii,
                hcpFirstName,
                hcpLastName,
                orgNihii,
                organizationType,
                annex1,
                annex2,
                parameterNames,
                agreementStartDate,
                agreementEndDate,
                agreementType,
                numberOfSessionForAnnex1,
                numberOfSessionForAnnex2
            )
            val kmehrMarshallHelper = MarshallerHelper(AskAgreementRequest::class.java, AskAgreementRequest::class.java)
            val requestXml = kmehrMarshallHelper.toXMLByteArray(synchronousAgreementRequest)

            val request = AskAgreementRequest().apply {
                val encryptedKnownContent = EncryptedKnownContent()
                encryptedKnownContent.replyToEtk = it.encoded
                val businessContent = BusinessContent().apply { id = detailId }
                encryptedKnownContent.businessContent = businessContent

                businessContent.value = requestXml
                log.info("Request is: " + businessContent.value.toString(Charsets.UTF_8))
                val xmlByteArray = handleEncryption(encryptedKnownContent, credential, crypto, detailId)

                val blob =
                    BlobBuilderFactory.getBlobBuilder("eagreement")
                        .build(
                            xmlByteArray,
                            "none",
                            detailId,
                            "text/xml",
                            null as String?,
                            "3.0",
                            "encryptedForKnownBED"
                        )
                blob.messageName = "E-AGREEMENT"

                commonInput = CommonInputType().apply {
                    request =
                        RequestType().apply {
                            isIsTest = config.getProperty("endpoint.genins")?.contains("-acpt") ?: false
                        }
                    origin = OriginType().apply {
                        `package` = PackageType().apply {
                            license = LicenseType().apply {
                                username = packageInfo.userName
                                password = packageInfo.password
                            }
                            name = ValueRefString().apply { value = packageInfo.packageName }
                        }
                        careProvider = CareProviderType().apply {
                            nihii =
                                NihiiType().apply {
                                    quality = "doctor"; value =
                                    ValueRefString().apply { value = hcpNihii }
                                }
                            physicalPerson = IdType().apply {
                                name = ValueRefString().apply { value = "$hcpFirstName $hcpLastName" }
                                ssin = ValueRefString().apply { value = hcpSsin }
                                nihii =
                                    NihiiType().apply {
                                        quality = "doctor"; value =
                                        ValueRefString().apply { value = hcpNihii }
                                    }
                            }
                        }
                    }
                    this.inputReference = inputReference
                }
                routing = RoutingType().apply {
                    careReceiver = CareReceiverIdType().apply {
                        ssin = patientSsin
                    }
                    this.referenceDate = refDateTime
                }
                this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
                this.issueInstant = DateTime()
                this.detail = BlobMapper.mapBlobTypefromBlob(blob)
            }

            val response = try {
                freehealthAgreementService.askAgreement(samlToken, request)
            } catch (e: SoaErrorException) {
                return generateError(e).apply {
                    val rt = e.responseType
                    if (rt is AskChap4MedicalAdvisorAgreementResponse) {
                        val co = rt.commonOutput
                        this.commonOutput = CommonOutput().apply {
                            this.inputReference = co?.inputReference ?: rt?.recordCommonOutput?.inputReference?.toString()
                            this.nipReference = co?.nipReference
                            this.outputReference = co?.outputReference ?: rt?.recordCommonOutput?.outputReference?.toString()
                        }
                        this.mycarenetConversation = MycarenetConversation().apply {
                            rt.soapRequest?.writeTo(this.soapRequestOutputStream())
                            rt.soapRequest?.writeTo(this.soapResponseOutputStream())
                        }
                        rt.returnInfo?.let { ri ->
                            this.errors = this.errors?.let { it + listOf(MycarenetError(code = ri.faultCode, path = ri.faultSource, msgFr = ri.message.value, msgNl = ri.message.value))}
                        }
                    }
                }
            }

            val commonOutput =
                CommonOutput(response.`return`.commonOutput?.inputReference, response.`return`.commonOutput.nipReference, response.`return`.commonOutput.outputReference)

            return AgreementResponse().apply {

            }
        }
    }

    fun createSynchronousAgreementRequest(
        requestType: RequestTypeEnum,
        messageEventSystem: String,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        pathologyStartDate: DateTime,
        pathologyCode: String,
        insuranceRef: String,
        hcpNihii: String,
        hcpFirstName: String,
        hcpLastName: String,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        parameterNames: Array<String>?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?
    ): AskAgreementRequest{

        val claim = agreementServiceUtils.getClaim(
            claimId = "1",
            claimStatus = "active",
            subTypeCode = "kine",
            agreementStartDate = DateTime(),
            insuranceRef = insuranceRef!!,
            pathologyCode = pathologyCode,
            pathologyStartDate = pathologyStartDate,
            providerType = ""
        )

        val bundle = agreementServiceUtils.getBundle(requestType, claim, messageEventSystem, messageEventCode, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, hcpNihii, hcpFirstName, hcpLastName, orgNihii, organizationType, annex1, annex2, parameterNames, agreementStartDate, agreementEndDate, agreementType, numberOfSessionForAnnex1, numberOfSessionForAnnex2)
        return AskAgreementRequest().apply {


        }
    }

    override fun requestAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String,
        patientSsin: String
    ): AgreementResponse? {
        val isTest = config.getProperty("endpoint.agreement").contains("-acpt")
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Agreement operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User
        val packageInfo = McnConfigUtil.retrievePackageInfo("genins", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)



        val request = AskAgreementRequest()

        val response = try {
            freehealthAgreementService.askAgreement(samlToken, request)
        } catch (e: SoaErrorException) {
            return generateError(e).apply {
                val rt = e.responseType
                if (rt is AskChap4MedicalAdvisorAgreementResponse) {
                    val co = rt.commonOutput
                    this.commonOutput = CommonOutput().apply {
                        this.inputReference = co?.inputReference ?: rt?.recordCommonOutput?.inputReference?.toString()
                        this.nipReference = co?.nipReference
                        this.outputReference = co?.outputReference ?: rt?.recordCommonOutput?.outputReference?.toString()
                    }
                    this.mycarenetConversation = MycarenetConversation().apply {
                        rt.soapRequest?.writeTo(this.soapRequestOutputStream())
                        rt.soapRequest?.writeTo(this.soapResponseOutputStream())
                    }
                    rt.returnInfo?.let { ri ->
                        this.errors = this.errors?.let { it + listOf(MycarenetError(code = ri.faultCode, path = ri.faultSource, msgFr = ri.message.value, msgNl = ri.message.value))}
                    }
                }
            }
        }

        val commonOutput =
            CommonOutput(response.`return`.commonOutput?.inputReference, response.`return`.commonOutput.nipReference, response.`return`.commonOutput.outputReference)

        return AgreementResponse().apply {

        }
    }

    private fun extractEtk(cred: KeyStoreCredential): EncryptionToken? {
        val parser = CertificateParser(cred.certificate)
        if (parser.identifier != null && !StringUtils.isEmpty(parser.id) && StringUtils.isNumeric(parser.id)) {
            try {
                return KeyDepotManagerImpl.getInstance(keyDepotService)
                    .getEtk(parser.identifier, java.lang.Long.parseLong(parser.id), parser.application, cred.keystoreId, false)
            } catch (ex: NumberFormatException) {
                log.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.message)
                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, ex)
            }
        } else {
            log.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.message)
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND)
        }
    }

    private fun handleEncryption(
        request: EncryptedKnownContent,
        credential: Credential,
        crypto: Crypto,
        detailId: String
    ): ByteArray? {
        val marshaller = JAXBContext.newInstance(request.javaClass).createMarshaller()
        val res = DOMResult()

        marshaller.marshal(request, res)

        val doc = res.node as Document

        val nodes = doc.getElementsByTagNameNS("urn:be:cin:encrypted", "EncryptedKnownContent")
        val content = toStringOmittingXmlDeclaration(nodes)
        val builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES)
        val options = HashMap<String, Any>()
        val tranforms = ArrayList<String>()
        tranforms.add("http://www.w3.org/2000/09/xmldsig#base64")
        tranforms.add("http://www.w3.org/2001/10/xml-exc-c14n#")
        options.put("transformerList", tranforms)
        options.put("baseURI", detailId)
        options.put("encapsulate", true)
        options.put("encapsulate-transformer", EncapsulationTransformer { signature ->
            val result = signature.ownerDocument.createElementNS("urn:be:cin:encrypted", "Xades")
            result.textContent = Base64.encodeBase64String(ConnectorXmlUtils.toByteArray(signature))
            result
        })
        val encryptedKnowContent = builder.sign(credential, content.toByteArray(charset("UTF-8")), options)
        return crypto.seal(
            Crypto.SigningPolicySelector.WITH_NON_REPUDIATION,
            KeyDepotManagerImpl.getInstance(keyDepotService).getEtkSet(
                IdentifierType.CBE,
                820563481L,
                "MYCARENET",
                null,
                false
            ),
            encryptedKnowContent
        )
    }

    @Throws(TransformerException::class)
    private fun toStringOmittingXmlDeclaration(nodes: NodeList): String {
        val sb = StringBuilder()
        val tf = TransformerFactory.newInstance()
        val serializer = tf.newTransformer()
        serializer.setOutputProperty("omit-xml-declaration", "yes")

        for (i in 0 until nodes.length) {
            val sw = StringWriter()
            serializer.transform(DOMSource(nodes.item(i)), StreamResult(sw))
            sb.append(sw.toString())
        }

        return sb.toString()
    }
}

