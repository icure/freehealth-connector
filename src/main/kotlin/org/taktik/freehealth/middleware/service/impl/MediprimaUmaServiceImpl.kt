package org.taktik.freehealth.middleware.service.impl

import be.ehealth.businessconnector.mediprimauma.service.impl.MediprimaUmaServiceImpl
import be.fgov.ehealth.mediprimaUma.core.v1.ActorType
import be.fgov.ehealth.mediprimaUma.core.v1.AuthorType
import be.fgov.ehealth.mediprimaUma.core.v1.CriteriaType
import be.fgov.ehealth.mediprimaUma.core.v1.IdType
import be.fgov.ehealth.mediprimaUma.core.v1.PeriodType
import be.fgov.ehealth.mediprimaUma.protocol.v1.DeleteUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.v1.DeleteUrgentMedicalAidAttestationResponseType
import be.fgov.ehealth.mediprimaUma.protocol.v1.SearchUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.v1.SearchUrgentMedicalAidAttestationResponseType
import be.fgov.ehealth.mediprimaUma.protocol.v1.SendUrgentMedicalAidAttestationRequestType
import be.fgov.ehealth.mediprimaUma.protocol.v1.SendUrgentMedicalAidAttestationResponseType
import ma.glasnost.orika.MapperFacade
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaDeleteUrgentMedicalAidAttestationResponse
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaSearchUrgentMedicalAidAttestationResponse
import org.taktik.freehealth.middleware.domain.mediprimaUma.MediprimaUmaSendUrgentMedicalAidAttestationResponse
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.MediprimaUmaService
import java.io.StringWriter
import java.time.Instant
import java.time.ZoneId
import java.util.GregorianCalendar
import java.util.UUID
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

@Service
class MediprimaUmaServiceImpl(val stsService: STSService, keyDepotService: KeyDepotService, val mapper: MapperFacade): MediprimaUmaService{

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val mediprimaUmaService = MediprimaUmaServiceImpl()
    private val config = ConfigFactory.getConfigValidator(listOf())
    val zone = ZoneId.of("Europe/Brussels")

    override fun sendUrgentMedicalAidAttestation(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        startDate: Instant,
        endDate: Instant,
        medicalCover: String
    ): MediprimaUmaSendUrgentMedicalAidAttestationResponse? {
        val soapAction = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1:sendMedicalEmergencyAttestion";
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Mediprima UMA send operations")
        val detailId = "R" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val issueInstant: Instant = Instant.now()

        val request: SendUrgentMedicalAidAttestationRequestType = SendUrgentMedicalAidAttestationRequestType().apply {
            this.issueInstant = instantToXMLGregorianCalendarDateTime(issueInstant, zone)
            this.id = detailId
            this.beneficiarySsin = patientSsin
            this.medicalCover = medicalCover
            this.validityPeriod = PeriodType().apply {
                this.startDate = instantToXMLGregorianCalendarDate(startDate, zone)
                this.endDate = instantToXMLGregorianCalendarDate(endDate, zone)
            }

            this.author = (this.author ?: AuthorType()).apply {
                getHcParty().add(getActorType(hcpNihii, hcpLastName, hcpFirstName))
            }
        }
        this.mediprimaUmaService.sendUrgentMedicalAidAttestation(samlToken, request, soapAction).let { response ->
            val result = SendUrgentMedicalAidAttestationResponseType().apply {
                this.id = response.id
                this.issueInstant = response.issueInstant
                this.inResponseTo = response.inResponseTo
                this.status = response.status
            }

            val jaxbContext = JAXBContext.newInstance(SendUrgentMedicalAidAttestationResponseType::class.java)
            val marshaller: Marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            val sw = StringWriter()
            marshaller.marshal(response, sw)
            return MediprimaUmaSendUrgentMedicalAidAttestationResponse().apply {
                this.status = response.status.statusCode.value
                this.response = result
                this.mycarenetConversation = MycarenetConversation().apply {
                    this.soapRequest = null
                    this.soapResponse = null
                    this.transactionRequest = MarshallerHelper(SendUrgentMedicalAidAttestationRequestType::class.java, SendUrgentMedicalAidAttestationRequestType::class.java).toXMLByteArray(request).toString(Charsets.UTF_8)
                    this.transactionResponse = MarshallerHelper(SendUrgentMedicalAidAttestationResponseType::class.java, SendUrgentMedicalAidAttestationResponseType::class.java).toXMLByteArray(response).toString(Charsets.UTF_8)
                }
            }

        }
    }

    override fun searchUrgentMedicalAidAttestation(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        attestationNumber: String?,
        startDate: Instant?,
        endDate: Instant?,
        medicalCover: String?
    ): MediprimaUmaSearchUrgentMedicalAidAttestationResponse? {
        val soapAction = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1:searchMedicalEmergencyAttestion"
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Mediprima UMA search operations")
        val detailId = "R" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val issueInstant: Instant = Instant.now()

        val request: SearchUrgentMedicalAidAttestationRequestType = SearchUrgentMedicalAidAttestationRequestType().apply {
            this.issueInstant = instantToXMLGregorianCalendarDateTime(issueInstant, zone)
            this.id = detailId
            this.criteria = CriteriaType().apply {
                this.beneficiarySsin = patientSsin
                attestationNumber?.let {
                    this.attestationNumber = attestationNumber
                }
                startDate?.let { s ->
                    endDate?.let { e ->
                        medicalCover?.let { m ->
                            this.period = PeriodType().apply {
                                this.startDate = instantToXMLGregorianCalendarDate(startDate, zone)
                                this.endDate = instantToXMLGregorianCalendarDate(endDate, zone)
                            }
                            this.medicalCover = m
                        }
                    }
                }
            }
        }

        this.mediprimaUmaService.searchUrgentMedicalAidAttestation(samlToken, request, soapAction).let{ response ->
            val result = SearchUrgentMedicalAidAttestationResponseType().apply {
                this.id = response.id
                this.issueInstant = response.issueInstant
                this.status = response.status
            }

            val jaxbContext = JAXBContext.newInstance(SearchUrgentMedicalAidAttestationResponseType::class.java)
            val marshaller: Marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            val sw = StringWriter()
            marshaller.marshal(response, sw)
            return MediprimaUmaSearchUrgentMedicalAidAttestationResponse().apply {
                this.status = response.status.statusCode.value
                this.response = result
                this.mycarenetConversation = MycarenetConversation().apply {
                    this.soapRequest = null
                    this.soapResponse = null
                    this.transactionRequest = MarshallerHelper(SearchUrgentMedicalAidAttestationRequestType::class.java, SearchUrgentMedicalAidAttestationRequestType::class.java).toXMLByteArray(request).toString(Charsets.UTF_8)
                    this.transactionResponse = MarshallerHelper(SearchUrgentMedicalAidAttestationResponseType::class.java, SearchUrgentMedicalAidAttestationResponseType::class.java).toXMLByteArray(response).toString(Charsets.UTF_8)
                }
            }
        }
    }

    override fun deleteUrgentMedicalAidAttestation(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        patientSsin: String,
        attestationNumber: String
    ): MediprimaUmaDeleteUrgentMedicalAidAttestationResponse? {
        val soapAction = "urn:be:fgov:ehealth:mediprima:uma:protocol:v1:deleteMedicalEmergencyAttestion";
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Mediprima delete operations")

        val detailId = "R" + IdGeneratorFactory.getIdGenerator("uuid").generateId()
        val issueInstant: Instant = Instant.now()

        val request: DeleteUrgentMedicalAidAttestationRequestType = DeleteUrgentMedicalAidAttestationRequestType().apply {
            this.issueInstant = instantToXMLGregorianCalendarDateTime(issueInstant, zone)
            this.id = detailId
            this.attestationNumber = attestationNumber
            this.beneficiarySsin = patientSsin
            this.author = (this.author ?: AuthorType()).apply {
                getHcParty().add(getActorType(hcpNihii, hcpLastName, hcpFirstName))
            }
        }

        this.mediprimaUmaService.deleteUrgentMedicalAidAttestation(samlToken, request, soapAction).let { response ->
            val result = DeleteUrgentMedicalAidAttestationResponseType().apply {
                this.id = response.id
                this.issueInstant = response.issueInstant
                this.inResponseTo = response.inResponseTo
                this.status = response.status
            }

            val jaxbContext = JAXBContext.newInstance(DeleteUrgentMedicalAidAttestationResponseType::class.java)
            val marshaller: Marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            val sw = StringWriter()
            marshaller.marshal(response, sw)
            return MediprimaUmaDeleteUrgentMedicalAidAttestationResponse().apply {
                this.status = response.status.statusCode.value
                this.response = result
                this.mycarenetConversation = MycarenetConversation().apply {
                    this.soapRequest = null
                    this.soapResponse = null
                    this.transactionRequest = MarshallerHelper(DeleteUrgentMedicalAidAttestationRequestType::class.java, DeleteUrgentMedicalAidAttestationRequestType::class.java).toXMLByteArray(request).toString(Charsets.UTF_8)
                    this.transactionResponse = MarshallerHelper(DeleteUrgentMedicalAidAttestationResponseType::class.java, DeleteUrgentMedicalAidAttestationResponseType::class.java).toXMLByteArray(response).toString(Charsets.UTF_8)
                }
            }
        }
    }

    private fun getActorType(hcpNihii: String, hcpLastName: String, hcpFirstName: String): ActorType {
        val actorType = ActorType().apply {
            val idType = IdType().apply {
                value = hcpNihii
                type = "urn:be:fgov:ehealth:1.0:physician:nihii-number"
            }
            name = hcpLastName
            firstName.add(hcpFirstName)
            id.add(idType)

        }
        return actorType
    }

   private fun instantToXMLGregorianCalendarDateTime(
       instant: Instant,
       zoneId: ZoneId = ZoneId.systemDefault()
   ): XMLGregorianCalendar {
       val zdt = instant.atZone(zoneId)
       val gregorianCalendar = GregorianCalendar.from(zdt)
       return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar)
   }

    private fun instantToXMLGregorianCalendarDate(
        instant: Instant,
        zoneId: ZoneId = ZoneId.systemDefault()
    ): XMLGregorianCalendar {
        val zdt = instant.atZone(zoneId)
        val offsetMinutes = zdt.offset.totalSeconds / 60
        return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
            zdt.year,
            zdt.monthValue,
            zdt.dayOfMonth,
            offsetMinutes
        )
    }
}
