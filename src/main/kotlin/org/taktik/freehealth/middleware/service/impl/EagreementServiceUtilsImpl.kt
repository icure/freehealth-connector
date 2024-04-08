package org.taktik.freehealth.middleware.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory
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
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
        requestType: EagreementServiceImpl.RequestTypeEnum,
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
            provider = Reference().apply { reference = "PractitionerRole/PractitionerRole1" },
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
            created = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"))
            enterer = Reference().apply { reference = "PractitionerRole/PractitionerRole1"}
            referral = Reference().apply {
                reference = "ServiceRequest/ServiceRequest1"
            }
            insurance = listOf(getInsurance(requestType, insuranceRef, "use of mandatory insurance coverage, no further details provided here."))
            if (requestType == EagreementServiceImpl.RequestTypeEnum.ASK) {
                supportingInfo = listOf(
                    getSupportingInfo(1, "attachment", "physiotherapist-report", null, null, "QW5uZXhlIGlubGluZSwgYmFzZTY0ZWQ=", "nom/description de l'annexe", "application/pdf"),
                    getSupportingInfo(2, "info", null, null, "additional Information", null, null, null)
                    //getSupportingInfo(3, "info", null, "ServiceRequest/ServiceRequest2", null, null, null, null)
                )
            }

            item = listOf(getServicedDateItem(requestType, pathologyStartDate!!, pathologyCode, 1))
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
            if (code != null) this.code = code
            when{
                //code != null -> this.code = code
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
                            system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancymembership"
                            value = ioMembership
                            assigner = Reference(identifier = Identifier().apply {
                                system = "https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancenumber"
                                value = io
                            })
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

    override fun getParameters(parameterId: String,
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
        param.add(getParameter("resourceType", agreementTypes, startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership))
        param.add(getParameter("patient", agreementTypes, startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership))
        param.add(getParameter("use", agreementTypes, startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership))
        param.add(getParameter("subType", agreementTypes, startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership))
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
                    code = "physiotherapy"
                }
                parameterName == "preAuthPeriod" -> valuePeriod = Period().apply {
                    start = startDate.toString()
                    end = endDate.toString()
                }
            }
        }
    }

    override fun getInsurance(requestType: EagreementServiceImpl.RequestTypeEnum, insuranceRef: String, display: String): ClaimInsurance{
        return ClaimInsurance(
            sequence = 1,
            focal = true,
            coverage = Reference(
                display = display
            )
        ).apply {
            if(requestType != EagreementServiceImpl.RequestTypeEnum.EXTEND) preAuthRef = listOf(insuranceRef)
        }
    }

    override fun getBillablePeriod(startDate: DateTime): Period{
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        return Period(
            start = formatter.print(startDate)
        )
    }

    override fun getServicedDateItem(requestType: EagreementServiceImpl.RequestTypeEnum, pathologyDate: DateTime, pathologyCode: String, sequenceNumber: Int): ClaimItem {
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
            )
        ).apply {
            if(requestType != EagreementServiceImpl.RequestTypeEnum.EXTEND) servicedDate = formatter.print(pathologyDate)
        }
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

    override fun getMessageHeader(messageFocusReference: String, messageEventSystem: String, messageEventsCode: String, practitionerRole1UUID: String): MessageHeader {
        return MessageHeader(
            eventCoding = Coding(
                system = messageEventSystem,
                code = messageEventsCode
            ),
            source = MessageHeaderSource(endpoint = "urn:uuid:$practitionerRole1UUID")
        ).apply {
            id = IdGeneratorFactory.getIdGenerator("uuid").generateId()
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-messageheader")
            )

            destination = listOf(
                MessageHeaderDestination(
                    endpoint = "MyCareNet",
                    name = "MyCareNet"
                )
            )
            focus = listOf(
                Reference().apply {
                    reference = messageFocusReference
                }
            )
            sender = Reference().apply {
                reference = "PractitionerRole/PractitionerRole1" //todo organization or praticioner
            }
        }
    }

    override fun getBundleJSON(
        requestType: EagreementServiceImpl.RequestTypeEnum,
        messageFocusReference: String,
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
        hcp2Nihii: String?,
        hcp2FirstName: String?,
        hcp2LastName: String?,
        orgNihii: String?,
        organizationType: String?,
        annex1: String?,
        annex2: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForAnnex1: Float?,
        numberOfSessionForAnnex2: Float?,
        insuranceRef: String?,
        pathologyCode: String,
        pathologyStartDate: DateTime?
    ): JsonObject? {
        val uuidGenerator = IdGeneratorFactory.getIdGenerator("uuid")
        val practitionerRole1UUID = uuidGenerator.generateId()
        var entries = mutableListOf<BundleEntry>()

        val mapper = ObjectMapper()
        mapper.registerModule(KotlinModule())
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE)

        val requestBundle = Bundle().apply {
            id = "Bundle1"
            meta = Meta(
                profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementdemand")
            )
            type = "message"
            timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"))
            entry = entries
        }
        when (requestType) {
            EagreementServiceImpl.RequestTypeEnum.ASK -> {
                requestBundle.meta = Meta(
                    profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementdemand")
                )
            }
            EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST -> {
                requestBundle.meta = Meta(
                    profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementconsult")
                )
            }
            EagreementServiceImpl.RequestTypeEnum.EXTEND -> {
                requestBundle.meta = Meta(
                    profile = listOf("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementdemand")
                )
            }
        }

        val requestJson = mapper.writeValueAsString(requestBundle)
        val gson = JsonParser().parse(requestJson).asJsonObject;

        val messageHeader = JsonObject()
        messageHeader.addProperty("fullUrl", "urn:uuid:" + uuidGenerator.generateId())
        messageHeader.add(
            "resource",
            JsonParser().parse(
                mapper.writeValueAsString(
                    getMessageHeader(
                        messageFocusReference,
                        messageEventSystem,
                        messageEventCode,
                        practitionerRole1UUID
                    )
                )
            ).asJsonObject
        )
        gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(messageHeader)

        if (hcpNihii != null && hcpFirstName != null && hcpLastName != null) {
            val practitioner1 = JsonObject()
            practitioner1.addProperty("fullUrl", "urn:uuid:" + uuidGenerator.generateId())
            practitioner1.add(
                "resource",
                JsonParser().parse(
                    mapper.writeValueAsString(
                        getPractitioner(
                            "1",
                            hcpNihii,
                            hcpFirstName,
                            hcpLastName
                        )
                    )
                ).asJsonObject
            )
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(practitioner1)
        }

        if (hcp2Nihii != null && hcp2FirstName != null && hcp2LastName != null) {
            val practitioner2 = JsonObject()
            practitioner2.addProperty("fullUrl", "urn:uuid:" + uuidGenerator.generateId())
            practitioner2.add(
                "resource",
                JsonParser().parse(
                    mapper.writeValueAsString(
                        getPractitioner(
                            "2",
                            hcp2Nihii,
                            hcp2FirstName,
                            hcp2LastName
                        )
                    )
                ).asJsonObject
            )
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(practitioner2)
        }

        if (patientFirstName != null && patientLastName != null && patientGender != null && (patientSsin != null || patientIo != null || patientIoMembership != null)) {
            val patient = JsonObject()
            patient.addProperty("fullUrl", "urn:uuid:" + uuidGenerator.generateId())
            patient.add(
                "resource",
                JsonParser().parse(
                    mapper.writeValueAsString(
                        getPatient(
                            patientFirstName,
                            patientLastName,
                            patientGender,
                            patientSsin,
                            patientIo,
                            patientIoMembership
                        )
                    )
                ).asJsonObject
            )
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(patient)
        }

        if (hcpNihii != null) {
            val practitionerRole1 = JsonObject()
            practitionerRole1.addProperty("fullUrl", "urn:uuid:$practitionerRole1UUID")
            practitionerRole1.add(
                "resource",
                JsonParser().parse(
                    mapper.writeValueAsString(
                        getPractitionerRole(
                            "1",
                            "persphysiotherapist"
                        )
                    )
                ).asJsonObject
            )
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(practitionerRole1)
        }

        if (hcp2Nihii != null) {
            val practitionerRole2 = JsonObject()
            practitionerRole2.addProperty("fullUrl", "urn:uuid:" + uuidGenerator.generateId())
            practitionerRole2.add(
                "resource",
                JsonParser().parse(mapper.writeValueAsString(getPractitionerRole("2", "persphysician"))).asJsonObject
            )
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(practitionerRole2)
        }

        /*if (orgNihii != null && organizationType != null) {
            val organization = JsonObject()
            organization.addProperty("fullUrl" , "urn:uuid:"+uuidGenerator.generateId())
            organization.add("resource", JsonParser().parse(mapper.writeValueAsString(getOrganization("1", orgNihii, organizationType))).asJsonObject)
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(organization)
        }*/

        //Ask
        /*if (requestType == EagreementServiceImpl.RequestTypeEnum.ASK) {
            val askParameter = JsonObject()
            askParameter.addProperty("fullUrl" , "urn:uuid:"+uuidGenerator.generateId())
            askParameter.add("resource", JsonParser().parse(mapper.writeValueAsString(getParameters("1", arrayOf("ask"), agreementType!!, agreementStartDate, agreementEndDate, hcpNihii, hcpFirstName, hcpLastName, patientSsin, patientIo, patientIoMembership))).asJsonObject)
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(askParameter)
        }*/
        //Parameters 1
        if (requestType == EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST) {
            val parameter1 = JsonObject()
            parameter1.addProperty("fullUrl", "urn:uuid:" + uuidGenerator.generateId())
            parameter1.add(
                "resource",
                JsonParser().parse(
                    mapper.writeValueAsString(
                        getParameters(
                            "1",
                            agreementType!!,
                            agreementStartDate,
                            agreementEndDate,
                            hcpNihii,
                            hcpFirstName,
                            hcpLastName,
                            patientSsin,
                            patientIo,
                            patientIoMembership
                        )
                    )
                ).asJsonObject
            )
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(parameter1)
        }
        //Service Request 1
        if (requestType != EagreementServiceImpl.RequestTypeEnum.CANCEL && requestType != EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST && requestType != EagreementServiceImpl.RequestTypeEnum.EXTEND) {
            val serviceRequest1 = JsonObject()
            serviceRequest1.addProperty("fullUrl" , "urn:uuid:" + uuidGenerator.generateId())
            serviceRequest1.add("resource", JsonParser().parse(mapper.writeValueAsString(getServiceRequest("1", "", "QW5uZXhlIGlubGluZSwgYmFzZTY0ZWQ=", "1", numberOfSessionForAnnex1!!, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership))).asJsonObject)
            serviceRequest1.getAsJsonObject("resource").getAsJsonObject("ServiceRequest").add("contained", JsonParser().parse(mapper.writeValueAsString( Binary(
                contentType = "application/pdf",
                data = "QW5uZXhlIGlubGluZSwgYmFzZTY0ZWQ=",
                id = "annexSR1"
            ))).asJsonObject)
            serviceRequest1.getAsJsonObject("resource").getAsJsonObject("ServiceRequest").getAsJsonObject("quantityQuantity").addProperty("value", numberOfSessionForAnnex1.toInt())
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(serviceRequest1)
        }
        //Service request 2
        /*if (requestType == EagreementServiceImpl.RequestTypeEnum.ARGUE || requestType == EagreementServiceImpl.RequestTypeEnum.ASK) {
            val serviceRequest2 = JsonObject()
            serviceRequest2.addProperty("fullUrl" , "urn:uuid:"+uuidGenerator.generateId())
            serviceRequest2.add("resource", JsonParser().parse(mapper.writeValueAsString(getServiceRequest("2", "BE8779879789", "ceci devrait etre un pdf", "2", numberOfSessionForAnnex2!!, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership))).asJsonObject)
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(serviceRequest2)
        }*/

        //Claim 1
        if (requestType == EagreementServiceImpl.RequestTypeEnum.ASK || requestType == EagreementServiceImpl.RequestTypeEnum.CANCEL || requestType == EagreementServiceImpl.RequestTypeEnum.EXTEND) {
            val claim = this.getClaim(
                requestType,
                claimId = "1",
                claimStatus = "active",
                subTypeCode = "physiotherapy-fb",
                agreementStartDate = DateTime(),
                insuranceRef = insuranceRef!!,
                pathologyCode = pathologyCode,
                pathologyStartDate = pathologyStartDate,
                providerType = ""
            )
            val parameter = JsonObject()
            parameter.addProperty("fullUrl" , "urn:uuid:"+uuidGenerator.generateId())
            parameter.add("resource", JsonParser().parse(mapper.writeValueAsString(claim)).asJsonObject)
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(parameter)
        }

        return gson
    }
}
