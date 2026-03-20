package org.taktik.freehealth.middleware.dto.dmg

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType

import java.io.Serializable
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class DmgExtension : DmgMessageWithPatient(), Serializable {
    var hcParty: HcpartyType? = null
    var claim: String? = null
    var encounterDate: Date? = null
}
