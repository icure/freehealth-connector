package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.mediprima.protocol.v2.BySsinType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedDataType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse
import ma.glasnost.orika.MapperFacade
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.connector.business.mediprimav2.service.impl.MediprimaServiceImpl
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.MediprimaService
import org.taktik.freehealth.middleware.service.STSService
import java.time.Instant
import java.util.*
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

@Service
class MediprimaServiceImpl(val stsService: STSService, keyDepotService: KeyDepotService, val mapper: MapperFacade) : MediprimaService {

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val keyDepotManager = KeyDepotManagerImpl.getInstance(keyDepotService)
    private val mediprimaService = MediprimaServiceImpl()

    override fun consultCaremedData(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String?,
        hcpName: String,
        passPhrase: String,
        patientSsin: String?,
        startDate: Instant,
        endDate: Instant,
        referenceDate: Instant
    ): ConsultCarmedInterventionResponse? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Mediprima operations")
        val datatypeFactory: DatatypeFactory = DatatypeFactory.newInstance()
        val now: GregorianCalendar = GregorianCalendar(TimeZone.getTimeZone("Europe/Brussels")).apply {
            time = Date()
        }
        val issueInstant: XMLGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(now)
        val consultCaremedRequest = be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequestType().apply {
            this.issueInstant = issueInstant
            this.selectionCriteria = ConsultCarmedDataType().apply {
                this.bySsin = BySsinType().apply {
                    this.ssin = patientSsin
                    this.referenceDate = issueInstant//DatatypeFactory.newInstance().newXMLGregorianCalendar(referenceDate.toString())
                }
            }
        }

        this.mediprimaService.consultMediprima(samlToken, consultCaremedRequest, "urn:be:fgov:ehealth:mediprima:consultCarmedIntervention").let { response ->
            val result = ConsultCarmedInterventionResponse()
            result.upstreamTiming = response.upstreamTiming
            result.soapRequest = response.soapRequest
            result.soapResponse = response.soapResponse
            return result
        }
    }

}
