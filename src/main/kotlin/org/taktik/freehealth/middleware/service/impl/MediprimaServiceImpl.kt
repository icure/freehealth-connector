package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.mediprima.core.v2.BySsinType
import be.fgov.ehealth.mediprima.core.v2.ConsultCarmedDataType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequestType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
import ma.glasnost.orika.MapperFacade
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.connector.business.mediprimav2.service.impl.MediprimaServiceImpl
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.MediprimaService
import org.taktik.freehealth.middleware.service.STSService
import java.io.StringWriter
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBElement
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar
import javax.xml.namespace.QName

@Service
class MediprimaServiceImpl(val stsService: STSService, keyDepotService: KeyDepotService, val mapper: MapperFacade) : MediprimaService {

    private val log = LoggerFactory.getLogger(this.javaClass)
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
    ): ConsultCarmedInterventionResponseType? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Mediprima operations")
        val zone = ZoneId.of("Europe/Brussels")
        val zdt: ZonedDateTime = referenceDate.atZone(zone)
        val year = zdt.year
        val month = zdt.monthValue
        val day = zdt.dayOfMonth

        val datatypeFactory = DatatypeFactory.newInstance()
        val refXgc: XMLGregorianCalendar = datatypeFactory.newXMLGregorianCalendarDate(
            year,
            month,
            day,
            DatatypeConstants.FIELD_UNDEFINED  // on ne précise pas de zone pour la date seule
        )

        val detailId = "R" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val issueInstant: DateTime = DateTime.now()
        val consultCaremedRequestType: ConsultCarmedInterventionRequestType = ConsultCarmedInterventionRequestType().apply {
            this.issueInstant = issueInstant
            this.id = detailId
            this.selectionCriteria = ConsultCarmedDataType().apply {
                this.bySsin = BySsinType().apply {
                    this.ssin = patientSsin
                    this.referenceDate = refXgc
                }
            }
        }

        this.mediprimaService.consultMediprima(samlToken, consultCaremedRequestType, "urn:be:fgov:ehealth:mediprima:protocol:v2:consultCarmedIntervention").let { response ->
            val result = ConsultCarmedInterventionResponseType()
            val jaxbContext = JAXBContext.newInstance(ConsultCarmedInterventionResponseType::class.java)
            val marshaller: Marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            val sw = StringWriter()
            marshaller.marshal(response, sw)
            //println(sw.toString())
            return result
        }
    }

    fun marshalToString(request: Any): String {
        val jaxbContext = JAXBContext.newInstance(request.javaClass)
        val marshaller = jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)

        val writer = StringWriter()

        val qName = QName("urn:be:fgov:ehealth:mediprima:protocol:v2", "ConsultCarmedInterventionRequest")
        val jaxbElement = JAXBElement(qName, request.javaClass, request)

        marshaller.marshal(jaxbElement, writer)
        return writer.toString()
    }

}
