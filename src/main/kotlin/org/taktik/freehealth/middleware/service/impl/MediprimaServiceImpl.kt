package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.etee.crypto.utils.KeyManager
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import ma.glasnost.orika.MapperFacade
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.taktik.connector.business.mediprimav2.service.impl.MediprimaServiceImpl
import org.taktik.connector.technical.service.etee.CryptoFactory
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotManagerImpl
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.MediprimaService
import org.taktik.freehealth.middleware.service.STSService
import java.time.Instant
import java.util.*
import javax.xml.datatype.DatatypeFactory

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
                ?: throw MissingTokenException("Cannot obtain token for Genins operations")

        val principal = SecurityContextHolder.getContext().authentication?.principal as? User

        log.debug("consultCaremedData called with principal " + (principal?._id
            ?: "<ANONYMOUS>") + " and license " + (principal?.mcnLicense ?: "<DEFAULT>"))


        val consultCaremedRequest = be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequestType().apply {
            this.issueInstant = XMLGregorianCalendarImpl(DateTime.now().toGregorianCalendar())
            this.selectionCriteria.bySsin.ssin = patientSsin
            this.selectionCriteria.bySsin.referenceDate =  DatatypeFactory.newInstance().newXMLGregorianCalendar()
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
