package org.taktik.freehealth.middleware.web.controllers

import com.google.gson.Gson
import org.joda.time.DateTime
import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.service.sts.STSService
import org.taktik.freehealth.middleware.MyTestsConfiguration
import org.taktik.freehealth.middleware.service.impl.AgreementServiceImpl

@RunWith(SpringRunner::class)
@Import(MyTestsConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AgreementControllerTest : EhealthTest() {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    private val gson = Gson()

    @Test
    fun createSynchronousAgreementRequest() {
        val request = AgreementServiceImpl(stsService = null, keyDepotService = null).createSynchronousAgreementRequest(
            requestType = AgreementServiceImpl.RequestTypeEnum.ASK,
            messageEventSystem = "",
            messageEventCode = "",
            patientFirstName = "",
            patientLastName = "",
            patientGender = "",
            patientSsin = "",
            patientIo = "",
            patientIoMembership = "",
            pathologyStartDate = DateTime(),
            pathologyCode = "",
            insuranceRef = "",
            hcpNihii = "",
            hcpFirstName = "",
            hcpLastName = "",
            orgNihii = "",
            organizationType = "",
            annex1 = "",
            annex2 = "",
            parameterNames = Array<String>(1) { "" },
            agreementStartDate = DateTime(),
            agreementEndDate = DateTime(),
            agreementType = "",
            numberOfSessionForAnnex1 = 10f,
            numberOfSessionForAnnex2 = 10f
        )

        println(request);
    }


}
