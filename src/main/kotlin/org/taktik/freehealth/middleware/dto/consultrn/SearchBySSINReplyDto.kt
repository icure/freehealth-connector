package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.ErrorType
import be.fgov.ehealth.consultrn._1_0.core.PersonType
import be.fgov.ehealth.consultrn._1_0.protocol.ConsultRnReplyType
import org.taktik.freehealth.middleware.dto.common.StatusDto

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement
import jakarta.xml.bind.annotation.XmlType
import java.io.Serializable

class SearchBySSINReplyDto(status: StatusDto? = null, id: String? = null, errorInformations: List<ErrorType>? = null, var person: ConsultRnPersonDto? = null) : ConsultRnReplyDto(status, id, errorInformations), Serializable
