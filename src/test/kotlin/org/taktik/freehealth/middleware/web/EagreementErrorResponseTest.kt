package org.taktik.freehealth.middleware.web

import jakarta.xml.soap.SOAPConstants
import jakarta.xml.soap.SOAPFactory
import jakarta.xml.ws.soap.SOAPFaultException
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.taktik.freehealth.middleware.mapper.MapperFacade
import org.taktik.freehealth.middleware.service.EagreementService
import org.taktik.freehealth.middleware.web.controllers.EagreementController
import java.lang.reflect.Proxy
import java.util.UUID
import javax.xml.namespace.QName

/**
 * Offline check that an eHealth SOAP fault raised during an eAgreement call reaches the client
 * as a JSON error body, not as plain text.
 */
class EagreementErrorResponseTest {
    private val faultMessage = "ROUTING_INVALID: Mutuality tag value doesn't match ([1234569]([0-9]{2})?)"

    private val failingService = Proxy.newProxyInstance(
        EagreementService::class.java.classLoader,
        arrayOf(EagreementService::class.java)
    ) { _, _, _ ->
        throw SOAPFaultException(
            SOAPFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL)
                .createFault(faultMessage, QName("urn:test", "Client"))
        )
    } as EagreementService

    private val mockMvc = MockMvcBuilders
        .standaloneSetup(EagreementController(failingService, MapperFacade()))
        .setControllerAdvice(ExceptionHandlers())
        .build()

    @Test
    fun soapFaultIsReturnedAsJson() {
        mockMvc.perform(
            post("/eagreement/askAgreement")
                .servletPath("/eagreement/askAgreement")
                .header("X-FHC-keystoreId", UUID.randomUUID().toString())
                .header("X-FHC-tokenId", UUID.randomUUID().toString())
                .header("X-FHC-passPhrase", "pass")
                .param("hcpQuality", "physiotherapist")
                .param("hcpNihii", "50052295501")
                .param("hcpSsin", "94120729204")
                .param("hcpFirstName", "Hcp")
                .param("hcpLastName", "Test")
                .param("prescriberNihii", "18785633004")
                .param("prescriberFirstName", "Prescriber")
                .param("prescriberLastName", "Test")
                .param("patientFirstName", "Patient")
                .param("patientLastName", "Test")
                .param("patientGender", "male")
                .param("pathologyStartDate", "20260216")
                .param("pathologyCode", "fa-1")
                .param("sctCode", "91251008")
                .param("prescriptionDate", "20260914")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]")
        )
            .andExpect(status().isBadGateway)
            .andExpect(jsonPath("$.status").value(502))
            .andExpect(jsonPath("$.message").value(faultMessage))
            .andExpect(jsonPath("$.path").value("/eagreement/askAgreement"))
    }
}
