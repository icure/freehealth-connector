package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse
import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrrequest
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse
import be.fgov.ehealth.standards.kmehr.cd.v1.CDERRORschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.taktik.connector.business.agreement.exception.AgreementBusinessConnectorException
import org.taktik.connector.business.agreement.service.impl.AgreementServiceImpl
import org.taktik.connector.business.agreement.validator.AgreementXmlValidatorImpl
import org.taktik.connector.business.domain.agreement.AgreementResponse
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.connector.technical.validator.XMLValidator
import org.taktik.freehealth.middleware.drugs.logic.DrugsLogic
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.AgreementService
import org.taktik.freehealth.middleware.service.STSService
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*
import javax.xml.bind.JAXBContext

class AgreementServiceImpl(private val stsService: STSService, private val keyDepotService: KeyDepotService) : AgreementService {
    private val freehealthAgreementService: org.taktik.connector.business.agreement.service.AgreementService =
        AgreementServiceImpl()

    private val agreementXmlValidator: XMLValidator = AgreementXmlValidatorImpl()
    private val config = ConfigFactory.getConfigValidator(emptyList())
    private var configValidator = ConfigFactory.getConfigValidator(listOf(
        "chapterIV.keydepot.application",
        "chapterIV.keydepot.identifiertype",
        "chapterIV.keydepot.identifiersubtype",
        "chapterIV.keydepot.identifiervalue"))

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

    override fun consultAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String
    ): AgreementResponse {
        val isTest = config.getProperty("endpoint.agreement").contains("-acpt")
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Agreement operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)


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

    override fun requestAgreement(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        passPhrase: String
    ): AgreementResponse {
        val isTest = config.getProperty("endpoint.agreement").contains("-acpt")
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Agreement operations")
        val keystore = stsService.getKeyStore(keystoreId, passPhrase)!!

        val credential = KeyStoreCredential(keystoreId, keystore, "authentication", passPhrase, samlToken.quality)
        val hokPrivateKeys = KeyManager.getDecryptionKeys(keystore, passPhrase.toCharArray())
        val crypto = CryptoFactory.getCrypto(credential, hokPrivateKeys)


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
}
