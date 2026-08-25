/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.dto.efact
/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 19/08/15
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
class InvoiceItem {
    var dateCode: Long? = null
    var endDateCode: Long? = null
    var codeNomenclature: Long = 0
    var relatedCode: Long? = null

    var eidItem: EIDItem? = null
    var insuranceRef: String? = null
    var insuranceRefDate: Long? = null

    /**
     * Agreement number, written to ET 52 Z 19 (20 positions, INAMI annexe 6.8).
     *
     * This is *not* [insuranceRef], which goes to ET 51 Z 42. It is the number an insurer assigned to an agreement
     * decision and returned through eAgreement; INAMI annexe 26.4 makes it mandatory for physiotherapists since
     * 01/05/2022, and its absence on e.g. code 567011 is rejected with 521904.
     *
     * Structure: XXX (mutuality) + 15 digits unique per insurer + DD (check-digit, modulo 97). Optional: when it is
     * null the zone is written as twenty zeroes, exactly as before this field existed.
     */
    var agreementNumber: String? = null

    var units: Int = 0

    var reimbursedAmount: Long = 0
    var patientFee: Long = 0
    var doctorSupplement: Long = 0

    var transplantationCode: InvoicingTransplantationCode? = null
    var sideCode: InvoicingSideCode? = null
    var timeOfDay: InvoicingTimeOfDay? = null

    var override3rdPayerCode: String? = null
    var gnotionNihii: String? = null

    var derogationMaxNumber: InvoicingDerogationMaxNumberCode? = null
    var prescriberNorm: InvoicingPrescriberCode? = null
    var prescriberNihii: String? = null
    var prescriptionDate: Long? = null

    var personalInterventionCoveredByThirdPartyCode: Int? = null

    var doctorIdentificationNumber: String? = null
    var invoiceRef: String? = null
    var percentNorm: InvoicingPercentNorm? = null

    var internshipNihii: String? = null
    var anatomy : String? = null
    var productLabel: String? = null

    var options: Map<String, String>? = null
}
