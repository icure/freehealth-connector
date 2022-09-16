package org.taktik.freehealth.middleware.service.impl

import be.cin.encrypted.BusinessContent
import be.cin.encrypted.EncryptedKnownContent
import be.cin.mycarenet.esb.common.v2.CareProviderType
import be.cin.mycarenet.esb.common.v2.CommonInput
import be.cin.mycarenet.esb.common.v2.IdType
import be.cin.mycarenet.esb.common.v2.LicenseType
import be.cin.mycarenet.esb.common.v2.NihiiType
import be.cin.mycarenet.esb.common.v2.OrigineType
import be.cin.mycarenet.esb.common.v2.PackageType
import be.cin.mycarenet.esb.common.v2.ValueRefString
import be.cin.nip.async.generic.GetResponse
import be.cin.nip.async.generic.Post
import be.cin.nip.async.generic.PostResponse
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import ma.glasnost.orika.MapperFacade
import org.apache.commons.codec.binary.Base64
import org.apache.commons.io.IOUtils
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.common.GenAsyncResponse
import org.taktik.connector.business.genericasync.builders.BuilderFactory
import org.taktik.connector.business.genericasync.service.impl.GenAsyncServiceImpl
import org.taktik.connector.business.medadminurses.domain.MedAdminRequestListType
import org.taktik.connector.business.mycarenetcommons.builders.util.BlobUtil
import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.domain.McnPackageInfo
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil
import org.taktik.connector.business.mycarenetdomaincommons.util.WsAddressingUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.handler.domain.WsAddressingHeader
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.ConnectorIOUtils
import org.taktik.connector.technical.utils.ConnectorXmlUtils
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.efact.EfactMessage
import org.taktik.freehealth.middleware.dto.efact.EfactSendResponse
import org.taktik.freehealth.middleware.dto.efact.ErrorDetail
import org.taktik.freehealth.middleware.dto.efact.Record
import org.taktik.freehealth.middleware.dto.efact.Zone
import org.taktik.freehealth.middleware.dto.efact.segments.RecordOrSegmentDescription
import org.taktik.freehealth.middleware.dto.efact.segments.ZoneDescription
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.format.efact.BelgianInsuranceInvoicingFormatReader
import org.taktik.freehealth.middleware.service.MemberDataService
import org.taktik.freehealth.middleware.service.STSService
import java.io.IOException
import java.io.StringReader
import java.net.URI
import java.net.URISyntaxException
import java.util.*
import javax.xml.ws.soap.SOAPFaultException

@Service
class MedAdminNurseServiceImpl(val stsService: STSService, keyDepotService: KeyDepotService, val mapper: MapperFacade) :
    MemberDataService {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"
    private val genAsyncService = GenAsyncServiceImpl("hcpadm")

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val config = ConfigFactory.getConfigValidator(listOf())
    private val keyDepotManager = KeyDepotManagerImpl.getInstance(keyDepotService)

    override fun sendBatch(
        keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpQuality: String,
        hcpNihii: String,
        hcpName: String,
        hcpSsin: String?, requestList: MedAdminRequestListType
    ): EfactSendResponse {
        requireNotNull(keystoreId) { "Keystore id cannot be null" }
        requireNotNull(tokenId) { "Token id cannot be null" }
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw IllegalArgumentException("Cannot obtain token for Efact operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!
        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val encryptRequest = true

        val inputReference =
            IdGeneratorFactory.getIdGenerator().generateId()//.let { if (istest) "T" + it.substring(1) else it }
        val now = DateTime().withMillisOfSecond(0)

        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)
        val principal = SecurityContextHolder.getContext().authentication?.principal as? User
        val packageInfo = McnConfigUtil.retrievePackageInfo(
            "hcpadm",
            principal?.mcnLicense,
            principal?.mcnPassword,
            principal?.mcnPackageName
        )

        val postHeader = WsAddressingHeader(URI("urn:be:cin:nip:async:generic:post:msg")).apply {
            faultTo = "http://www.w3.org/2005/08/addressing/anonymous"
            replyTo = "http://www.w3.org/2005/08/addressing/anonymous"
            messageID = URI("uuid:" + UUID.randomUUID())
        }

        val issueInstantDateTime = DateTime()
        val issueInstant = XMLGregorianCalendarImpl(issueInstantDateTime.toGregorianCalendar())

        val unEncryptedMessage = ConnectorXmlUtils.toByteArray(requestList)

        val blobBuilder = BlobBuilderFactory.getBlobBuilder("hcpadm")
        val detailId = "_" + IdGeneratorFactory.getIdGenerator("uuid").generateId();

        val blob = unEncryptedMessage.let { aqb ->
            if (encryptRequest) {
                val identifierTypeString = config.getProperty("memberdata.keydepot.identifiertype", "CBE")
                val identifierValue = config.getLongProperty("memberdata.keydepot.identifiervalue", 820563481L)
                val applicationId = config.getProperty("memberdata.keydepot.application", "MYCARENET")
                val identifierSource = 48
                val identifier = IdentifierType.lookup(identifierTypeString, null as String?, identifierSource)

                val mbEtk = if (identifier == null) {
                    throw IllegalStateException("invalid configuration : identifier with type ]$identifierTypeString[ for source ETKDEPOT not found")
                } else {
                    keyDepotManager.getEtkSet(IdentifierType.CBE, identifierValue, applicationId, keystoreId, false)
                }

                crypto.seal(
                    Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, mbEtk, ConnectorXmlUtils.toByteArray(
                        EncryptedKnownContent().apply {
                            replyToEtk = keyDepotManager.getETK(credential, keystoreId).encoded
                            businessContent = BusinessContent().apply {
                                id = detailId
                                value = aqb
                            }
                        })
                ).let {
                    blobBuilder.build(it, "none", detailId, "text/xml", "M4A_XML", "encryptedForKnownBED")
                }
            } else blobBuilder.build(aqb, "none", detailId, "text/xml", "M4A_XML")
        }

        val ci = CommonInput().apply {
            request = be.cin.mycarenet.esb.common.v2.RequestType().apply {
                isIsTest = true
            }
            origin = buildOriginType(packageInfo, hcpQuality, hcpNihii, hcpSsin)

            this.inputReference = inputReference
        }

        val xades = BlobUtil.generateXades(credential, SendRequestMapper.mapBlobToBlobType(blob), "invoicing").value

        val post = BuilderFactory.getRequestObjectBuilder("hcpadm").buildPostRequest(ci, SendRequestMapper.mapBlobToCinBlob(blob), xades)
        val header: WsAddressingHeader
        try {
            header = WsAddressingHeader(URI("urn:be:cin:nip:async:generic:post:msg"))
            header.to = URI("")
            header.faultTo = "http://www.w3.org/2005/08/addressing/anonymous"
            header.replyTo = "http://www.w3.org/2005/08/addressing/anonymous"
            header.messageID = URI("" + UUID.randomUUID())
        } catch (e: URISyntaxException) {
            throw IllegalStateException(e)
        }

        val postResponse = genAsyncService.postRequest(samlToken, post, header)

        val tack = postResponse.getReturn()
        val success = tack.resultMajor != null && tack.resultMajor == "urn:nip:tack:result:major:success"

        if (!success) {
            throw IllegalStateException("postRequest failed : " + tack.resultMinor)
        }
        //Parse response and send it back to controller
    }

    private fun buildOriginType(
        packageInfo: McnPackageInfo,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String?
    ) = OrigineType().apply {
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
                        quality = hcpQuality;
                        value = ValueRefString()
                            .apply { value = hcpNihii.padEnd(11, '0') }
                    }

                physicalPerson = IdType().apply {
                    nihii = NihiiType().apply {
                        quality = hcpQuality;
                        value = ValueRefString()
                            .apply { value = hcpNihii.padEnd(11, '0') }
                    }

                    hcpSsin?.let {
                        ssin = ValueRefString().apply {
                            value = hcpSsin;
                        }
                    }
                }

                organization = IdType().apply {
                    nihii =
                        NihiiType().apply {
                            quality = hcpQuality;
                            value = ValueRefString()
                                .apply { value = hcpNihii.padEnd(11, '0') }
                        }
                }
            }
        }

    override fun loadMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        language: String,
        limit: Int
    ): List<EfactMessage> {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Efact operations")

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User

        requireNotNull(keystoreId) { "Keystore id cannot be null" }
        requireNotNull(tokenId) { "Token id cannot be null" }

        val inputReference = "" + System.currentTimeMillis()
        val requestObjectBuilder = try {
            BuilderFactory.getRequestObjectBuilder("invoicing")
        } catch (e: Exception) {
            throw IllegalArgumentException(e)
        }

        val ci = CommonInput().apply {
            request = be.cin.mycarenet.esb.common.v2.RequestType().apply {
                isIsTest = isTest
            }
            origin = buildOriginType(McnConfigUtil.retrievePackageInfo(
                "hcpadm",
                principal?.mcnLicense,
                principal?.mcnPassword,
                principal?.mcnPackageName
            ),samlToken.quality, hcpNihii, hcpSsin)
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

        val eFactMessages = ArrayList<EfactMessage>()

        while (retries-- > 0) {
            val msgQuery = requestObjectBuilder.createMsgQuery(batchSize, true, "M4A_CNF_XML", "M4A_FLAT", "M4A_XML")
            val query = requestObjectBuilder.createQuery(batchSize, true)

            val getResponse: GetResponse
            try {
                getResponse =
                    genAsyncService.getRequest(
                        samlToken,
                        requestObjectBuilder.buildGetRequest(ci.origin, msgQuery, query),
                        header
                    )
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

            eFactMessages += getResponse.getReturn().msgResponses.map { r ->
                EfactMessage().apply {
                    id = r.detail.id
                    name = r.detail.messageName

                    commonOutput = CommonOutput().apply {
                        this.inputReference = r.commonOutput.inputReference
                        this.nipReference = r.commonOutput.nipReference
                        this.outputReference = r.commonOutput.outputReference
                    }
                    try {
                        detail =
                            String(
                                ConnectorIOUtils.decompress(IOUtils.toByteArray(r.detail.value.inputStream)),
                                Charsets.UTF_8
                            ) //This starts with 92...

                        message =
                            BelgianInsuranceInvoicingFormatReader(language).parse(StringReader(this.detail!!))?.map {
                                Record(
                                    mapper.map(it.description, RecordOrSegmentDescription::class.java),
                                    it.zones.map { z ->
                                        Zone(
                                            mapper.map(z.zoneDescription, ZoneDescription::class.java),
                                            z.value
                                        )
                                    },
                                    mapper.map(it.errorDetail, ErrorDetail::class.java)
                                )
                            }
                        xades = Base64.encodeBase64String(r.xadesT.value)
                        hashValue = Base64.encodeBase64String(r.detail.hashValue)
                    } catch (e: IOException) {
                    }
                }
            } + getResponse.getReturn().tAckResponses.map { r ->
                EfactMessage().apply {
                    id = r.tAck.appliesTo.replace("urn:nip:reference:input:".toRegex(), "")
                    name = "tAck"
                    try {
                        tAck = r.tAck
                        xades = Base64.encodeBase64String(r.xadesT.value)
                        hashValue = Base64.encodeBase64String(r.tAck.value)
                    } catch (e: IOException) {
                    }
                }
            }

            break
        }
        return eFactMessages
    }

    override fun confirmAcks(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>
    ): Boolean {
        if (valueHashes.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Efact operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")

        val confirm =
            BuilderFactory.getRequestObjectBuilder("invoicing")
                .buildConfirmRequestWithHashes(buildOriginType(
                    samlToken.quality,
                    hcpNihii,
                    hcpSsin,
                    hcpFirstName,
                    hcpLastName
                ),
                    listOf(),
                    valueHashes.map { valueHash -> java.util.Base64.getDecoder().decode(valueHash) })

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }

    override fun confirmMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>
    ): Boolean {
        if (valueHashes.isEmpty()) {
            return true
        }
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Efact operations")

        val confirmheader = WsAddressingUtil.createHeader("", "urn:be:cin:nip:async:generic:confirm:hash")
        val confirm =
            BuilderFactory.getRequestObjectBuilder("invoicing")
                .buildConfirmRequestWithHashes(
                    buildOriginType(samlToken.quality, hcpNihii, hcpSsin, hcpFirstName, hcpLastName),
                    valueHashes.map { valueHash -> java.util.Base64.getDecoder().decode(valueHash) },
                    listOf()
                )

        genAsyncService.confirmRequest(samlToken, confirm, confirmheader)

        return true
    }

}
