/*
 *
 * Copyright (C) 2018 iCure SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.web.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.sun.xml.messaging.saaj.soap.impl.ElementImpl
import com.sun.xml.messaging.saaj.soap.ver1_1.DetailEntry1_1Impl
import org.taktik.freehealth.middleware.mapper.MapperFacade
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import org.taktik.freehealth.middleware.dto.etarif.TarificationConsultationResult
import org.taktik.freehealth.middleware.service.TarificationService
import java.time.LocalDateTime
import java.util.*

/**
 * REST controller for consulting official Belgian healthcare tariffs and fees.
 *
 * Provides an endpoint to look up INAMI/RIZIV nomenclature codes and their associated tariffs
 * for a given patient. The tarification consultation takes into account factors such as
 * the patient's insurance status, GMD (Global Medical Dossier) holder, trainee supervision,
 * guard post context, anatomy, and related services.
 */
@RestController
@RequestMapping("/tarif")
@Tag(name = "Tarification", description = "Tarification and fee consultation service. Allows looking up official Belgian healthcare tariffs and fees (INAMI/RIZIV nomenclature codes) for a given patient.")
class TarificationController(val tarificationService: TarificationService, val mapper: MapperFacade) {
    private val consultTarifErrors =
        ObjectMapper().readValue<Array<MycarenetError>>(
            this.javaClass.getResourceAsStream("/be/errors/ConsultTarifErrors.json")!!
        ).associateBy({ it.uid }, { it })

    /**
     * Consults official Belgian healthcare tariffs (INAMI/RIZIV nomenclature) for a given patient.
     *
     * Accepts a list of nomenclature codes and returns the applicable tariffs for the patient
     * identified by their SSIN. The consultation takes into account the healthcare provider's
     * identity, optional GMD holder, justification codes, trainee supervisor details, guard post
     * context, anatomy specifications, and related services.
     *
     * The [date] parameter is encoded as a long in YYYYMMDD format (e.g., 20260115 for January 15, 2026).
     * If not provided, the current date is used.
     *
     * The [anatomy] and [relatedService] parameters can be comma-separated lists that must match
     * the length of [codes], or a single value applied to all codes.
     *
     * @param ssin the patient's social security identification number (SSIN/NISS)
     * @param tokenId UUID of the SAML authentication token
     * @param keystoreId UUID of the uploaded PKCS12 keystore
     * @param passPhrase passphrase to decrypt the keystore's private key
     * @param hcpFirstName first name of the healthcare provider
     * @param hcpLastName last name of the healthcare provider
     * @param hcpNihii NIHII number of the healthcare provider
     * @param hcpSsin social security number of the healthcare provider
     * @param date optional consultation date in YYYYMMDD format; defaults to today if not provided
     * @param gmdNihii optional NIHII number of the GMD (Global Medical Dossier) holder
     * @param justification optional justification code for the consultation
     * @param traineeSupervisorSsin optional SSIN of the trainee's supervisor
     * @param traineeSupervisorNihii optional NIHII number of the trainee's supervisor
     * @param traineeSupervisorFirstName optional first name of the trainee's supervisor
     * @param traineeSupervisorLastName optional last name of the trainee's supervisor
     * @param guardPostNihii optional NIHII number of the guard post
     * @param guardPostSsin optional SSIN associated with the guard post
     * @param anatomy optional anatomy code(s), comma-separated if multiple (must match codes length)
     * @param relatedService optional related service code(s), comma-separated if multiple (must match codes length)
     * @param codes list of INAMI/RIZIV nomenclature codes to look up tariffs for
     * @return a [TarificationConsultationResult] containing the applicable tariffs or errors if the consultation fails
     */
    @Operation(
        summary = "Consult tarification for a patient",
        description = "Looks up official Belgian healthcare tariffs and fees (INAMI/RIZIV nomenclature codes) for a given patient identified by SSIN. Accepts a list of nomenclature codes in the request body and returns the applicable tariffs, taking into account optional parameters such as GMD holder, justification, trainee supervisor, guard post, anatomy, and related services."
    )
    @PostMapping("/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultTarification(
        @PathVariable ssin: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) gmdNihii: String?,
        @RequestParam(required = false) justification: String?,
        @RequestParam(required = false) traineeSupervisorSsin: String?,
        @RequestParam(required = false) traineeSupervisorNihii: String?,
        @RequestParam(required = false) traineeSupervisorFirstName: String?,
        @RequestParam(required = false) traineeSupervisorLastName: String?,
        @RequestParam(required = false) guardPostNihii: String?,
        @RequestParam(required = false) guardPostSsin: String?,
        @RequestParam(required = false) anatomy: String?,
        @RequestParam(required = false) relatedService: String?,
        @RequestBody codes: List<String>
    ) = try {
        tarificationService.consultTarif(
        keystoreId = keystoreId,
        tokenId = tokenId,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        passPhrase = passPhrase,
        patientSsin = ssin,
        consultationDate = date?.let { LocalDateTime.of((date / 10000).toInt(), ((date / 100).toInt() % 100), (date % 100).toInt(), 0, 0)} ?: LocalDateTime.now(),
        justification = justification,
        gmdNihii = gmdNihii,
        codes = codes,
        traineeSupervisorSsin = traineeSupervisorSsin,
        traineeSupervisorNihii = traineeSupervisorNihii,
        traineeSupervisorFirstName = traineeSupervisorFirstName,
        traineeSupervisorLastName = traineeSupervisorLastName,
        guardPostNihii = guardPostNihii,
        guardPostSsin = guardPostSsin,
        anatomies = if (anatomy?.contains(',') == true) anatomy.split(',').also {
            if (it.size != codes.size) throw IllegalArgumentException("Anatomy length must match codes length.")
        } else codes.map { anatomy },
        relatedServices = if (relatedService?.contains(',') == true) relatedService.split(',').also {
            if (it.size != codes.size) throw IllegalArgumentException("Related services length must match codes length.")
        } else codes.map { relatedService }).let { mapper.map(it, TarificationConsultationResult::class.java) } }
    catch (e: jakarta.xml.ws.soap.SOAPFaultException) {
         TarificationConsultationResult().apply {
             errors = extractError(e).toMutableList()
         }
    }
    catch (e : Exception) {
        TarificationConsultationResult().apply { errors.add(MycarenetError(
            code = "999999",
            msgFr = e.message,
            msgNl = e.message,
            locFr = e.stackTrace?.toList()?.joinToString(";") { it.toString() },
            locNl = e.stackTrace?.toList()?.joinToString(";") { it.toString() }))
        }
    }

    private fun extractError(e: jakarta.xml.ws.soap.SOAPFaultException): Set<MycarenetError> {
        val result = mutableSetOf<MycarenetError>()

        e.fault.detail.detailEntries.forEach { entry ->
            if(entry != null) {
                val detailEntry = entry as DetailEntry1_1Impl
                val codeElements = detailEntry.getElementsByTagName("Code")
                for (i in 0..<codeElements.length){
                    val codeElement = codeElements.item(i) as ElementImpl
                    val currentConsultTarifErrors = consultTarifErrors.values.filter { it.code == codeElement.value }
                    if (currentConsultTarifErrors.isNotEmpty()) result.addAll(currentConsultTarifErrors)
                    else {
                        val msgElements = detailEntry.getElementsByTagName("Message")
                        val msgElement = msgElements.item(0) as ElementImpl
                        result.add(MycarenetError(
                            code = codeElement.value,
                            msgFr = msgElement.value,
                            msgNl = msgElement.value)
                        )
                    }
                }
            }
        }
        return result
    }

}
