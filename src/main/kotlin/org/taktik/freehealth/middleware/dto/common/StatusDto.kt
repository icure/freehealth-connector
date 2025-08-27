package org.taktik.freehealth.middleware.dto.common

import be.fgov.ehealth.commons._1_0.core.LocalisedString

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement
import jakarta.xml.bind.annotation.XmlType
import java.io.Serializable
import java.util.ArrayList

class StatusDto(var code: String? = null, var messages: List<LocalisedString>? = null) : Serializable
