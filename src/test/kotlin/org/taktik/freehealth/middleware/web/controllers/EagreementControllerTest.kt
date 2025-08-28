package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.service.impl.EagreementServiceImpl


@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EagreementControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val gson = Gson()

    @Test
    fun createSynchronousAgreementRequest() {
        //generate test

        val io = "300"
        val gender = "male"
        val requestType = "agreement-ask"
        val messageEventSystem = ""
        val messageEventCode = ""
        val pathologyCode = ""
        val insuranceRef = ""
        val ioMembership = ""
        val annex1 = ""
        val annex2 = ""
        val orgNihii = ""
        val organizationType = ""
        val agreementType = ""
        val numberOfSessionForAnnex1 = ""
        val numberOfSessionForAnnex2 = ""

        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin4!!, password4!!)
        val agreement = this.restTemplate.exchange("http://localhost:$port/eagreement/askAgreement?hcpNihii=$nihii4&hcpName=$name4&hcpSsin=$ssin4&hcpFirstName=$firstName4&hcpLastName=$lastName4" +
            "&patientFirstName=$firstName4&patientLastName=$lastName4&patientGender=$gender&requestType=$requestType" +
            "&messageEventSystem=$messageEventSystem&messageEventCode=$messageEventCode&pathologyStartDate="+20200605+"&pathologyCode=$pathologyCode&insuranceRef=$insuranceRef" +
            "&patientSsin={$ssin4}&patientIo=$io&patientIoMembership=$ioMembership&annex1=$annex1&annex2=$annex2" +
            "&orgNihii=$orgNihii&organizationType=$organizationType&agreementStartDate="+20200605+"&agreementEndDate="+20200605+"&agreementType=$agreementType" +
            "&numberOfSessionForAnnex1=$numberOfSessionForAnnex1&numberOfSessionForAnnex2=$numberOfSessionForAnnex2",
        HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase).body
        println(agreement)

    }

    @Test
    fun askAgreement() {
    }
}
