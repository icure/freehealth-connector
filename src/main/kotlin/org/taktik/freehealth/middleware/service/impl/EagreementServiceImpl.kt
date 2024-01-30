package org.taktik.freehealth.middleware.service.impl

import be.apb.gfddpp.common.utils.Utils
import be.cin.encrypted.BusinessContent
import be.cin.encrypted.EncryptedKnownContent
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.messageservices.mycarenet.core.v1.SendTransactionRequest
import be.fgov.ehealth.mycarenet.commons.core.v4.CareProviderType
import be.fgov.ehealth.mycarenet.commons.core.v4.LicenseType
import be.fgov.ehealth.mycarenet.commons.core.v3.NihiiType
import be.fgov.ehealth.mycarenet.commons.core.v4.OriginType
import be.fgov.ehealth.mycarenet.commons.core.v4.PackageType
import be.fgov.ehealth.mycarenet.commons.core.v4.ValueRefString
import be.fgov.ehealth.mycarenet.commons.core.v4.CareReceiverIdType
import be.fgov.ehealth.mycarenet.commons.core.v4.RoutingType
import be.fgov.ehealth.mycarenet.commons.core.v4.*
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory
import be.fgov.ehealth.technicalconnector.signature.transformers.EncapsulationTransformer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.agreement.exception.AgreementBusinessConnectorException
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.connector.business.mycarenet.attest.mappers.BlobMapper
import org.taktik.connector.business.mycarenetcommons.builders.util.BlobUtil
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
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EagreementService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.icure.fhir.entities.r4.bundle.Bundle
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import java.io.StringWriter
import java.time.LocalDateTime
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.datatype.DatatypeFactory
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMResult
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

@Service
class EagreementServiceImpl(private val stsService: STSService, private val keyDepotService: KeyDepotService) : EagreementService {
    private val freehealthAgreementService: org.taktik.connector.business.agreement.service.AgreementService = org.taktik.connector.business.agreement.service.impl.AgreementServiceImpl()

    private val keyDepotManager = KeyDepotManagerImpl.getInstance(keyDepotService)
    private val config = ConfigFactory.getConfigValidator(emptyList())
    private var configValidator = ConfigFactory.getConfigValidator(listOf(
        "chapterIV.keydepot.application",
        "chapterIV.keydepot.identifiertype",
        "chapterIV.keydepot.identifiersubtype",
        "chapterIV.keydepot.identifiervalue"))

    val agreementServiceUtils: EagreementServiceUtilsImpl = EagreementServiceUtilsImpl();

    enum class RequestTypeEnum(val requestType: String) {
        ASK("claim-ask"),
        EXTEND("claim-extend"),
        ARGUE("claim-argue"),
        COMPLETE_AGREEMENT("claim-completeAgreement"),
        CANCEL("claim-cancel"),
        CONSULT_LIST("")
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


    override fun askAgreement(
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
        val isTest = config.getProperty("endpoint.mcn.agreement").contains("-acpt")
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Agreement operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User
        val packageInfo = McnConfigUtil.retrievePackageInfo("genins", principal?.mcnLicense, principal?.mcnPassword, principal?.mcnPackageName)

        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val blobBuilder = BlobBuilderFactory.getBlobBuilder("agreement")

        val now = DateTime.now().withMillisOfSecond(0)
        val xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(now.toGregorianCalendar())
        return extractEtk(credential)?.let {
            val requestBundle = createRequestBundle(
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

            val askAgreementRequest = AskAgreementRequest().apply {
                val requestJson = ObjectMapper().registerModule(KotlinModule()).writeValueAsString(requestBundle)
                val unEncryptedQuery = ConnectorXmlUtils.toByteArray(requestJson as Any)

                this.commonInput = CommonInputType().apply {
                    request =
                        be.fgov.ehealth.mycarenet.commons.core.v4.RequestType()
                            .apply {
                                isIsTest = config.getProperty("endpoint.mcn.agreement")?.contains("-acpt") ?: false
                            }
                    this.inputReference = inputReference
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
                                be.fgov.ehealth.mycarenet.commons.core.v4.NihiiType().apply {
                                    quality = "physiotherapist";
                                    value = ValueRefString().apply { value = hcpNihii }
                                }
                            physicalPerson = IdType().apply {
                                name = ValueRefString().apply { value = "$hcpFirstName $hcpLastName" }
                                ssin = ValueRefString().apply { value = hcpSsin }
                                nihii = be.fgov.ehealth.mycarenet.commons.core.v4.NihiiType().apply {
                                    quality = "physiotherapist";
                                    value = ValueRefString().apply { value = hcpNihii }
                                }
                            }
                        }
                    }
                }
                this.routing = RoutingType().apply {
                    careReceiver = CareReceiverIdType().apply {
                        patientSsin?.let {
                            ssin = patientSsin
                        }
                        patientIoMembership?.let {
                            mutuality = patientIo
                            regNrWithMut = patientIoMembership
                        }
                    }
                    this.referenceDate = xmlGregorianCalendar
                }

                val encryptedQuery = unEncryptedQuery.let { aqb ->
                    val identifierTypeString = "CBE"
                    val identifierValue = 820563481L
                    val applicationId = "MYCARENET"
                    val identifierSource = 48
                    val identifier = IdentifierType.lookup(identifierTypeString, null as String?, identifierSource)

                    val mbEtk = if (identifier == null) {
                        throw IllegalStateException("invalid configuration : identifier with type ]$identifierTypeString[ for source ETKDEPOT not found")
                    } else {
                        keyDepotManager.getEtkSet(IdentifierType.CBE, identifierValue, applicationId, keystoreId, false)
                    }

                    crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, mbEtk, ConnectorXmlUtils.toByteArray(
                        EncryptedKnownContent().apply {
                            replyToEtk = keyDepotManager.getETK(credential, keystoreId).encoded
                            businessContent = BusinessContent().apply {
                                id = detailId
                                value = aqb
                                contentType = "text/xml"
                            }
                        })).let {
                        org.taktik.connector.business.mycarenetcommons.mapper.v4.BlobMapper.mapBlobTypefromBlob(blobBuilder.build(it, "none", detailId, "text/xml", "eAgreement-ask", "encryptedForKnownBED"))
                    }
                }

                this.detail = encryptedQuery

                this.xades //= BlobUtil.generateXades(credential, this.detail, "eAgreement-response")
            }



            val askAgreementResponse = try {
                freehealthAgreementService.askAgreement(samlToken, askAgreementRequest)

            } catch (e: SoaErrorException) {
                return generateError(e).apply {
                    val rt = e.responseType
                    if (rt is AskAgreementResponse) {
                        // do something
                    }
                }
            }

            val commonOutput =
                CommonOutput(
                    askAgreementResponse?.`return`?.commonOutput?.inputReference,
                    askAgreementResponse?.`return`?.commonOutput?.nipReference,
                    askAgreementResponse?.`return`?.commonOutput?.outputReference
                )

            return AgreementResponse().apply {
                isAcknowledged = true
            }

        }
    }

    override fun consultAgreement(keystoreId: UUID, tokenId: UUID, passPhrase: String): ConsultAgreementResponse? {
        TODO("Not yet implemented")
    }

    fun createRequestBundle(
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
    ): Bundle?{

        val claim = this.agreementServiceUtils.getClaim(
            claimId = "1",
            claimStatus = "active",
            subTypeCode = "kine",
            agreementStartDate = DateTime(),
            insuranceRef = insuranceRef!!,
            pathologyCode = pathologyCode,
            pathologyStartDate = pathologyStartDate,
            providerType = ""
        )

        val bundle = this.agreementServiceUtils.getBundle(requestType, claim, messageEventSystem, messageEventCode, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, hcpNihii, hcpFirstName, hcpLastName, orgNihii, organizationType, annex1, annex2, parameterNames, agreementStartDate, agreementEndDate, agreementType, numberOfSessionForAnnex1, numberOfSessionForAnnex2) ?: throw IllegalArgumentException("Cannot load fhir")
        return bundle
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

