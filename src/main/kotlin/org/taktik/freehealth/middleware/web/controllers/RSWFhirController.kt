package org.taktik.freehealth.middleware.web.controllers

import org.taktik.freehealth.middleware.mapper.MapperFacade
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.service.RswFhirService
import java.util.UUID

@RestController
@RequestMapping("/rsw/fhir")
@Tag(name = "RSWFhir", description = "Réseau Santé Wallon FHIR interface for accessing the Walloon health network using the FHIR standard.")
class RSWFhirController(val rswFhirService: RswFhirService) {
    @Operation(
        summary = "Search patient data on RSW",
        description = "Searches for patient health data on the Réseau Santé Wallon (Walloon Health Network) using the FHIR standard, identified by NIHII and patient SSIN."
    )
    @GetMapping("/{nihii}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun search(
        @PathVariable nihii: String,
        @RequestHeader(name = "X-FHC-RSW-actor-id") clientId: String,
        @RequestHeader(name = "X-FHC-RSW-actor-type") clientType: String,
        @RequestHeader(name = "X-FHC-RSW-actor-secret") clientSecret: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable patientSsin: String
                    ) = rswFhirService.search(nihii, clientId, clientType, clientSecret, keystoreId, passPhrase, patientSsin)
}
