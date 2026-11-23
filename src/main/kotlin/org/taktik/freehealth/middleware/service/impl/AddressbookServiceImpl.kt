/*
 *
 * Copyright (C) 2018 Taktik SA
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

package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.addressbook.core.v1.IndividualContactInformationType
import be.fgov.ehealth.addressbook.core.v1.OrganizationContactInformationType
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoRequest
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoRequest
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsRequest
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsRequest
import org.joda.time.DateTime
import org.springframework.stereotype.Service
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.AddressType
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.dto.Telecom
import org.taktik.freehealth.middleware.dto.TelecomType
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.AddressbookService
import org.taktik.freehealth.middleware.service.STSService
import java.util.UUID

@Service
class AddressbookServiceImpl(val stsService: STSService) : AddressbookService {
    private val freehealthTokenAddressbookService: org.taktik.connector.business.addressbook.service.AddressbookTokenService =
        org.taktik.connector.business.addressbook.service.impl.AddressbookTokenServiceImpl(
            EhealthReplyValidatorImpl()
        )

    override fun searchHcp(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        lastName: String?,
        firstName: String?,
        profession: String?,
        nihii: String?,
        ssin: String?,
        zipCode: String?,
        city: String?,
        email: String?,
        offset: Int,
        limit: Int
    ): List<HealthcareParty> {
        val queryLastName = lastName?.trim()?.takeIf { it.isNotEmpty() }
        val queryFirstName = firstName?.trim()?.takeIf { it.isNotEmpty() }
        val queryProfession = profession?.trim()?.takeIf { it.isNotEmpty() && !it.equals("ALL", true) }
        val queryNihii = nihii?.trim()?.takeIf { it.isNotEmpty() }
        val querySsin = ssin?.trim()?.takeIf { it.isNotEmpty() }
        val queryZipCode = zipCode?.trim()?.takeIf { it.isNotEmpty() }
        val queryCity = city?.trim()?.takeIf { it.isNotEmpty() }
        val queryEmail = email?.trim()?.takeIf { it.isNotEmpty() }
        // Hoisted: inside apply {} a bare `offset` would resolve to the receiver's own member, not to this parameter
        val queryOffset = offset.coerceAtLeast(0)
        val queryLimit = limit.coerceIn(1, MAX_SEARCH_RESULTS)

        require(queryNihii == null || querySsin == null) { "nihii and ssin are mutually exclusive" }
        require(queryZipCode == null || queryCity == null) { "zipCode and city are mutually exclusive" }
        // Only guard against the fully empty query; which combinations the addressbook actually supports is the
        // service's call, and its refusal ("This combination of search criteria is not supported.") now reaches the
        // caller through ExceptionHandlers.
        require(
            listOfNotNull(
                queryLastName, queryFirstName, queryProfession, queryNihii, querySsin, queryZipCode, queryCity,
                queryEmail
            ).isNotEmpty()
        ) { "At least one search criterion is required" }

        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Addressbook operations")
        val searchProfessionals =
            freehealthTokenAddressbookService.searchProfessionals(samlToken, SearchProfessionalsRequest().apply {
                this.lastName = queryLastName; this.firstName = queryFirstName
                this.profession = queryProfession
                this.nihii = queryNihii; this.ssin = querySsin
                this.zipCode = queryZipCode; this.city = queryCity
                this.eMail = queryEmail
                issueInstant = DateTime.now()
                this.offset = queryOffset
                maxElements = queryLimit
            })
        return searchProfessionals.healthCareProfessionals.map { hcp ->
            val hcpProfession = queryProfession?.let { wanted ->
                hcp.professions?.find { p -> p.professionCodes?.any { it.value.equals(wanted, true) } ?: false }
            } ?: hcp.professions?.firstOrNull()
            HealthcareParty(lastName = hcp.lastName,
                            firstName = hcp.firstName,
                            gender = Gender.fromCode(hcp.gender) ?: Gender.unknown,
                            speciality = hcpProfession?.specialities?.firstOrNull()?.let { spec ->
                                spec.specialityFriendlyNames?.firstOrNull()?.value ?: spec.specialityCode
                            },
                            nihii = hcpProfession?.nihii,
                            ssin = hcp.ssin,
                            professionCodes = hcpProfession?.professionCodes ?: listOf(),
                            ehealthBoxes = listOf())
        }
    }

    override fun searchOrg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        name: String,
        type: String
    ): List<HealthcareParty> {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Addressbook operations")
        val searchProfessionals =
            freehealthTokenAddressbookService.searchOrganizations(samlToken, SearchOrganizationsRequest().apply {
                institutionName = name; issueInstant = DateTime.now(); institutionType = type
                offset = 0; maxElements = 100
            })
        return searchProfessionals.healthCareOrganizations.map {
            HealthcareParty(
                name = it.names.joinToString { it.value },
                cbe = if (it.id.type == "CBE") it.id.value else null,
                ehp = if (it.id.type == "HCI") it.id.value else null,
                nihii = if (it.id.type == "NIHII") it.id.value else null,
                ehealthBoxes = listOf()
                           )
        }
    }

    override fun getHcp(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        nihii: String?,
        ssin: String?,
        quality: String?,
        language: String
    ): HealthcareParty? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Addressbook operations")
        val professionalContactInfo =
            freehealthTokenAddressbookService.getProfessionalContactInfo(
                samlToken,
                GetProfessionalContactInfoRequest().apply {
                    this.nihii = nihii; this.ssin = ssin; issueInstant = DateTime.now()
                })
        return professionalContactInfo.individualContactInformation?.let { makeHealthcareParty(it, nihii, quality, language) }
    }

    override fun getOrg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ehp: String?,
        cbe: String?,
        nihii: String?,
        language: String
    ): HealthcareParty? {
        val samlToken =
            stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
                ?: throw MissingTokenException("Cannot obtain token for Addressbook operations")
        val professionalContactInfo =
            freehealthTokenAddressbookService.getOrganizationContactInfo(
                samlToken,
                GetOrganizationContactInfoRequest().apply {
                    this.nihii = nihii; this.cbe = cbe; this.ehp = ehp; issueInstant = DateTime.now()
                })
        return professionalContactInfo.organizationContactInformation?.let { makeHealthcareParty(it, language) }
    }

    private fun makeHealthcareParty(it: IndividualContactInformationType, nihii: String?, quality: String?, language: String): HealthcareParty {
        val professionalInformation =
            it.professionalInformations.find { pi ->
                nihii?.let { it == pi?.profession?.nihii } ?: pi.profession?.professionCodes?.any { it.value == (quality ?: "PHYSICIAN") } ?: false
            } ?: it.professionalInformations.firstOrNull()
        return HealthcareParty(
            lastName = it.lastName,
            firstName = it.firstName,
            gender = Gender.fromCode(it.gender) ?: Gender.unknown,
            nihii = professionalInformation?.profession?.nihii,
            ssin = it.ssin,
            professionCodes = professionalInformation?.profession?.professionCodes ?: listOf(),
            ehealthBoxes = it.professionalInformations?.mapNotNull { it.eHealthBox } ?: listOf()
                              ).apply {
            addresses.addAll(professionalInformation?.addresses?.map {
                Address(addressType = AddressType.work,
                        street = (it.street.descriptions.find { it.lang == language }
                            ?: it.street.descriptions.firstOrNull())?.value,
                        houseNumber = it.houseNumber,
                        postboxNumber = it.postBox,
                        country = (it.country.descriptions.find { it.lang == language }
                            ?: it.country.descriptions.firstOrNull())?.value,
                        postalCode = it.municipality?.zipCode?.toString(),
                        city = (it.municipality.descriptions.find { it.lang == language }
                            ?: it.municipality.descriptions.firstOrNull())?.value).apply {
                    telecoms.addAll(professionalInformation.healthCareAdditionalInformations?.filter { it.type == "Mail" }?.map {
                        Telecom(
                            telecomType = TelecomType.email,
                            telecomNumber = it.value
                        )
                    } ?: listOf())
                }
            } ?: listOf())
        }
    }

    private fun makeHealthcareParty(org: OrganizationContactInformationType, language: String): HealthcareParty {
        return HealthcareParty(name = org.names.find { language == it.lang }?.value
            ?: org.names.joinToString { it.value },
                               nihii = if (org.id.authenticSource == "NIHII") org.id.value else null,
                               cbe = if (org.id.authenticSource == "CBE") org.id.value else null,
                               ehp = if (org.id.authenticSource == "EHP" || org.id.authenticSource == "EHP_TEST") org.id.value else null,
                               type = org.organizationTypeCodes?.find { it.type == "code" }?.value,
                               ehealthBoxes = org.eHealthBoxes
        ).apply {
            addresses.addAll(org.addresses?.map {
                Address(addressType = AddressType.work,
                        street = (it.street.descriptions.find { it.lang == language }
                            ?: it.street.descriptions.firstOrNull())?.value,
                        houseNumber = it.houseNumber,
                        postboxNumber = it.postBox,
                        country = (it.country.descriptions.find { it.lang == language }
                            ?: it.country.descriptions.firstOrNull())?.value,
                        postalCode = it.municipality?.zipCode?.toString(),
                        city = (it.municipality.descriptions.find { it.lang == language }
                            ?: it.municipality.descriptions.firstOrNull())?.value).apply {
                    telecoms.addAll(org.healthCareAdditionalInformations?.filter { it.type == "Mail" }?.map {
                        Telecom(
                            telecomType = TelecomType.email,
                            telecomNumber = it.value
                        )
                    } ?: listOf())
                }
            } ?: listOf())
        }
    }

    companion object {
        // The addressbook service refuses maxElements above 100 with
        // "The maxElements paging attribute is too high." (measured against acceptance)
        private const val MAX_SEARCH_RESULTS = 100
    }
}
