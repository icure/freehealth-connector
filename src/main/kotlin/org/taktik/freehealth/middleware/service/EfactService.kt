package org.taktik.freehealth.middleware.service

import com.sun.org.apache.xpath.internal.operations.Bool
import org.taktik.connector.business.domain.dmg.DmgAcknowledge
import org.taktik.freehealth.middleware.dto.efact.*
import java.util.UUID

interface EfactService {

    fun sendBatch(keystoreId: UUID, tokenId: UUID, passPhrase: String, batch: InvoicesBatch): EfactSendResponse
    fun sendFlatFile(keystoreId: UUID, tokenId: UUID, passPhrase: String, invoice: InvoiceFlatFile): EfactSendResponse

    fun loadMessages(keystoreId: UUID,
                     tokenId: UUID,
                     passPhrase: String,
                     hcpNihii: String,
                     hcpSsin: String,
                     hcpFirstName: String,
                     hcpLastName: String,
                     language: String,
                     limit: Int
        ): List<EfactMessage>
    fun confirmAcks(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>
    ): Boolean

    fun confirmMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        valueHashes: List<String>
    ): Boolean

    fun makeFlatFile(batch: InvoicesBatch, isTest: Boolean, isMediprima: Boolean): String
    fun makeFlatFileCoreWithMetadata(batch: InvoicesBatch, isTest: Boolean, isMediprima: Boolean): FlatFileWithMetadata
}
