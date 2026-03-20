package org.taktik.freehealth.middleware.dto.etarif

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError
import java.io.Serializable
import java.util.ArrayList
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
class TarificationConsultationResult {
    var commonOutput: CommonOutput? = null
    var mycarenetConversation: MycarenetConversation? = null
    var birthdate: Date? = null
    var CT1: String? = null
    var CT2: String? = null
    var date: Date? = null
    var deceased: Date? = null
    var errors: MutableList<MycarenetError> = ArrayList()
    var firstName: String? = null
    var insurancePeriodEnd: Date? = null
    var insurancePeriodStart: Date? = null
    var lastName: String? = null
    var niss: String? = null
    var sex: Sex? = null
    var codeResults: MutableList<CodeResult> = ArrayList()

    enum class Sex : Serializable {
        MALE, FEMALE
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Payment : Serializable {
        var amount: Double = 0.toDouble()
        var currencyUnit: String? = null
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class CodeResult : Serializable {
        var code: String? = null
        var fee: Payment? = null
        var reimbursement: Payment? = null
        var patientFee: Payment? = null
        var contract: String? = null
        var justification: String = "0"
    }
}
