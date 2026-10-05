package org.taktik.freehealth.utils

import be.cin.encrypted.EncryptedKnownContent
import be.cin.types.v1.Blob
import org.taktik.connector.business.mycarenetdomaincommons.mapper.DomainBlobMapper
import org.taktik.connector.technical.service.etee.Crypto
import org.taktik.connector.technical.utils.ConnectorIOUtils
import org.taktik.connector.technical.utils.MarshallerHelper

/**
 * Thrown when the payload of a generic async message cannot be decoded.
 * [stage] tells which decoding step failed and [payloadHead] holds the first bytes (hex) of the data given to that step.
 */
class AsyncPayloadDecodingException(val stage: String, val payloadHead: String?, cause: Throwable) :
    RuntimeException("Cannot decode async message payload at stage [$stage]: ${cause.message}", cause)

object AsyncPayloadDecoder {
    private const val HEAD_SIZE = 16


    /**
     * Turns the Detail blob of a generic async message into the business payload bytes:
     * decompresses it, unseals it when it is encrypted for a known recipient and unwraps the EncryptedKnownContent.
     */
    fun decodeDetail(detail: Blob, crypto: Crypto): ByteArray {
        val content = stage("read detail", null) { DomainBlobMapper.mapToBlob(detail).content }
        val data = if (detail.contentEncoding == "deflate") stage("decompress detail", content) { ConnectorIOUtils.decompress(content) } else content
        if (detail.contentEncryption != "encryptedForKnownRecipient") return data

        val unsealedData = stage("unseal detail", data) { crypto.unseal(Crypto.SigningPolicySelector.WITHOUT_NON_REPUDIATION, data).contentAsByte }
        val businessContent = stage("unmarshal EncryptedKnownContent", unsealedData) {
            MarshallerHelper(EncryptedKnownContent::class.java, EncryptedKnownContent::class.java).toObject(unsealedData).businessContent
        }
        return if (businessContent.contentEncoding == "deflate") stage("decompress business content", businessContent.value) { ConnectorIOUtils.decompress(businessContent.value) } else businessContent.value
    }

    /** Unmarshals the decoded payload, reporting a failure as an [AsyncPayloadDecodingException] carrying the payload head. */
    fun <T> unmarshal(payload: ByteArray, clazz: Class<T>): T =
        stage("unmarshal ${clazz.simpleName}", payload) { MarshallerHelper(clazz, clazz).toObject(payload) }

    fun hexHead(bytes: ByteArray?) = bytes?.take(HEAD_SIZE)?.joinToString(" ") { "%02x".format(it) }

    private fun <T> stage(name: String, input: ByteArray?, block: () -> T): T = try {
        block()
    } catch (e: AsyncPayloadDecodingException) {
        throw e
    } catch (e: Exception) {
        throw AsyncPayloadDecodingException(name, input?.let { "${hexHead(it)} (${it.size} bytes)" }, e)
    }
}
