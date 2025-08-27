package org.taktik.freehealth.middleware.dto.consultrn

import be.fgov.ehealth.consultrn._1_0.core.CountryType

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlType
import java.io.Serializable

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlainAddressType", propOrder = ["address", "country"])
class ConsultRnPlainAddressDto(var country: CountryType? = null, var address: String? = null) : Serializable
