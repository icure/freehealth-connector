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

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.taktik.freehealth.middleware.service.APBService

/**
 * REST controller for the Association Pharmaceutique Belge (APB - Belgian Pharmaceutical Association).
 * Provides endpoints for obtaining authentication bearer tokens needed to access APB and FTM
 * (Formulaire Therapeutique Magistral) pharmaceutical services and product information APIs.
 *
 * @property apbService the service handling APB and FTM token retrieval
 */
@RestController
@RequestMapping("/apb")
@Tag(name = "APB", description = "Association Pharmaceutique Belge (Belgian Pharmaceutical Association). Provides pharmaceutical product information.")
class APBController(private val apbService: APBService) {
    val log = LoggerFactory.getLogger(this.javaClass)

    /**
     * Retrieves an authentication bearer token for the APB (Association Pharmaceutique Belge) API.
     * This token is required to authenticate subsequent calls to APB pharmaceutical product
     * information services.
     *
     * @return the APB bearer token as a string
     */
    @Operation(
        summary = "Get APB bearer token",
        description = "Retrieves an authentication bearer token for the APB (Belgian Pharmaceutical Association) API."
    )
    @GetMapping("/token/bearer", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAPBBearerToken() = apbService.getAPBBearerToken()

    /**
     * Retrieves an authentication bearer token for the FTM (Formulaire Therapeutique Magistral) API.
     * The FTM provides information about magistral (compounded) pharmaceutical preparations.
     * This token is required to authenticate subsequent calls to FTM services.
     *
     * @return the FTM bearer token as a string
     */
    @Operation(
        summary = "Get FTM bearer token",
        description = "Retrieves an authentication bearer token for the FTM (Formulaire Thérapeutique Magistral) API."
    )
    @GetMapping("/token/bearer/ftm", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFTMBearerToken() = apbService.getFTMBearerToken()
}
