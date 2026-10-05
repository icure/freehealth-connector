package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.mediprima.core.v2.BySsinType
import be.fgov.ehealth.mediprima.core.v2.CbssStatusType
import be.fgov.ehealth.mediprima.core.v2.ConsultCarmedDataType
import be.fgov.ehealth.mediprima.core.v2.MedicalCardRegistryStatusType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequestType
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponseType
import be.fgov.ehealth.messageservices.core.v1.PatientType
import be.fgov.ehealth.messageservices.core.v1.RequestType
import be.fgov.ehealth.messageservices.core.v1.RetrieveTransactionRequest
import be.fgov.ehealth.messageservices.core.v1.RetrieveTransactionResponse
import be.fgov.ehealth.messageservices.core.v1.SelectRetrieveTransactionType
import be.fgov.ehealth.messageservices.core.v1.TransactionType
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDERRORMYCARENETschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDINSURANCE
import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDINSURANCEschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType
import be.fgov.ehealth.standards.kmehr.schema.v1.ContentType
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType
import be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ma.glasnost.orika.MapperFacade
import org.apache.commons.logging.LogFactory
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.domain.etarif.TarificationConsultationResult
import org.taktik.connector.business.domain.etarif.TarificationMediprimaConsultationResult
import org.taktik.connector.business.mediprimav2.service.impl.MediprimaServiceImpl
import org.taktik.connector.business.mycarenetcommons.mapper.SendRequestMapper
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory
import org.taktik.connector.business.mycarenetdomaincommons.domain.CareReceiverId
import org.taktik.connector.business.mycarenetdomaincommons.domain.Routing
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.domain.mediprima.MediprimaMdaResponse
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.MediprimaService
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.protocol.Response
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.ByteArrayInputStream
import java.io.StringReader
import java.io.StringWriter
import java.io.UnsupportedEncodingException
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBElement
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar
import javax.xml.namespace.NamespaceContext
import javax.xml.namespace.QName
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

@Service
class MediprimaServiceImpl(val stsService: STSService, keyDepotService: KeyDepotService, val mapper: MapperFacade) : MediprimaService {

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val mediprimaService = MediprimaServiceImpl()

    private val config = ConfigFactory.getConfigValidator(listOf())
    private val freehealthTarificationService = org.taktik.connector.business.tarification.impl.TarificationServiceImpl()
    private val ConsultTarifErrors =
        Gson().fromJson(
            this.javaClass.getResourceAsStream("/be/errors/ConsultTarificationMediprimaErrors.json").reader(Charsets.UTF_8),
            arrayOf<MycarenetError>().javaClass
        ).associateBy({ it.uid }, { it })
    private val xPathfactory = XPathFactory.newInstance()

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
    ): MediprimaMdaResponse? {
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
            val result = ConsultCarmedInterventionResponseType().apply {
                this.id = detailId
                this.issueInstant = issueInstant
                this.selectionCriteria = consultCaremedRequestType.selectionCriteria
                this.result = response.result
                this.status = response.status
                this.inResponseTo = response.inResponseTo
            }

            val cbssStatus = response.status?.statusDetail?.anies
                ?.filterIsInstance<Element>()
                ?.firstOrNull { it.localName == "CbssStatus" }
                ?.let { unmarshalElement(it, CbssStatusType::class.java) }

            val medicalStatus = response.status?.statusDetail?.anies
                ?.filterIsInstance<Element>()
                ?.firstOrNull { it.localName == "MedicalCardRegistryStatus" }
                ?.let { unmarshalElement(it, MedicalCardRegistryStatusType::class.java) }

            val jaxbContext = JAXBContext.newInstance(ConsultCarmedInterventionResponseType::class.java)
            val marshaller: Marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            val sw = StringWriter()
            marshaller.marshal(response, sw)
            return MediprimaMdaResponse().apply {
                this.status = response.status.statusCode.value
                this.response = result
                this.medicalStatus = medicalStatus
                this.cbssStatus = cbssStatus
                this.mycarenetConversation = MycarenetConversation().apply {
                    this.soapRequest = null
                    this.soapResponse = null
                    this.transactionResponse =  MarshallerHelper(ConsultCarmedInterventionResponseType::class.java, ConsultCarmedInterventionResponseType::class.java).toXMLByteArray(response).toString(Charsets.UTF_8)
                    this.transactionRequest =  MarshallerHelper(ConsultCarmedInterventionRequestType::class.java, ConsultCarmedInterventionRequestType::class.java).toXMLByteArray(consultCaremedRequestType).toString(Charsets.UTF_8)
                }
            }
        }
    }

    fun <T> unmarshalElement(element: Element, clazz: Class<T>): T {
        val context = JAXBContext.newInstance(clazz)
        val unmarshaller = context.createUnmarshaller()
        val jaxbElement = unmarshaller.unmarshal(element, clazz)
        return jaxbElement.value
    }

    override fun consultTarif(
        keystoreId: UUID,
        tokenId: UUID,
        hcpFirstName: String,
        hcpLastName: String,
        hcpNihii: String,
        hcpSsin: String,
        passPhrase: String,
        patientSsin: String?,
        consultationDate: LocalDateTime,
        traineeSupervisorSsin: String?,
        traineeSupervisorNihii: String?,
        traineeSupervisorFirstName: String?,
        traineeSupervisorLastName: String?,
        guardPostNihii: String?,
        guardPostSsin: String?,
        codes: List<String>
    ): TarificationMediprimaConsultationResult? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Tarif operations")

        try {
            val isTest = config.getProperty("endpoint.mcn.tarification.mediprima").contains("-acpt")
            val now = DateTime().withMillisOfSecond(0).withZone(null)
            val kmehrUUID = now.toString("YYYYddhhmmssSS")
            val requestAuthorNihii = (guardPostNihii ?: hcpNihii).padEnd(11, '0')
            val requestAuthorSsin = guardPostSsin ?: hcpSsin
            val reqId = "${(guardPostNihii ?: hcpNihii).padEnd(11, '0')}.$kmehrUUID"
            val quality = "doctor"
            val hcParty = if (guardPostNihii?.isEmpty() != false) "persphysician" else "guardpost"

            //  The author is always the caller
            val author = AuthorType().apply {
                hcparties.add(HcpartyType().apply {
                    if(guardPostNihii != null) {
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = guardPostNihii.padEnd(11, '0') })
                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = hcParty })
                    }else{
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                        ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                        cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = hcParty })
                        firstname = hcpFirstName
                        familyname = hcpLastName
                    }
                })
            }

            //  The supervisor
            val supervisor = AuthorType().apply {
                hcparties.add(HcpartyType().apply {
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = traineeSupervisorNihii })
                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = traineeSupervisorSsin })
                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = hcParty })
                    firstname = traineeSupervisorFirstName
                    familyname = traineeSupervisorLastName
                })
            }

            val csDT = DateTime(consultationDate.year, consultationDate.monthValue, consultationDate.dayOfMonth, 0, 0)
            val req = RetrieveTransactionRequest().apply {

                this.request = RequestType().apply {
                    messageProtocoleSchemaVersion = BigDecimal("1.18")
                    id = IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = reqId }
                    this.author = author
                    date = now; time = now
                }
                this.select = SelectRetrieveTransactionType().apply {
                    patient = PatientType().apply {
                        ids.add(IDPATIENT().apply { s = IDPATIENTschemes.ID_PATIENT; sv = "1.0"; value = patientSsin })
                    }
                    transaction = TransactionType().apply {
                        var h = 1

                        traineeSupervisorNihii?.let {
                            this.author = supervisor
                        } ?: run {
                            this.author = AuthorType().apply {
                                hcparties.add(HcpartyType().apply {
                                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.ID_HCPARTY; sv = "1.0"; value = hcpNihii })
                                    ids.add(IDHCPARTY().apply { s = IDHCPARTYschemes.INSS; sv = "1.0"; value = hcpSsin })
                                    cds.add(CDHCPARTY().apply { s = CDHCPARTYschemes.CD_HCPARTY; sv = "1.3"; value = hcParty })
                                    firstname = hcpFirstName
                                    familyname = hcpLastName
                                })
                            }
                        }

                        cds.add(CDTRANSACTION().apply { s = CDTRANSACTIONschemes.CD_TRANSACTION_MYCARENET; sv = "1.2"; value = "tariffmediprima" })


                        headingsAndItemsAndTexts.add(ItemType().apply {
                            ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                            cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.9"; value = "encounterdatetime" })
                            contents.add(ContentType().apply { date = csDT })
                        })

                        headingsAndItemsAndTexts.addAll(codes.mapIndexed { index, code ->
                            ItemType().apply {
                                ids.add(IDKMEHR().apply { s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value = (h++).toString() })
                                cds.add(CDITEM().apply { s = CDITEMschemes.CD_ITEM; sv = "1.9"; value = "claim" })
                                contents.add(ContentType().apply {
                                    cds.add(CDCONTENT().apply { s = CDCONTENTschemes.CD_NIHDI; sv = "1.0"; value = code })
                                })
                            }
                        })
                    }
                }
            }

            val kmehrRequestMarshaller =
                MarshallerHelper(RetrieveTransactionRequest::class.java, RetrieveTransactionRequest::class.java)
            val xmlByteArray = kmehrRequestMarshaller.toXMLByteArray(req)

            if (xmlByteArray != null && config.getBooleanProperty("mcn.tarification.dumpMessages", false)) {
                log.debug("RequestObjectBuilder : created blob content: " + String(xmlByteArray))
            }

            val blob =
                BlobBuilderFactory.getBlobBuilder("mcn.tarification").build(xmlByteArray)

            val request = TarificationConsultationRequest().apply {
                this.commonInput = be.fgov.ehealth.mycarenet.commons.core.v2.CommonInputType().apply {
                    this.inputReference = kmehrUUID
                    this.request = be.fgov.ehealth.mycarenet.commons.core.v2.RequestType().apply {
                        this.isIsTest = isTest
                    }
                    this.origin = be.fgov.ehealth.mycarenet.commons.core.v2.OriginType().apply {
                        val principal = SecurityContextHolder.getContext().authentication?.principal as? User

                        this.`package` = be.fgov.ehealth.mycarenet.commons.core.v2.PackageType().apply {
                            this.name =
                                be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                    .apply { this.value = config.getProperty("mcn.registration.package.name") }
                            this.license = be.fgov.ehealth.mycarenet.commons.core.v2.LicenseType().apply {
                                this.username = principal?.mcnLicense
                                    ?: config.getProperty("mycarenet.license.username")
                                this.password = principal?.mcnPassword
                                    ?: config.getProperty("mycarenet.license.password")
                            }
                        }

                        this.careProvider = be.fgov.ehealth.mycarenet.commons.core.v2.CareProviderType().apply {
                            this.nihii = be.fgov.ehealth.mycarenet.commons.core.v2.NihiiType().apply {
                                this.quality = if (guardPostNihii?.isEmpty() != false) quality else "guardpost"
                                this.value =
                                    be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                        .apply { this.value = requestAuthorNihii }
                            }
                            if (guardPostNihii?.isEmpty() != false) {
                                this.physicalPerson = be.fgov.ehealth.mycarenet.commons.core.v2.IdType().apply {
                                    this.ssin =
                                        be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString()
                                            .apply { this.value = hcpSsin }
                                }
                            }
                        }
                    }
                }
                this.id = IdGeneratorFactory.getIdGenerator("xsid").generateId()
                this.issueInstant = DateTime()
                this.routing = SendRequestMapper.mapRouting(Routing(CareReceiverId(patientSsin), csDT))
                this.detail = SendRequestMapper.mapBlobToBlobType(blob)
            }

            var consultTarificationResponse = freehealthTarificationService.consultTarificationMediPrima(samlToken, request)

            val detail = consultTarificationResponse.getReturn().detail
            val content = BlobBuilderFactory.getBlobBuilder("mcn.tarification").checkAndRetrieveContent(SendRequestMapper.mapBlobTypeToBlob(detail))

            val helper =
                MarshallerHelper(RetrieveTransactionResponse::class.java, RetrieveTransactionResponse::class.java)
            val commonInputResponse = helper.toObject(content)

            val commonOutput =
                CommonOutput(
                    consultTarificationResponse.`return`?.commonOutput?.inputReference?.toString(),
                    consultTarificationResponse.`return`?.commonOutput?.nipReference?.toString(),
                    consultTarificationResponse.`return`?.commonOutput?.outputReference?.toString())

            val result = TarificationMediprimaConsultationResult().apply {
                this.mycarenetConversation = MycarenetConversation().apply {
                    consultTarificationResponse.soapRequest?.writeTo(this.soapRequestOutputStream())
                    consultTarificationResponse.soapResponse?.writeTo(this.soapResponseOutputStream())
                    this.transactionRequest = MarshallerHelper(TarificationConsultationRequest::class.java, TarificationConsultationRequest::class.java)
                        .toXMLByteArray(request)
                        .toString(Charsets.UTF_8)
                    this.transactionResponse = MarshallerHelper(RetrieveTransactionResponse::class.java, RetrieveTransactionResponse::class.java)
                        .toXMLByteArray(commonInputResponse)
                        .toString(Charsets.UTF_8)
                }
                this.commonOutput = commonOutput
                this.patient = TarificationMediprimaConsultationResult.Patient()
            }

            val errors = commonInputResponse.acknowledge.errors?.flatMap { e ->
                e.cds.find { it.s == CDERRORMYCARENETschemes.CD_ERROR }?.value?.let { ec ->
                    extractError(xmlByteArray, ec, e.url)
                } ?: setOf()
            }

            val kmehrmessage = commonInputResponse.kmehrmessage


            if (kmehrmessage != null && kmehrmessage!!.folders != null && kmehrmessage!!.folders.size > 0) {
                val folder = kmehrmessage!!.folders.get(0)
                if (folder.patient != null) {
                    result.patient.apply {
                        this.niss = folder.patient!!.ids.find { it.s == IDPATIENTschemes.ID_PATIENT }?.value
                        this.sex = folder.patient!!.sex.cd.value.toString()
                        this.lastName = folder.patient!!.familyname
                        this.firstName = folder.patient!!.firstnames[0]
                    }
                }
                if (folder.transactions != null) {
                    result.fill(folder.transactions)
                }
            }

            result.errors = errors


            val xml = MarshallerHelper(TarificationMediprimaConsultationResult::class.java, TarificationMediprimaConsultationResult::class.java)
                .toXMLByteArray(result)
                .toString(Charsets.UTF_8)

            println(xml)

            return result
        } catch (e: ConnectorException) {
            throw IllegalStateException(e)
        } catch (e: UnsupportedEncodingException) {
            throw IllegalStateException(e)
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

    private fun extractError(sendTransactionRequest: ByteArray, ec: String, errorUrl: String?): Set<MycarenetError> {
        return errorUrl?.let { url ->
            val factory = DocumentBuilderFactory.newInstance()
            factory.isNamespaceAware = true
            val builder = factory.newDocumentBuilder()

            val xpath = xPathfactory.newXPath()
            val expr = xpath.compile(if (url.startsWith("/")) url else "/" + url)
            val result = mutableSetOf<MycarenetError>()

            (expr.evaluate(
                builder.parse(ByteArrayInputStream(sendTransactionRequest)),
                XPathConstants.NODESET
            ) as NodeList).let { it ->
                if (it.length > 0) {
                    var node = it.item(0)
                    val textContent = node.textContent
                    var base = "/" + nodeDescr(node)
                    while (node.parentNode != null && node.parentNode is Element) {
                        base = "/${nodeDescr(node.parentNode)}$base"
                        node = node.parentNode
                    }
                    val initialElements = ConsultTarifErrors.values.filter {
                        it.path == base && it.code == ec && (it.regex == null || url.matches(Regex(".*${it.regex}.*")))
                    }

                    val elements = if (initialElements.isEmpty()) {
                        // IOs sometimes are overeager to provide us with precise xpath. Let's try again while truncating after the item
                        val truncatedBase = base.replace(Regex("(.+/item.+?)/.*"), "$1")
                        ConsultTarifErrors.values.filter {
                            it.path == truncatedBase && it.code == ec && (it.regex == null || url.matches(Regex(".*${it.regex}.*")))
                        }
                    } else {
                        initialElements
                    }

                    elements.forEach { it.value = textContent }
                    result.addAll(elements)
                } else {
                    result.add(
                        MycarenetError(
                            code = ec,
                            path = url,
                            msgFr = "Erreur générique, xpath invalide",
                            msgNl = "Onbekend foutmelding, xpath ongeldig"
                        )
                    )
                }
            }
            if (result.isEmpty()) {
                result.add(
                    MycarenetError(
                        code = ec,
                        path = url,
                        msgFr = "Erreur générique, xpath invalide",
                        msgNl = "Onbekend foutmelding, xpath ongeldig"
                    ))

            }
            result
        } ?: setOf()
    }

    private fun nodeDescr(node: Node): String {
        val localName = node.localName ?: node.nodeName?.replace(Regex(".+?:(.+)"), "$1") ?: "unknown"
        val xpath = xPathfactory.newXPath()
        xpath.namespaceContext = object : NamespaceContext {
            override fun getNamespaceURI(prefix: String?) = when (prefix) {
                "ns2" -> "http://www.ehealth.fgov.be/messageservices/core/v1"
                "ns3" -> "http://www.ehealth.fgov.be/standards/kmehr/schema/v1"
                else -> null
            }

            override fun getPrefix(namespaceURI: String?) = when (namespaceURI) {
                "http://www.ehealth.fgov.be/messageservices/core/v1" -> "ns2"
                "http://www.ehealth.fgov.be/standards/kmehr/schema/v1" -> "ns3"
                else -> null
            }

            override fun getPrefixes(namespaceURI: String?): Iterator<Any?> =
                when (namespaceURI) {
                    "http://www.ehealth.fgov.be/messageservices/core/v1" -> listOf("ns2").iterator()
                    "http://www.ehealth.fgov.be/standards/kmehr/schema/v1" -> listOf("ns3").iterator()
                    else -> listOf<String>().iterator()
                }
        }
        return when {
            localName == "transaction" -> "transaction[${xpath.evaluate("ns2:cd[@S=\"CD-TRANSACTION-MYCARENET\"]", node)}]"
            localName == "item" -> "item[${xpath.evaluate("ns3:cd[@S=\"CD-ITEM-MYCARENET\" or @S=\"CD-ITEM\"]", node)}]"
            localName == "cd" && node is Element -> {
                if (node.getAttribute("SL")?.isNotEmpty() == true) "cd[${node.getAttribute("SL")}]" else "cd[${node.getAttribute("S")}]"
            }
            else -> localName
        }
    }

}
