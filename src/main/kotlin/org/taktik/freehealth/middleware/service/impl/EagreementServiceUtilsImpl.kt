package org.taktik.freehealth.middleware.service.impl

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.taktik.freehealth.middleware.service.EagreementServiceUtils
import org.taktik.icure.fhir.entities.r4.Meta
import org.taktik.icure.fhir.entities.r4.attachment.Attachment
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

class EagreementServiceUtilsImpl(): EagreementServiceUtils {
    override fun getPractitionerRole(practitionerRoleId: String, practitionerRole: String): PractitionerRole {
        return PractitionerRole().apply {
            id = "PractitionerRole$practitionerRoleId"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-practitionerrole")
            )
            practitioner = Reference().apply {
                reference = "Practitioner/Practitioner$practitionerRoleId"
            }
            code = listOf(
                CodeableConcept().apply {
                    coding = listOf(
                        Coding(
                            system = "https://www.ehealth.fgov.be/standards/fhir/core/CodeSystem/cd-hcparty",
                            code = practitionerRole
                        ))
                }
            )
        }
    }

    override fun getPractitioner(practitionerId: String, hcpNihii: String, hcpFirstName: String, hcpLastName: String): Practitioner {
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

    override fun getCodableConcept(system: String, code: String): CodeableConcept{
        return CodeableConcept().apply {
            coding = listOf(
                Coding(
                    system = system,
                    code = code
                )
            )
        }
    }

    override fun getClaim(
        claimId: String,
        claimStatus: String,
        subTypeCode: String,
        agreementStartDate: DateTime,
        insuranceRef: String,
        pathologyCode: String,
        pathologyStartDate: DateTime?,
        providerType: String
    ): Claim {
        return Claim(
            patient = Reference().apply { reference = "Patient/Patient1" },
            priority = getCodableConcept("http://terminology.hl7.org/CodeSystem/processpriority", "stat"),
            provider = Reference().apply { reference = providerType },
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
            enterer = Reference().apply { reference = "PractitionerRole/PractitionerRole1"}
            referral = Reference().apply {
                reference = "ServiceRequest/ServiceRequest1"
            }
            insurance = listOf(getInsurance(insuranceRef, ""))
            supportingInfo = listOf(
                getSupportingInfo(1, "attachment", "functional-report", null, null, "QW5uZXhlIGlubGluZSwgYmFzZTY0ZWQ=", "nom/description de l'annexe", "application/pdf"),
                getSupportingInfo(2, "info", null, null, "additional Information", null, null, null),
                getSupportingInfo(3, "info", null, "ServiceRequest/ServiceRequest2", null, null, null, null)
            )

            item = listOf(getServicedDateItem(pathologyStartDate!!, pathologyCode, 1), getCodeItem(pathologyCode))
        }
    }

    override fun getSupportingInfo(sequenceNumber: Int, claimInformationCategory: String, annexTypeCode: String?, valueReference: String?, valueString: String?, valueAttachmentData: String?, valueAttachmentTitle: String?, valueAttachmentContentType: String?): ClaimSupportingInfo {
        var code: CodeableConcept? = null
        val category = CodeableConcept().apply {
            coding = listOf(Coding(
                system = "http://terminology.hl7.org/CodeSystem/claiminformationcategory",
                code = claimInformationCategory
            ))
        }

        if(!annexTypeCode.isNullOrEmpty()){
            code = CodeableConcept().apply {
                coding = listOf(Coding(
                    system = "https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/annex-types",
                    code = annexTypeCode
                ))
            }
        }


        return ClaimSupportingInfo(
            category = category
        ).apply {
            when{
                code != null -> this.code = code
                !valueReference.isNullOrEmpty() -> this.valueReference = Reference().apply {
                    reference = valueReference
                }
                !valueString.isNullOrEmpty() -> this.valueString = valueString
                !valueAttachmentData.isNullOrEmpty() -> this.valueAttachment = Attachment().apply {
                    contentType = valueAttachmentContentType
                    data = valueAttachmentData
                    title = valueAttachmentTitle
                }
            }

            sequence = sequenceNumber

        }
    }

    override fun getOrganization(organizationId: String, orgNihii: String, organizationType: String): Organization {
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
                    coding = listOf(
                        Coding(
                            system = "https://www.ehealth.fgov.be/standards/fhir/core/CodeSystem/cd-hcparty",
                            code = organizationType
                        )
                    )
                }
            )
        }
    }

    override fun getPatient(patientFirstName: String, patientLastName: String, gender: String, patientSsin: String?, io: String ?, ioMembership: String?): Patient {
        return Patient(
            id = "Patient1",
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-patient")
            ),
            identifier = listOf(
                Identifier().apply {
                    when{
                        !patientSsin.isNullOrEmpty() && ioMembership.isNullOrEmpty() -> {
                            system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/ssin"
                            value = patientSsin
                        }
                        patientSsin.isNullOrEmpty() && !ioMembership.isNullOrEmpty() && !io.isNullOrEmpty() -> {
                            system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancymembership"
                            value = ioMembership
                            assigner = Reference(identifier = Identifier().apply {
                                system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancenumber"
                                value = io
                            })
                        }
                        !patientSsin.isNullOrEmpty() && !ioMembership.isNullOrEmpty() -> {
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

    override fun getServiceRequest(serviceRequestId: String, prescriptionId: String, data: String, annexId: String, quantity: Float, patientFirstName: String, patientLastName: String, gender: String, patientSsin: String?, io: String?, ioMembership: String?): ServiceRequest {
        val patient = getPatient(patientFirstName!!, patientLastName!!, gender!!, patientSsin, io, ioMembership)
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        return ServiceRequest(subject = Reference().apply { patient }).apply {
            id = "ServiceRequest$serviceRequestId"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementservicerequest")
            )
            contained = getContained(data, annexId)
            identifier = listOf(
                Identifier().apply {
                    system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/uhmep"
                    value = prescriptionId
                }
            )
            status = "active"
            intent = "order"
            category = listOf(
                CodeableConcept().apply {
                    coding = listOf(
                        Coding(
                            system = "http://snomed.info/sct",
                            code = "91251008",
                            display = "Physical therapy procedure"
                        )
                    )
                }
            )
            code = CodeableConcept().apply {
                coding = listOf(
                    Coding(
                        system = "http://snomed.info/sct",
                        code = "91251008",
                        display = "Physical therapy procedure"
                    )
                )
            }
            quantityQuantity = Count().apply {
                value = quantity
            }
            subject = Reference().apply {
                reference = "Patient/Patient1"
            }
            authoredOn = formatter.print(DateTime())
            requester = Reference().apply {
                reference = "PractitionerRole/PractitionerRole2"
            }
            supportingInfo = listOf(Reference().apply {
                reference = "#annexSR$annexId"
            })
        }
    }

    override fun getContained(data: String, containedId: String): List<Binary>{
        return listOf(
            Binary(
                contentType = "application/pdf",
                data = data,
                id = "annexSR$containedId"
            )
        )
    }

    override fun getParameters(parameterId: String, parameterNames: Array<String>,
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

    override fun getParameter(parameterName: String,
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
                parameterName == "resourceType" -> valueString = "ClaimResponse"
                parameterName == "patient" -> valueReference = Reference().apply {
                    reference = "Patient/Patient1"
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

    override fun getInsurance(insuranceRef: String, display: String): ClaimInsurance{
        return ClaimInsurance(
            sequence = 1,
            focal = true,
            coverage = Reference(
                display = display
            ),
            preAuthRef = listOf(insuranceRef)
        )
    }

    override fun getBillablePeriod(startDate: DateTime): Period{
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        return Period(
            start = formatter.print(startDate)
        )
    }

    override fun getServicedDateItem(pathologyDate: DateTime, pathologyCode: String, sequenceNumber: Int): ClaimItem {
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        return ClaimItem(
            sequence = sequenceNumber,
            productOrService = CodeableConcept(
                coding = listOf(
                    Coding(
                        system = "https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/nihdi-physiotherapy-pathologysituationcode",
                        code = pathologyCode
                    )
                )
            ),
            servicedDate = formatter.print(pathologyDate)
        )
    }

    override fun getCodeItem(code: String): ClaimItem {
        return ClaimItem(
            productOrService = getCodableConcept("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/nihdi-physiotherapy-pathologysituationcode", code)
        )
    }

    override fun getAdditionalNotes(additionalNotes: String): ClaimSupportingInfo{
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

    override fun getPrescriptionInfos(quantity: Int, document: String, documentId: String): ServiceRequest{
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

    override fun getMessageHeader(claim: Claim, messageEventSystem: String, messageEventsCode: String): MessageHeader {
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
            sender = Reference().apply {
                reference = "PractitionerRole/PractitionerRole1" //todo organization or praticioner
            }
        }
    }


    override fun getBundle(
        requestType: EagreementServiceImpl.RequestTypeEnum,
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
    ): Bundle? {
        var entries = mutableListOf<BundleEntry>()
        entries.add(BundleEntry(
            resource = getMessageHeader(claim, messageEventSystem, messageEventCode),
            fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
        ))
        entries.add( BundleEntry(
            resource = getPatient(patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership),
            fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
        ))
        entries.add(   BundleEntry(
            resource = getPractitionerRole("1", "persphysiotherapist"),
            fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
        ))
        entries.add(BundleEntry(
            resource = getPractitioner("1", hcpNihii, hcpFirstName, hcpLastName),
            fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
        ))
        if (orgNihii != null && organizationType != null) {
            entries.add(BundleEntry(
                resource = getOrganization("1", orgNihii, organizationType),
                fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
            ))
        }
        when{
            //Claim 1
            requestType != EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST -> entries.add(BundleEntry(
                resource = claim,
                fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
            ))
            //Parameters 1
            requestType == EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST ->   entries.add(BundleEntry(
                resource = getParameters("1", parameterNames!!, agreementType!!, agreementStartDate, agreementEndDate, hcpNihii, hcpFirstName, hcpLastName, patientSsin, patientIo, patientIoMembership),
                fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
            ))
            //Service Request 1
            requestType != EagreementServiceImpl.RequestTypeEnum.CANCEL && requestType != EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST ->
                entries.add(BundleEntry(
                    resource = getServiceRequest("1", "BE8779879789", annex1!!, "1", numberOfSessionForAnnex1!!, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership),
                    fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                ))
            //Service request 2
            requestType == EagreementServiceImpl.RequestTypeEnum.ARGUE || requestType == EagreementServiceImpl.RequestTypeEnum.ASK ->
                entries.add(BundleEntry(
                    resource = getServiceRequest("2", "BE8779879789", annex2!!, "2", numberOfSessionForAnnex2!!, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership),
                    fullUrl = "https://www.hl7.org/fhir/bundle-definitions.html#Bundle.entry.fullUrl"
                ))
        }

        return Bundle().apply {
            id = "Bundle1"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementdemand")
            )
            type = "message"
            timestamp = DateTime().toString()
            entry = entries
        }
    }
}
