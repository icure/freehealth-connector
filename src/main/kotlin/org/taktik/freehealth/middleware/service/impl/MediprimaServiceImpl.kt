package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.mediprima.protocol.v2.BySsinType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedDataType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequestType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse
import ma.glasnost.orika.MapperFacade
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.connector.business.mediprimav2.service.impl.MediprimaServiceImpl
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.MediprimaService
import org.taktik.freehealth.middleware.service.STSService
import java.io.StringWriter
import java.time.Instant
import java.time.LocalDate
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeConstants
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
        val detailId = "R" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val issueInstant: XMLGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(now)
        val referenceDate: XMLGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(now)
        val consultCaremedRequestType: ConsultCarmedInterventionRequestType = ConsultCarmedInterventionRequestType().apply {
            this.issueInstant = issueInstant
            this.id = detailId
            this.selectionCriteria = ConsultCarmedDataType().apply {
                this.bySsin = BySsinType().apply {
                    this.ssin = patientSsin
                    this.referenceDate = normalizeToDateOnly(referenceDate)//DatatypeFactory.newInstance().newXMLGregorianCalendar(referenceDate.toString())
                }
            }
        }
        println("Request is: " +marshalToString(consultCaremedRequestType))

        this.mediprimaService.consultMediprima(samlToken, consultCaremedRequestType, "urn:be:fgov:ehealth:mediprima:consultCarmedIntervention").let { response ->
            val result = ConsultCarmedInterventionResponse()
            result.upstreamTiming = response.upstreamTiming
            result.soapRequest = response.soapRequest
            result.soapResponse = response.soapResponse
            return result
        }
    }

    fun marshalToString(request: Any): String {
        val jaxbContext = JAXBContext.newInstance(request.javaClass)
        val marshaller = jaxbContext.createMarshaller()

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true) // Indente le XML

        val writer = StringWriter()
        marshaller.marshal(request, writer)
        return writer.toString()
    }

    fun normalizeToDateOnly(date: XMLGregorianCalendar): XMLGregorianCalendar {
        date.hour = DatatypeConstants.FIELD_UNDEFINED
        date.minute = DatatypeConstants.FIELD_UNDEFINED
        date.second = DatatypeConstants.FIELD_UNDEFINED
        date.millisecond = DatatypeConstants.FIELD_UNDEFINED
        date.timezone = DatatypeConstants.FIELD_UNDEFINED

        return date
    }

}
