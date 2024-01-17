package org.taktik.freehealth.middleware.service.impl

import org.joda.time.DateTime
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.binary.Binary
import org.taktik.icure.fhir.entities.r4.bundle.Bundle
import org.taktik.icure.fhir.entities.r4.bundle.BundleEntry
import org.taktik.icure.fhir.entities.r4.claim.Claim
import org.taktik.icure.fhir.entities.r4.claim.ClaimInsurance
import org.taktik.icure.fhir.entities.r4.claim.ClaimItem
import org.taktik.icure.fhir.entities.r4.claim.ClaimSupportingInfo
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.coding.Coding
import org.taktik.icure.fhir.entities.r4.count.Count
import org.taktik.icure.fhir.entities.r4.humanname.HumanName
import org.taktik.icure.fhir.entities.r4.identifier.Identifier
import org.taktik.icure.fhir.entities.r4.messageheader.MessageHeader
import org.taktik.icure.fhir.entities.r4.messageheader.MessageHeaderDestination
import org.taktik.icure.fhir.entities.r4.messageheader.MessageHeaderSource
import org.taktik.icure.fhir.entities.r4.organization.Organization
import org.taktik.icure.fhir.entities.r4.parameters.Parameters
import org.taktik.icure.fhir.entities.r4.parameters.ParametersParameter
import org.taktik.icure.fhir.entities.r4.patient.Patient
import org.taktik.icure.fhir.entities.r4.period.Period
import org.taktik.icure.fhir.entities.r4.practitioner.Practitioner
import org.taktik.icure.fhir.entities.r4.practitionerrole.PractitionerRole
import org.taktik.icure.fhir.entities.r4.reference.Reference
import org.taktik.icure.fhir.entities.r4.servicerequest.ServiceRequest

class AgreementServiceUtils {
    fun getPractitionerRole(practitionerRoleId: String, hcpNihii: String, hcpFirstName: String, hcpLastName: String): PractitionerRole {
        return PractitionerRole().apply {
            id = "PractitionerRole$practitionerRoleId"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-practitionerrole")
            )
            practitioner = Reference().apply {
                getPractitioner(practitionerRoleId, hcpNihii, hcpFirstName, hcpLastName)
            }
            code = listOf(
                CodeableConcept().apply {
                    Coding(
                        system = "https://www.ehealth.fgov.be/standards/fhir/core/CodeSystem/cd-hcparty",
                        code = "persphysiotherapist"
                    )
                }
            )
        }
    }

    fun getPractitioner(practitionerId: String, hcpNihii: String, hcpFirstName: String, hcpLastName: String): Practitioner {
        return Practitioner().apply {
            id = "Practitioner$practitionerId"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-practitioner")
            )
            identifier = listOf(
                Identifier().apply {
                    system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/nihdi"
                    value = hcpNihii
                }
            )
            name = listOf(
                HumanName(
                    family = hcpLastName,
                    given = listOf(hcpFirstName)
                )
            )
        }
    }

    fun getCodableConcept(system: String, code: String): CodeableConcept{
        return CodeableConcept().apply {
            coding = listOf(
                Coding(
                    system = system,
                    code = code
                )
            )
        }
    }

    fun getClaim(
        claimId: String,
        claimStatus: String,
        subTypeCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        hcpNihii: String,
        hcpFirstName: String,
        hcpLastName: String,
        agreementStartDate: DateTime,
        insuranceRef: String,
        pathologyCode: String,
        pathologyStartDate: DateTime?,
        patientSsin: String?,
        io: String?,
        ioMembership: String?
    ): Claim {
        val serviceRequestRef = Reference().apply { getServiceRequest("ServiceRequest/ServiceRequest1", "", "", 1f, patientFirstName, patientLastName, patientGender, hcpNihii, hcpFirstName, hcpLastName, patientSsin, io, ioMembership) }
        return Claim(
            patient = Reference().apply { getPatient(patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership) },
            priority = getCodableConcept("http://terminology.hl7.org/CodeSystem/processpriority", "stat"),
            provider = Reference().apply { getPractitioner("", hcpNihii, hcpFirstName, hcpLastName) },
            type = getCodableConcept("http://terminology.hl7.org/CodeSystem/claim-type", "professional")
        ).apply {
            id = "Claim$claimId"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementclaim-kine")
            )
            status = claimStatus
            subType = getCodableConcept("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/agreement-types", subTypeCode)
            use = "preauthorization"
            billablePeriod = getBillablePeriod(agreementStartDate!!)
            created = DateTime().toString()
            enterer = Reference().apply { getPractitioner("", hcpNihii, hcpFirstName, hcpLastName) }
            referral = serviceRequestRef
            insurance = getInsurance(insuranceRef, "")
            supportingInfo = listOf(getSupportingInfo(serviceRequestRef))
            item = listOf(getServicedDateItem(pathologyStartDate!!, pathologyCode), getCodeItem(pathologyCode))
        }
    }

    fun getSupportingInfo(serviceRequest: Reference): ClaimSupportingInfo {
        val category = CodeableConcept().apply {
            Coding(
                system = "http://terminology.hl7.org/CodeSystem/claiminformationcategory",
                code = "info"
            )
        }
        return ClaimSupportingInfo(
            category = category
        ).apply {
            sequence = 1
            valueReference = serviceRequest
        }
    }

    fun getOrganization(organizationId: String, orgNihii: String, organizationType: String): Organization {
        return Organization().apply {
            id = "Organization$organizationId"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-organization")
            )
            identifier = listOf(
                Identifier().apply {
                    system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/nihdi"
                    value = orgNihii
                }
            )
            type = listOf(
                CodeableConcept().apply {
                    Coding(
                        system = "https://www.ehealth.fgov.be/standards/fhir/core/CodeSystem/cd-hcparty",
                        code = organizationType
                    )
                }
            )
        }
    }

    fun getPatient(patientFirstName: String, patientLastName: String, gender: String, patientSsin: String?, io: String ?, ioMembership: String?): Patient {
        return Patient(
            id = "Patient1",
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-patient")
            ),
            identifier = listOf(
                Identifier().apply {
                    when{
                        patientSsin != null && ioMembership == null -> {
                            system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/ssin"
                            value = patientSsin
                        }
                        patientSsin == null && ioMembership != null && io != null -> {
                            system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancymembership"
                            value = ioMembership
                            assigner = Reference(identifier = Identifier().apply {
                                system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancenumber"
                                value = io
                            })
                        }
                        patientSsin != null && ioMembership != null -> {
                            system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/ssin"
                            value = patientSsin
                        }
                    }
                }),
            name = listOf(
                HumanName(
                    family = patientLastName,
                    given = listOf(patientFirstName)
                )
            ),
            gender = gender
        )
    }

    fun getServiceRequest(serviceRequestId: String, data: String, annexId: String, quantity: Float, patientFirstName: String, patientLastName: String, gender: String, hcpNihii: String, hcpFirstName: String, hcpLastName: String, patientSsin: String?, io: String?, ioMembership: String?): ServiceRequest {
        val patient = getPatient(patientFirstName!!, patientLastName!!, gender!!, patientSsin, io, ioMembership)
        return ServiceRequest(subject = Reference().apply { patient }).apply {
            id = "ServiceRequest$serviceRequestId"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementservicerequest")
            )
            contained = getContained(data, annexId)
            identifier = listOf(
                Identifier().apply {
                    system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/uhmep"
                    value = "Prescription id"
                }
            )
            status = "active"
            intent = "order"
            category = listOf(
                CodeableConcept().apply {
                    Coding(
                        system = "http://snomed.info/sct",
                        code = "91251008",
                        display = "Physical therapy procedure"
                    )
                }
            )
            code = CodeableConcept().apply {
                Coding(
                    system = "http://snomed.info/sct",
                    code = "91251008",
                    display = "Physical therapy procedure"
                )
            }
            quantityQuantity = Count().apply {
                value = quantity
            }
            subject = Reference().apply {
                getPatient(patientFirstName!!, patientLastName!!, gender!!, patientSsin, io, ioMembership)
            }
            authoredOn = DateTime().toString()
            requester = Reference().apply {
                getPractitionerRole("", hcpNihii, hcpFirstName, hcpLastName)
            }
            supportingInfo = listOf<Reference>().apply {
                getContained(data, annexId)
            }
        }
    }

    fun getContained(data: String, containedId: String): List<Binary>{
        return listOf(
            Binary(
                contentType = "application/pdf",
                data = data,
                id = "annexSR$containedId"
            )
        )
    }

    fun getParameters(parameterId: String, parameterNames: Array<String>,
                              agreementTypes: String,
                              startDate: DateTime?,
                              endDate: DateTime?,
                              patientFirstName: String?,
                              patientLastName: String?,
                              patientGender: String?,
                              patientSsin: String?,
                              io: String?,
                              ioMembership: String?): Parameters {
        val param = mutableListOf<ParametersParameter>();
        for (parameterName in parameterNames){
            param.add(getParameter(parameterName, agreementTypes, startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership))
        }
        return Parameters().apply {
            id = "Parameters$parameterId"
            parameter = param
        }
    }

    fun getParameter(parameterName: String,
                             agreementTypes: String?,
                             startDate: DateTime?,
                             endDate: DateTime?,
                             patientFirstName: String?,
                             patientLastName: String?,
                             patientGender: String?,
                             patientSsin: String?,
                             io: String?,
                             ioMembership: String?
    ): ParametersParameter{
        return ParametersParameter().apply {
            name = parameterName
            when{
                parameterName == "resourceType" -> valueString = "Claim"
                parameterName == "patient" -> valueReference = Reference().apply {
                    getPatient(patientFirstName!!, patientLastName!!, patientGender!!, patientSsin, io, ioMembership)
                }
                parameterName == "use" -> valueCode = "preauthorization"
                parameterName == "subType" -> valueCoding = Coding().apply {
                    system = "https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/agreement-types"
                    code = agreementTypes
                }
                parameterName == "preAuthPeriod" -> valuePeriod = Period().apply {
                    start = startDate.toString()
                    end = endDate.toString()
                }
            }
        }
    }

    fun getInsurance(insuranceRef: String, display: String): List<ClaimInsurance>{
        return listOf(
            ClaimInsurance(
                sequence = 1,
                focal = true,
                coverage = Reference(
                    display = display
                ),
                preAuthRef = listOf(insuranceRef)
            )
        )
    }

    fun getBillablePeriod(startDate: DateTime): Period{
        return Period(
            start = startDate.toString()
        )
    }

    fun getServicedDateItem(pathologyDate: DateTime, pathologyCode: String): ClaimItem {
        return ClaimItem(
            productOrService = CodeableConcept(
                coding = listOf(
                    Coding(
                        system = "https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/nihdi-physiotherapy-pathologysituationcode",
                        code = pathologyCode
                    )
                )
            ),
            servicedDate = pathologyDate.toString()
        )
    }

    fun getCodeItem(code: String): ClaimItem {
        return ClaimItem(
            productOrService = getCodableConcept("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/nihdi-physiotherapy-pathologysituationcode", code)
        )
    }

    fun getAdditionalNotes(additionalNotes: String): ClaimSupportingInfo{
        return ClaimSupportingInfo(
            sequence = 2,
            category = CodeableConcept().apply {
                coding = listOf(Coding().apply {
                    system = "http://terminology.hl7.org/CodeSystem/claiminformationcategory"
                    code = "info"
                })
            },
            valueString = additionalNotes
        )
    }

    fun getPrescriptionInfos(quantity: Int, document: String, documentId: String): ServiceRequest{
        return ServiceRequest(
            identifier = listOf(
                Identifier(
                    value = "Prescription id",
                    system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/uhmep"
                )
            ),
            status = "active",
            intent = "order",
            category = listOf(
                CodeableConcept(
                    coding = listOf(
                        Coding(
                            system = "http://snomed.info/sct",
                            code = "procedure snomed",
                            display = "?????"
                        )
                    )
                )
            ),
            code = CodeableConcept(
                coding = listOf(
                    Coding(
                        system = "http://snomed.info/sct",
                        code = "procedure snomed",
                        display = "?????"
                    )
                )
            ),
            quantityQuantity = Count().apply {
                value = quantity.toFloat()
            },
            subject = Reference().apply {
                reference = "Patient/Patient1"
            },
            authoredOn = DateTime.now().toString("yyyy-MM-dd"),
            requester = Reference().apply {
                PractitionerRole(
                    practitioner = Reference().apply {
                        reference = "PractitionerRole/PractitionerRole2"
                    }
                )
            },
            supportingInfo = listOf(Reference().apply {
                Binary(
                    contentType = "application/pdf",
                    data = document,
                    id = documentId
                )
            })
        )
    }

    fun getMessageHeader(claim: Claim, messageEventSystem: String, messageEventsCode: String): MessageHeader {
        return MessageHeader(
            eventCoding = Coding(
                system = messageEventSystem,
                code = messageEventsCode
            ),
            source = MessageHeaderSource()
        ).apply {
            id = "generate id"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementmessageheader")
            )

            destination = listOf(
                MessageHeaderDestination(
                    endpoint = "MyCareNet",
                    name = "MyCareNet"
                )
            )
            focus = listOf(
                Reference().apply {
                    claim
                }
            )
        }
    }

    fun getBundle(
        requestType: AgreementServiceImpl.RequestTypeEnum,
        claim: Claim,
        messageEventSystem: String,
        messageEventCode: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        patientSsin: String?,
        patientIo: String?,
        patientIoMembership: String?,
        hcpNihii: String,
        hcpFirstName: String,
        hcpLastName: String,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        parameterNames: Array<String>?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?
    ): Bundle {
        return Bundle().apply {
            id = "Bundle1"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementdemand")
            )
            type = "message"
            timestamp = DateTime().toString()
            entry = listOf<BundleEntry>().apply {
                BundleEntry(
                    resource = getMessageHeader(claim, messageEventSystem, messageEventCode),
                    fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                )
                BundleEntry(
                    resource = getPatient(patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership),
                    fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                )
                BundleEntry(
                    resource = getPractitionerRole("1", hcpNihii, hcpFirstName, hcpLastName),
                    fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                )
                BundleEntry(
                    resource = getPractitioner("1", hcpNihii, hcpFirstName, hcpLastName),
                    fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                )
                BundleEntry(
                    resource = getOrganization("1", orgNihii!!, organizationType!!),
                    fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                )
                when{
                    //Claim 1
                    requestType != AgreementServiceImpl.RequestTypeEnum.CONSULT_LIST -> BundleEntry(
                        resource = claim,
                        fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                    )
                    //Parameters 1
                    requestType == AgreementServiceImpl.RequestTypeEnum.CONSULT_LIST ->   BundleEntry(
                        resource = getParameters("1", parameterNames!!, agreementType!!, agreementStartDate, agreementEndDate, hcpNihii, hcpFirstName, hcpLastName, patientSsin, patientIo, patientIoMembership),
                        fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                    )
                    //Service Request 1
                    requestType != AgreementServiceImpl.RequestTypeEnum.CANCEL && requestType != AgreementServiceImpl.RequestTypeEnum.CONSULT_LIST ->
                        BundleEntry(
                            resource = getServiceRequest("1", annex1!!, "1", numberOfSessionForAnnex1!!, patientFirstName, patientLastName, patientGender, hcpNihii, hcpFirstName, hcpLastName, patientSsin, patientIo, patientIoMembership),
                            fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                        )
                    //Service request 2
                    requestType == AgreementServiceImpl.RequestTypeEnum.ARGUE || requestType == AgreementServiceImpl.RequestTypeEnum.ASK ->
                        BundleEntry(
                            resource = getServiceRequest("2", annex2!!, "2", numberOfSessionForAnnex2!!, patientFirstName, patientLastName, patientGender, hcpNihii, hcpFirstName, hcpLastName, patientSsin, patientIo, patientIoMembership),
                            fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                        )
                }
            }
        }
    }
}
