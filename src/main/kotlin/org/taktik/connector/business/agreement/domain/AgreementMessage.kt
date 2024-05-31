package org.taktik.connector.business.agreement.domain

import be.cin.nip.async.generic.TAck
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput

class AgreementMessage {
    var detail: ByteArray? = null
    var id: String? = null
    var name: String? = null

    var commonOutput: CommonOutput? = null

    var xades : ByteArray? = null

    var reference: String? = null
}

