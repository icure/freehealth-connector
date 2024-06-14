package org.taktik.freehealth.middleware.service.impl

import be.cin.encrypted.BusinessContent
import be.cin.encrypted.EncryptedKnownContent
import be.cin.mycarenet.esb.common.v2.CommonInput
import be.cin.mycarenet.esb.common.v2.OrigineType
import be.cin.nip.async.generic.Confirm
import be.cin.nip.async.generic.GetResponse
import be.fgov.ehealth.agreement.protocol.v1.*
import be.fgov.ehealth.agreement.protocol.v1.ObjectFactory
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.mycarenet.commons.core.v3.*
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult
import be.fgov.ehealth.technicalconnector.signature.transformers.EncapsulationTransformer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.json.JSONObject
import org.json.XML
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.agreement.domain.Agreement
import org.taktik.connector.business.agreement.domain.AgreementMessage
import org.taktik.connector.business.agreement.exception.AgreementBusinessConnectorException
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.connector.business.genericasync.builders.BuilderFactory
import org.taktik.connector.business.genericasync.service.impl.GenAsyncServiceImpl
import org.taktik.connector.business.mycarenet.attest.domain.InputReference
import org.taktik.connector.business.mycarenetcommons.mapper.v3.BlobMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.business.mycarenetdomaincommons.util.PropertyUtil
import org.taktik.connector.business.mycarenetdomaincommons.util.WsAddressingUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.handler.domain.WsAddressingHeader
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.etee.domain.EncryptionToken
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.*
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EagreementService
import org.taktik.freehealth.middleware.service.STSService
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.IOException
import java.io.StringWriter
import java.net.URI
import java.net.URISyntaxException
import java.util.*
import java.util.function.Consumer
import javax.xml.bind.JAXBContext
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.ws.soap.SOAPFaultException


@Service
class EagreementServiceImpl(private val stsService: STSService, private val keyDepotService: KeyDepotService) : EagreementService {
    private val freehealthAgreementService: org.taktik.connector.business.agreement.service.AgreementService = org.taktik.connector.business.agreement.service.impl.AgreementServiceImpl()

    private val keyDepotManager = KeyDepotManagerImpl.getInstance(keyDepotService)
    private val config = ConfigFactory.getConfigValidator(emptyList())
    private val genAsyncService = GenAsyncServiceImpl("eagreement")

    val agreementServiceUtils: EagreementServiceUtilsImpl = EagreementServiceUtilsImpl();

    enum class RequestTypeEnum(val requestType: String) {
        ASK("claim-ask"),
        EXTEND("claim-extend"),
        ARGUE("claim-argue"),
        COMPLETE_AGREEMENT("claim-completeAgreement"),
        CANCEL("claim-cancel"),
        CONSULT_LIST("search-type")
    }

    enum class MessageEventSystemEnum(val eventSystem: String){
        MESSAGE_EVENTS("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/message-events"),
        INTERACTION("http://hl7.org/fhir/restful-interaction")
    }

    private val log = LoggerFactory.getLogger(this.javaClass)

    private fun generateError(e: AgreementBusinessConnectorException, co: CommonOutput): AgreementResponse {
        val error = AgreementResponse()
        error.isAcknowledged = false
        error.errors = Arrays.asList(MycarenetError(code = e.errorCode, msgFr = e.message, msgNl = e.message))
        error.commonOutput = co
        return error
    }

    private fun generateError(e: SoaErrorException): AgreementResponse {
        val error = AgreementResponse()
        error.isAcknowledged = false
        error.errors = Arrays.asList(MycarenetError(code = e.errorCode, msgFr = e.message, msgNl = e.message))
        return error
    }

    override fun getEAgreementMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        limit: Int
    ): List<AgreementMessage> {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for EAgreement operations")

        requireNotNull(keystoreId) { "Keystore id cannot be null" }
        requireNotNull(tokenId) { "Token id cannot be null" }

        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())

        val token = extractEtk(credential)

        val inputReference = "" + System.currentTimeMillis()
        val requestObjectBuilder = try {
            BuilderFactory.getRequestObjectBuilder("eagreement")
        } catch (e: Exception) {
            throw IllegalArgumentException(e)
        }

        val responseObjectBuilder = try {
            BuilderFactory.getResponseObjectBuilder()
        } catch (e: Exception) {
            throw IllegalArgumentException(e)
        }

        val ci = CommonInput().apply {
            request = be.cin.mycarenet.esb.common.v2.RequestType().apply {
                isIsTest = config.getProperty("endpoint.eagreement")?.contains("-acpt") ?: false
            }
            origin = buildOriginType(samlToken.quality, hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
            this.inputReference = inputReference
        }

        val header = try {
            WsAddressingHeader(URI("urn:be:cin:nip:async:generic:get:query")).apply {
                faultTo = "http://www.w3.org/2005/08/addressing/anonymous"
                replyTo = "http://www.w3.org/2005/08/addressing/anonymous"
                messageID = URI("" + UUID.randomUUID())
            }
        } catch (e: URISyntaxException) {
            throw IllegalStateException(e)
        }

        var batchSize = Math.min(64, limit)
        var retries = 8

        val agreementMessages = ArrayList<AgreementMessage>()

        while (retries-- > 0) {
            val msgQuery = requestObjectBuilder.createMsgQuery(batchSize, true, "eAgreement-response")
            val query = requestObjectBuilder.createQuery(batchSize, true)

            val getResponse: GetResponse
            try {
                getResponse =
                    genAsyncService.getRequest(samlToken, requestObjectBuilder.buildGetRequest(ci.origin, msgQuery, query, token?.encoded), header)
            } catch (e: TechnicalConnectorException) {
                if ((e.message?.contains("SocketTimeout") == true) && batchSize > 1) {
                    batchSize /= 4
                    continue
                }
                throw IllegalStateException(e)
            } catch (e: SOAPFaultException) {
                if (e.message?.contains("Not enough time") == true) {
                    Thread.sleep(30000)
                    continue
                }
                throw IllegalStateException(e)
            }

            val processedGetResponse = responseObjectBuilder.processResponse(getResponse, ByteArray::class.java , "eagreement", credential, hokPrivateKeys)

            agreementMessages += processedGetResponse.msgResponses.map { r ->
                val msgResponse = r.msgResponse;
                AgreementMessage().apply {
                    id = msgResponse.detail.id
                    name = msgResponse.detail.messageName

                    commonOutput = CommonOutput().apply {
                        this.inputReference = msgResponse.commonOutput.inputReference
                        this.nipReference = msgResponse.commonOutput.nipReference
                        this.outputReference = msgResponse.commonOutput.outputReference
                    }
                    try {
                        detail = r.businessResponse as ByteArray?

                        xades = r.rawDecryptedBlob.xades
                        reference = msgResponse.detail.reference
                    } catch (e: IOException) {
                    }
                }
            }

            break
        }
        return agreementMessages
    }

    override fun confirmMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        references: List<String>
    ): Boolean {
        if (references.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for EAgreement operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm = Confirm();
        confirm.origin = buildOriginType(samlToken.quality, hcpNihii, hcpSsin, hcpFirstName, hcpLastName)
        confirm.msgRefValues.addAll(references)

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }

    private fun buildOriginType(quality: String, nihii: String, ssin: String, firstName: String, lastName: String): OrigineType =
        OrigineType().apply {
            val principal = SecurityContextHolder.getContext().authentication?.principal as? User
            val packageInfo = McnConfigUtil.retrievePackageInfo("eagreement", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)

            `package` = be.cin.mycarenet.esb.common.v2.PackageType().apply {
                name = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = packageInfo.packageName }
                license = be.cin.mycarenet.esb.common.v2.LicenseType().apply {
                    this.username = packageInfo.userName
                    this.password = packageInfo.password
                }
            }
            careProvider = be.cin.mycarenet.esb.common.v2.CareProviderType().apply {
                if (quality == "guardpost" || quality == "medicalhouse") {
                    this.nihii = be.cin.mycarenet.esb.common.v2.NihiiType().apply {
                        this.quality = quality
                        this.value = be.cin.mycarenet.esb.common.v2.ValueRefString()
                            .apply { value = nihii.padEnd(11, '0') }
                    }
                    this.organization = be.cin.mycarenet.esb.common.v2.IdType().apply {
                        this.nihii = be.cin.mycarenet.esb.common.v2.NihiiType()
                            .apply { value = be.cin.mycarenet.esb.common.v2.ValueRefString()
                                .apply { value = nihii.padEnd(11, '0') } }
                        this.ssin = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = ssin }
                        this.name = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = "$firstName $lastName" }
                    }
                } else {
                    this.nihii = be.cin.mycarenet.esb.common.v2.NihiiType().apply {
                        this.quality = quality
                        this.value = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = nihii }
                    }
                    this.physicalPerson = be.cin.mycarenet.esb.common.v2.IdType().apply {
                        this.nihii = be.cin.mycarenet.esb.common.v2.NihiiType()
                            .apply { value = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = nihii } }
                        this.ssin = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = ssin }
                        this.name = be.cin.mycarenet.esb.common.v2.ValueRefString().apply { value = "$firstName $lastName" }
                    }
                }
            }
        }

    override fun askAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        requestType: RequestTypeEnum,
        hcpQuality: String,
        messageEventSystem: MessageEventSystemEnum,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        pathologyStartDate: DateTime?,
        pathologyCode: String?,
        insuranceRef: String?,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?,
        sctCode: String?,
        sctDisplay: String?
    ): AgreementResponse?
    {
        val requestBundleJSON = createRequestBundle(
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
            agreementStartDate,
            agreementEndDate,
            agreementType,
            numberOfSessionForAnnex1,
            numberOfSessionForAnnex2,
            sctCode,
            sctDisplay
        )

        val xmlString = getXmlBytesOfRequestBundle(requestBundleJSON)

        val agreement = Agreement();
        agreement.patientSsin = patientSsin
        agreement.patientIo = patientIo
        agreement.hcpQuality = hcpQuality
        agreement.hcpNihii = hcpNihii
        agreement.hcpSsin = hcpSsin
        agreement.hcpFirstName = hcpFirstName
        agreement.hcpLastName = hcpLastName
        agreement.agreementXmlString = xmlString

        return askAgreement(keystoreId, tokenId, passPhrase, agreement)
    }

    override fun askAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        agreement: Agreement
    ): AgreementResponse? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Agreement operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)
        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId()

        return extractEtk(credential)?.let {
            var askAgreementRequest = AskAgreementRequest();
            askAgreementRequest.apply {
                val encryptedKnownContent = EncryptedKnownContent()
                encryptedKnownContent.replyToEtk = it.encoded
                val businessContent = BusinessContent().apply { id = detailId }
                encryptedKnownContent.businessContent = businessContent

                val byteArray = agreement.agreementXmlString!!.toByteArray(Charsets.UTF_8)

                businessContent.value = byteArray

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
                blob.messageName = "eAgreement-ask"

                val principal = SecurityContextHolder.getContext().authentication?.principal as? User
                val packageInfo = McnConfigUtil.retrievePackageInfo("eagreement", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)

                commonInput = CommonInputType().apply {
                    request =
                        RequestType()
                            .apply {
                                isIsTest = config.getProperty("endpoint.eagreement")?.contains("-acpt") ?: false
                            }
                    inputReference = InputReference().inputReference
                    origin = OriginType().apply {
                        `package` = PackageType().apply {
                            license = LicenseType().apply {
                                username = packageInfo.userName
                                password = packageInfo.password
                            }
                            name = ValueRefString().apply { value = packageInfo.packageName }
                        }
                        config.getProperty("mycarenet.${PropertyUtil.retrieveProjectNameToUse("eagreement", "mycarenet.")}.site.id")?.let {
                            if (it.isNotBlank()) {
                                siteID = ValueRefString().apply { value = it }
                            }
                        }
                        careProvider = CareProviderType().apply {
                            nihii =
                                NihiiType().apply {
                                    quality = agreement.hcpQuality!!;
                                    value = ValueRefString().apply { value = agreement.hcpNihii!! }
                                }
                            physicalPerson = IdType().apply {
                                name = ValueRefString().apply { value = "${agreement.hcpFirstName!!} ${agreement.hcpLastName!!}" }
                                ssin = ValueRefString().apply { value = agreement.hcpSsin!! }
                                nihii = NihiiType().apply {
                                    quality = agreement.hcpQuality!!;
                                    value = ValueRefString().apply { value = agreement.hcpNihii!! }
                                }
                            }
                        }
                    }
                }
                routing = RoutingType().apply {
                    careReceiver = CareReceiverIdType().apply {
                        agreement.patientSsin?.let {
                            ssin = agreement.patientSsin
                        }
                        agreement.patientIo?.let {
                            mutuality = agreement.patientIo
                        }
                    }
                    referenceDate = DateTime()
                }
                issueInstant = DateTime()
                this.detail = BlobMapper.mapBlobTypefromBlob(blob)
                this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
                // xades = BlobUtil.generateXades(credential, detail, "eagreement")
            }

            try {
                val agreementResponse: AskAgreementResponse? =freehealthAgreementService.askAgreement(samlToken, ObjectFactory().createAskAgreementRequest(askAgreementRequest).value)

                val blobType = agreementResponse?.`return`?.detail
                val blob = BlobMapper.mapBlobfromBlobType(blobType!!)
                val unsealedData =
                    crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, blob.content).contentAsByte
                val decryptedKnownContent =
                    MarshallerHelper(EncryptedKnownContent::class.java, EncryptedKnownContent::class.java).toObject(
                        unsealedData)

                val xades = decryptedKnownContent!!.xades
                val signatureVerificationResult = xades?.let {
                    val builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES)
                    val options = emptyMap<String, Any>()
                    builder.verify(unsealedData, it, options)
                } ?: SignatureVerificationResult().apply {
                    errors.add(SignatureVerificationError.SIGNATURE_NOT_PRESENT)
                }

                log.info("Response is: " + decryptedKnownContent.businessContent.value.toString(Charsets.UTF_8))

                val responseXML = decryptedKnownContent.businessContent.value.toString(Charsets.UTF_8)
                val responseJSON = XML.toJSONObject(responseXML)

                // val errors = responseJSON.getJSONObject("Bundle").getJSONArray("entry")

                var commonOutput =
                    CommonOutput(
                        agreementResponse?.`return`?.commonOutput?.inputReference,
                        agreementResponse?.`return`?.commonOutput?.nipReference,
                        agreementResponse?.`return`?.commonOutput?.outputReference
                    )

                var res = AgreementResponse()
                res.isAcknowledged = true
                res.commonOutput = commonOutput
                res.mycarenetConversation = MycarenetConversation().apply {
                    soapRequest = agreementResponse.soapRequest?.writeTo(this.soapRequestOutputStream())?.toString()
                    soapResponse = agreementResponse.soapResponse?.writeTo(this.soapResponseOutputStream())?.toString()
                    transactionRequest = agreement.agreementXmlString
                    transactionResponse = responseXML
                }
                res.content = responseXML.toByteArray(Charsets.UTF_8)
                res.xades = xades;
                // TODO call that method but it's not fully implemented yest
                // res.errors = extractErrors(responseJSON).toList()
                return res;
            } catch (e: SoaErrorException) {
                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e, e.message)
            }
        }
    }

    override fun consultAgreementList(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        requestType: RequestTypeEnum,
        hcpQuality: String,
        messageEventSystem: MessageEventSystemEnum,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        insuranceRef: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        subTypeCode: String,
        orgNihii: String?,
        organizationType: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?
    ): AgreementResponse? {
        val requestBundleJSON = createConsultAgreementBundle(
            requestType,
            messageEventSystem,
            messageEventCode,
            patientFirstName,
            patientLastName,
            patientGender,
            patientSsin,
            patientIo,
            patientIoMembership,
            insuranceRef,
            hcpNihii,
            hcpFirstName,
            hcpLastName,
            subTypeCode,
            orgNihii,
            organizationType,
            agreementStartDate,
            agreementEndDate,
            agreementType
        )

        val xmlString = getXmlBytesOfRequestBundle(requestBundleJSON)

        val agreement = Agreement();
        agreement.patientSsin = patientSsin
        agreement.patientIo = patientIo
        agreement.hcpQuality = hcpQuality
        agreement.hcpNihii = hcpNihii
        agreement.hcpSsin = hcpSsin
        agreement.hcpFirstName = hcpFirstName
        agreement.hcpLastName = hcpLastName
        agreement.agreementXmlString = xmlString

        return consultAgreementList(keystoreId, tokenId, passPhrase, agreement)
    }

    override fun consultAgreementList(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        agreement: Agreement
    ): AgreementResponse? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Agreement operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)
        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId()

        val byteArray = agreement.agreementXmlString!!.toByteArray(Charsets.UTF_8)

        return extractEtk(credential)?.let {
            var consultAgreementList = ConsultAgreementRequest()
            consultAgreementList.apply {
                val encryptedKnownContent = EncryptedKnownContent()
                encryptedKnownContent.replyToEtk = it.encoded
                val businessContent = BusinessContent().apply { id = detailId }
                encryptedKnownContent.businessContent = businessContent

                businessContent.value = byteArray

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
                blob.messageName = "eAgreement-ask"

                val principal = SecurityContextHolder.getContext().authentication?.principal as? User
                val packageInfo = McnConfigUtil.retrievePackageInfo("eagreement", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)

                commonInput = CommonInputType().apply {
                    request =
                        RequestType()
                            .apply {
                                isIsTest = config.getProperty("endpoint.eagreement")?.contains("-acpt") ?: false
                            }
                    inputReference = InputReference().inputReference
                    origin = OriginType().apply {
                        `package` = PackageType().apply {
                            license = LicenseType().apply {
                                username = packageInfo.userName
                                password = packageInfo.password
                            }
                            name = ValueRefString().apply { value = packageInfo.packageName }
                        }
                        config.getProperty("mycarenet.${PropertyUtil.retrieveProjectNameToUse("eagreement", "mycarenet.")}.site.id")?.let {
                            if (it.isNotBlank()) {
                                siteID = ValueRefString().apply { value = it }
                            }
                        }
                        careProvider = CareProviderType().apply {
                            nihii =
                                NihiiType().apply {
                                    quality = agreement.hcpQuality!!;
                                    value = ValueRefString().apply { value = agreement.hcpNihii!! }
                                }
                            physicalPerson = IdType().apply {
                                name = ValueRefString().apply { value = "${agreement.hcpFirstName!!} ${agreement.hcpLastName!!}" }
                                ssin = ValueRefString().apply { value = agreement.hcpSsin!! }
                                nihii = NihiiType().apply {
                                    quality = agreement.hcpQuality!!;
                                    value = ValueRefString().apply { value = agreement.hcpNihii!! }
                                }
                            }
                        }
                    }
                }
                routing = RoutingType().apply {
                    careReceiver = CareReceiverIdType().apply {
                        agreement.patientSsin?.let {
                            ssin = agreement.patientSsin
                        }
                        agreement.patientIo?.let {
                            mutuality = agreement.patientIo
                        }
                    }
                    referenceDate = DateTime()
                }
                issueInstant = DateTime()
                this.detail = BlobMapper.mapBlobTypefromBlob(blob)
                this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
                //xades = BlobUtil.generateXades(credential, detail, "eagreement")
            }

            try {
                val consultAgreementResponse: ConsultAgreementResponse? = freehealthAgreementService.consultAgreement(samlToken, ObjectFactory().createConsultAgreementRequest(consultAgreementList).value)

                val blobType = consultAgreementResponse?.`return`?.detail
                val blob = BlobMapper.mapBlobfromBlobType(blobType!!)
                val unsealedData =
                    crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, blob.content).contentAsByte
                val decryptedKnownContent =
                    MarshallerHelper(EncryptedKnownContent::class.java, EncryptedKnownContent::class.java).toObject(
                        unsealedData)

                val xades = decryptedKnownContent!!.xades
                val signatureVerificationResult = xades?.let {
                    val builder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES)
                    val options = emptyMap<String, Any>()
                    builder.verify(unsealedData, it, options)
                } ?: SignatureVerificationResult().apply {
                    errors.add(SignatureVerificationError.SIGNATURE_NOT_PRESENT)
                }

                log.info("Response is: " + decryptedKnownContent.businessContent.value.toString(Charsets.UTF_8))

                val responseXML = decryptedKnownContent.businessContent.value.toString(Charsets.UTF_8)
                val responseJSON = XML.toJSONObject(responseXML)

                // val errors = responseJSON.getJSONObject("Bundle").getJSONArray("entry")

                var commonOutput =
                    CommonOutput(
                        consultAgreementResponse?.`return`?.commonOutput?.inputReference,
                        consultAgreementResponse?.`return`?.commonOutput?.nipReference,
                        consultAgreementResponse?.`return`?.commonOutput?.outputReference
                    )

                var res = AgreementResponse()
                res.isAcknowledged = true
                res.commonOutput = commonOutput
                res.mycarenetConversation = MycarenetConversation().apply {
                    soapRequest = consultAgreementResponse.soapRequest?.writeTo(this.soapRequestOutputStream())?.toString()
                    soapResponse = consultAgreementResponse.soapResponse?.writeTo(this.soapResponseOutputStream())?.toString()
                    transactionRequest = agreement.agreementXmlString
                    transactionResponse = responseXML
                }
                res.content = responseXML.toByteArray(Charsets.UTF_8)
                res.xades = xades

                // TODO call that method but it's not fully implemented yest
                // res.errors = extractErrors(responseJSON).toList()
                return res;
            } catch (e: SoaErrorException) {
                throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e, e.message)
            }

        }
    }

    private fun getXmlBytesOfRequestBundle(requestBundleJSON: JsonObject?): String {
        val xmlString = convertJsonObjectToXml(requestBundleJSON!!)
        val requestXml = transformXml(xmlString)

        return requestXml
    }

    fun transformElement(element: Element, doc: Document) {
        val nodeList = element.childNodes
        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            if (node is Element) {
                transformElement(node, doc)
            }
        }
        if (element.childNodes.length == 1 && element.firstChild.nodeType == Node.TEXT_NODE) {
            val textContent = element.textContent
            element.textContent = "" // Clear the current text content
            element.setAttribute("value", textContent)
        }
    }


    // TODO check if this is needed. This transform request the same way we receive response : <xmlTag value="hello"/> instead of <xmlTag>hello</xmlTag>
    fun transformXml(inputXml: String): String {
        // Parse the XML
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val originalDoc = dBuilder.parse(inputXml.byteInputStream())

        // Transform the document
        val rootElement = originalDoc.documentElement
        rootElement.setAttribute("xmlns", "http://hl7.org/fhir")
        transformElement(rootElement, originalDoc)

        // Convert the document back to a string
        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        val domSource = DOMSource(originalDoc)
        val writer = StringWriter()
        val result = StreamResult(writer)
        transformer.transform(domSource, result)

        return writer.toString()
    }

    fun convertJsonObjectToXml(jsonObject: JsonObject): String {
        val xmlBuilder = java.lang.StringBuilder()
        // Ajout de l'en-tête XML (optionnel)
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
        // Convertir le JsonObject en XML
        xmlBuilder.append(convertElement(jsonObject.get("Bundle").asJsonObject, "Bundle"))
        return xmlBuilder.toString()
    }

    private fun convertElement(jsonElement: JsonElement, elementName: String): String? {
        // Cas de base : si l'élément est une primitive ou null, retourner sa représentation en chaîne
        if (jsonElement.isJsonPrimitive || jsonElement.isJsonNull) {
            return String.format("<%s>%s</%s>", elementName, jsonElement.asString, elementName)
        } else if (jsonElement.isJsonObject) {
            val elementBuilder = java.lang.StringBuilder()
            elementBuilder.append(String.format("<%s>", elementName))
            val jsonObject = jsonElement.asJsonObject
            jsonObject.entrySet().forEach(Consumer { (key, value): Map.Entry<String, JsonElement> ->
                // Récursion pour chaque élément du JsonObject
                elementBuilder.append(convertElement(value, key))
            })
            elementBuilder.append(String.format("</%s>", elementName))
            return elementBuilder.toString()
        } else if (jsonElement.isJsonArray) {
            // Gérer les tableaux JSON. Notez que cela ne définit pas de balises spécifiques pour les éléments de tableau
            val arrayBuilder = java.lang.StringBuilder()
            jsonElement.asJsonArray.forEach(Consumer { item: JsonElement ->
                arrayBuilder.append(
                    convertElement(item, elementName)
                )
            })
            return arrayBuilder.toString()
        }
        // Retourner une chaîne vide pour tout autre cas (ce qui ne devrait normalement pas arriver)
        return ""
    }


    fun createRequestBundle(
        requestType: RequestTypeEnum,
        messageEventSystem: MessageEventSystemEnum,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        pathologyStartDate: DateTime?,
        pathologyCode: String?,
        insuranceRef: String?,
        hcpNihii: String,
        hcpFirstName: String,
        hcpLastName: String,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?,
        sctCode: String?,
        sctDisplay: String?
    ): JsonObject?{
        when (requestType) {
            RequestTypeEnum.ASK -> return return this.agreementServiceUtils.getBundleJSON(requestType, "Claim/Claim1", messageEventSystem, messageEventCode, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, hcpNihii, hcpFirstName, hcpLastName, "14375992004", "Robin", "Hormaux", orgNihii, organizationType, annex1, annex2, agreementStartDate, agreementEndDate, agreementType, numberOfSessionForAnnex1, numberOfSessionForAnnex2, insuranceRef, pathologyCode, pathologyStartDate, sctCode, sctDisplay, null) ?: throw IllegalArgumentException("Cannot load fhir")
            RequestTypeEnum.ARGUE -> return return this.agreementServiceUtils.getBundleJSON(requestType, "Claim/Claim1", messageEventSystem, messageEventCode, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, hcpNihii, hcpFirstName, hcpLastName, "14375992004", "Robin", "Hormaux", orgNihii, organizationType, annex1, annex2, agreementStartDate, agreementEndDate, agreementType, numberOfSessionForAnnex1, numberOfSessionForAnnex2, insuranceRef, pathologyCode, pathologyStartDate, sctCode, sctDisplay, null) ?: throw IllegalArgumentException("Cannot load fhir")
            RequestTypeEnum.CANCEL -> return return this.agreementServiceUtils.getBundleJSON(requestType, "Claim/Claim1", messageEventSystem, messageEventCode, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, hcpNihii, hcpFirstName, hcpLastName, "14375992004", "Robin", "Hormaux", orgNihii, organizationType, annex1, annex2, agreementStartDate, agreementEndDate, agreementType, numberOfSessionForAnnex1, numberOfSessionForAnnex2, insuranceRef, pathologyCode, pathologyStartDate, sctCode, sctDisplay, null) ?: throw IllegalArgumentException("Cannot load fhir")
            RequestTypeEnum.COMPLETE_AGREEMENT -> return return this.agreementServiceUtils.getBundleJSON(requestType, "Claim/Claim1", messageEventSystem, messageEventCode, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, hcpNihii, hcpFirstName, hcpLastName, "14375992004", "Robin", "Hormaux", orgNihii, organizationType, annex1, annex2, agreementStartDate, agreementEndDate, agreementType, numberOfSessionForAnnex1, numberOfSessionForAnnex2, insuranceRef, pathologyCode, pathologyStartDate, sctCode, sctDisplay, null) ?: throw IllegalArgumentException("Cannot load fhir")
            RequestTypeEnum.EXTEND -> return this.agreementServiceUtils.getBundleJSON(requestType, "Claim/Claim1", messageEventSystem, messageEventCode, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, hcpNihii, hcpFirstName, hcpLastName, "14375992004", "Robin", "Hormaux", orgNihii, organizationType, annex1, annex2, agreementStartDate, agreementEndDate, agreementType, numberOfSessionForAnnex1, numberOfSessionForAnnex2, insuranceRef, pathologyCode, pathologyStartDate, sctCode, sctDisplay, null) ?: throw IllegalArgumentException("Cannot load fhir")
            else -> throw IllegalArgumentException("Request type not supported")
        }
    }

    fun createConsultAgreementBundle(
        requestType: RequestTypeEnum,
        messageEventSystem: MessageEventSystemEnum,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        insuranceRef: String,
        hcpNihii: String,
        hcpFirstName: String,
        hcpLastName: String,
        subTypeCode: String,
        orgNihii: String?,
        organizationType: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?
    ): JsonObject?{
        return this.agreementServiceUtils.getBundleJSON(requestType, "Parameters/Parameters1", messageEventSystem, messageEventCode, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, hcpNihii, hcpFirstName, hcpLastName, null, null, null, orgNihii, organizationType, null, null, agreementStartDate, agreementEndDate, agreementType, null, null, insuranceRef, null, null, null, null, subTypeCode) ?: throw IllegalArgumentException("Cannot load fhir")
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

    private fun extractErrors(jsonObject: JSONObject): Set<MycarenetError> {
        val errors = mutableSetOf<MycarenetError>()
        val entries = jsonObject.getJSONObject("Bundle").getJSONArray("entry")

        for (i in 0 until entries.length()) {
            val entry = entries.getJSONObject(i)
            val resource = entry.getJSONObject("resource")

            if (resource.has("OperationOutcome")) {
                val operationOutcome = resource.getJSONObject("OperationOutcome")
                if (operationOutcome.has("issue")) {
                    val issue = operationOutcome.getJSONObject("issue")
                    if (issue.has("severity") && issue.getJSONObject("severity").getString("value") == "error") {
                        val errorCode = issue.getJSONObject("details").getJSONObject("coding").getJSONObject("code").getString("value")
                        errors.add(MycarenetError(code = errorCode))
                    }
                }
            }
        }

        return errors
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

