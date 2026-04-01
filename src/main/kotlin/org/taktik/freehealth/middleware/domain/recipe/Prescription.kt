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

package org.taktik.freehealth.middleware.domain.recipe

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import java.io.Serializable
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 18/06/13
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
open class Prescription(
    var creationDate: Date,
    var encryptionKeyId: String,
    var rid: String,
    var isFeedbackAllowed: Boolean? = null,
    var patientId: String? = null,
    var notificationWasSent: Boolean? = null,
    var requestXml: String? = null,
    var prescriberId: String? = null,
    var visionByOthers: String? = null,
    var status: String? = null,
    var validUntil: Date? = null,
    var decryptedContent: Kmehrmessage? = null
) : Serializable
