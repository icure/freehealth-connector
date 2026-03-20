package org.taktik.freehealth.middleware.dto.consultrnv2

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.joda.time.DateTime
import be.fgov.ehealth.commons.core.v2.Status
import be.fgov.ehealth.rn.personservice.core.v1.PersonResponseResultsType
import java.lang.Exception

@JsonIgnoreProperties(ignoreUnknown = true)
class RnConsultSearchPersonPhoneticallyResponseDto(
        var id: String? = null,
        var inResponseTo: String? = null,
        var issueInstant: DateTime? = null,
        var status: Status? = null,
        var result: PersonResponseResultsType? = null,
        var xmlConversations: RnConsultConversationDto? = null,
        var exception: Exception? = null
)
