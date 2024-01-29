package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
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
        val (keystoreId, tokenId, passPhrase) = register(restTemplate!!, port, ssin1!!, password1!!)
        val agreement = this.restTemplate.exchange("http://localhost:$port/agreement/askAgreement?hcpNihii=$nihii1&hcpName=$name1&hcpSsin=$ssin1&hcpFirstName=$firstName1&hcpLastName=$lastName1" +
            "&patientFirstName=$firstName2&patientLastName=$lastName2&patientGender=&requestType=${EagreementServiceImpl.RequestTypeEnum.ASK}" +
            "&messageEventSystem={messageEventSystem}&messageEventCode={messageEventCode}&pathologyStartDate="+20200605+"&pathologyCode={pathologyCode}&insuranceRef={insuranceRef}" +
            "&patientSsin={ssin2}&patientIo={io}&patientIoMembership={ioMembership}&annex1={annex1}&annex2={annex2}" +
            "&orgNihii={nihii}&organizationType={organizationType}&parameterNames={parameterNames}&agreementStartDate="+20200605+"&agreementEndDate="+20200605+"&agreementType={agreementType}" +
            "&numberOfSessionForAnnex1={numberOfSessionForAnnex1}&numberOfSessionForAnnex2={numberOfSessionForAnnex2}",
        HttpMethod.POST, HttpEntity<Void>(createHeaders(null, null, keystoreId, tokenId, passPhrase)), String::class.java, passPhrase).body

    }
}
