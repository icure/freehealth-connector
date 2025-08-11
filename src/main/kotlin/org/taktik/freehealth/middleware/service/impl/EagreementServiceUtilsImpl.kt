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
import org.taktik.freehealth.middleware.web.controllers.EagreementController
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

    enum class MetaProfileEnum(val metaProfile: String){
        BE_MESSAGEHEADER("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-messageheader"),
        BE_AGREEMENTCONSULT("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementconsult"),
        BE_AGREEMENTDEMAND("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementdemand"),
        BE_PRACTIONNERROLE("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-practitionerrole"),
        BE_PRACTIONNER("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-practitioner"),
        BE_AGREEMENT_CLAIM_KINE("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementclaim-kine"),
        BE_ORGANIZATION("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-organization"),
        BE_PATIENT("https://www.ehealth.fgov.be/standards/fhir/core/StructureDefinition/be-patient"),
        BE_EAGREEMENTSERVICEREQUEST("https://www.ehealth.fgov.be/standards/fhir/mycarenet/StructureDefinition/be-eagreementservicerequest"),
    }

    enum class CodingSystemEnum(val codingSystem: String){
        CD_HCPARTY("https://www.ehealth.fgov.be/standards/fhir/core/CodeSystem/cd-hcparty"),
        NIHDI("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/nihdi"),
        CLAIM_INFORMATION_CATEGORY("http://terminology.hl7.org/CodeSystem/claiminformationcategory"),
        ANNEX_TYPES("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/annex-types"),
        INSURANCE_NUMBER("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancenumber"),
        INSURANCY_MEMBERSHIP("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/insurancymembership"),
        SSIN("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/ssin"),
        SCT("http://snomed.info/sct"),
        UHMEP("https://www.ehealth.fgov.be/standards/fhir/core/NamingSystem/uhmep"),
        NIHDI_PHYSIO_PATHO_SITUATION_CODE("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/nihdi-physiotherapy-pathologysituationcode"),
        AGREEMENT_TYPE("https://www.ehealth.fgov.be/standards/fhir/mycarenet/CodeSystem/agreement-types"),
        PROCESS_PRIORITY("http://terminology.hl7.org/CodeSystem/processpriority"),
        CLAIM_TYPE("http://terminology.hl7.org/CodeSystem/claim-type"),
    }

    override fun getPractitionerRole(practitionerRoleId: String, practitionerRole: String): PractitionerRole {
        return PractitionerRole().apply {
            id = "PractitionerRole$practitionerRoleId"
            meta = Meta(
                profile = listOf(MetaProfileEnum.BE_PRACTIONNERROLE.metaProfile)
            )
            practitioner = Reference().apply {
                reference = "Practitioner/Practitioner$practitionerRoleId"
            }
            code = listOf(
                CodeableConcept().apply {
                    coding = listOf(
                        Coding(
                            system = CodingSystemEnum.CD_HCPARTY.codingSystem,
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
                profile = listOf(MetaProfileEnum.BE_PRACTIONNER.metaProfile)
            )
            identifier = listOf(
                Identifier().apply {
                    system = CodingSystemEnum.NIHDI.codingSystem
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
        subTypeCode: String,
        agreementStartDate: DateTime,
        insuranceRef: String?,
        pathologyCode: String?,
        pathologyStartDate: DateTime?,
        provider: String,
        attachments: List<EagreementController.Attachment>?
    ): Claim {
        return Claim(
            patient = Reference().apply { reference = "Patient/Patient1" },
            priority = getCodableConcept(CodingSystemEnum.PROCESS_PRIORITY.codingSystem, "stat"),
            provider = Reference().apply { reference = provider },
            type = getCodableConcept(CodingSystemEnum.CLAIM_TYPE.codingSystem, "professional")
        ).apply {
            id = "Claim$claimId"
            meta = Meta(
                profile = listOf(MetaProfileEnum.BE_AGREEMENT_CLAIM_KINE.metaProfile)
            )
            status = "active"
            subType = getCodableConcept(CodingSystemEnum.AGREEMENT_TYPE.codingSystem, subTypeCode)
            use = "preauthorization"
            if(requestType == EagreementServiceImpl.RequestTypeEnum.ASK || requestType == EagreementServiceImpl.RequestTypeEnum.EXTEND) billablePeriod = getBillablePeriod(agreementStartDate!!)
            created = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"))
            enterer = Reference().apply { reference = "PractitionerRole/PractitionerRole1"}
            if (requestType == EagreementServiceImpl.RequestTypeEnum.ASK || requestType == EagreementServiceImpl.RequestTypeEnum.COMPLETE_AGREEMENT || requestType == EagreementServiceImpl.RequestTypeEnum.EXTEND) {
                referral = Reference().apply {
                    reference = "ServiceRequest/ServiceRequest1"
                }
            }
            if (attachments != null) {
                val supportingInfoList = mutableListOf<ClaimSupportingInfo>()
                var sequenceNumber = 1
                for (attachment in attachments!!) {
                    if (attachment.type == "info") {
                        supportingInfoList.add(getSupportingInfo(sequenceNumber, "info", null, null, attachment.data, null, null, null))
                    } else {
                        supportingInfoList.add(getSupportingInfo(sequenceNumber, "attachment", attachment.type, null, null, attachment.data, attachment.type, "application/pdf"))
                    }
                    sequenceNumber += 1
                }
                supportingInfo = supportingInfoList
            }
            insurance = listOf(getInsurance(requestType, insuranceRef, "use of mandatory insurance coverage, no further details provided here."))
            if(requestType == EagreementServiceImpl.RequestTypeEnum.ASK || requestType == EagreementServiceImpl.RequestTypeEnum.EXTEND) item = listOf(getServicedDateItem(requestType, pathologyStartDate!!, pathologyCode, 1))
        }
    }

    override fun getSupportingInfo(sequenceNumber: Int, claimInformationCategory: String, annexTypeCode: String?, valueReference: String?, valueString: String?, valueAttachmentData: String?, valueAttachmentTitle: String?, valueAttachmentContentType: String?): ClaimSupportingInfo {
        var code: CodeableConcept? = null
        val category = CodeableConcept().apply {
            coding = listOf(Coding(
                system = CodingSystemEnum.CLAIM_INFORMATION_CATEGORY.codingSystem,
                code = claimInformationCategory
            ))
        }

        if(!annexTypeCode.isNullOrEmpty()){
            code = CodeableConcept().apply {
                coding = listOf(Coding(
                    system = CodingSystemEnum.ANNEX_TYPES.codingSystem,
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
                profile = listOf(MetaProfileEnum.BE_ORGANIZATION.metaProfile)
            )
            identifier = listOf(
                Identifier().apply {
                    system = CodingSystemEnum.NIHDI.codingSystem
                    value = orgNihii
                }
            )
            type = listOf(
                CodeableConcept().apply {
                    coding = listOf(
                        Coding(
                            system = CodingSystemEnum.CD_HCPARTY.codingSystem,
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
                profile = listOf(MetaProfileEnum.BE_PATIENT.metaProfile)
            ),
            identifier = listOf(
                Identifier().apply {
                    when{
                        !patientSsin.isNullOrEmpty() && ioMembership.isNullOrEmpty() -> {
                            system = CodingSystemEnum.SSIN.codingSystem
                            value = patientSsin
                        }
                        patientSsin.isNullOrEmpty() && !ioMembership.isNullOrEmpty() && !io.isNullOrEmpty() -> {
                            system = CodingSystemEnum.INSURANCY_MEMBERSHIP.codingSystem
                            value = ioMembership
                            assigner = Reference(identifier = Identifier().apply {
                                system = CodingSystemEnum.INSURANCE_NUMBER.codingSystem
                                value = io
                            })
                        }
                        !patientSsin.isNullOrEmpty() && !ioMembership.isNullOrEmpty() -> {
                            system = CodingSystemEnum.INSURANCY_MEMBERSHIP.codingSystem
                            value = ioMembership
                            assigner = Reference(identifier = Identifier().apply {
                                system = CodingSystemEnum.INSURANCE_NUMBER.codingSystem
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

    override fun getServiceRequest(serviceRequestId: String, prescriptionId: String, data: String, annexId: String, quantity: Float, patientFirstName: String, patientLastName: String, gender: String, patientSsin: String?, io: String?, ioMembership: String?, sctCode: String?, sctDisplay: String?): ServiceRequest {
        val patient = getPatient(patientFirstName!!, patientLastName!!, gender!!, patientSsin, io, ioMembership)
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        return ServiceRequest(subject = Reference().apply { patient }).apply {
            id = "ServiceRequest$serviceRequestId"
            meta = Meta(
                profile = listOf(MetaProfileEnum.BE_EAGREEMENTSERVICEREQUEST.metaProfile)
            )
            status = "active"
            intent = "order"
            if(!sctCode.isNullOrEmpty() && !sctDisplay.isNullOrEmpty()){
                category = listOf(
                    CodeableConcept().apply {
                        coding = listOf(
                            Coding(
                                system = CodingSystemEnum.SCT.codingSystem,
                                code = sctCode,
                                display = sctDisplay
                            )
                        )
                    }
                )
                code = CodeableConcept().apply {
                    coding = listOf(
                        Coding(
                            system = CodingSystemEnum.SCT.codingSystem,
                            code = sctCode,
                            display = sctDisplay
                        )
                    )
                }
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
                               startDate: DateTime?,
                               endDate: DateTime?,
                               patientFirstName: String?,
                               patientLastName: String?,
                               patientGender: String?,
                               patientSsin: String?,
                               io: String?,
                               ioMembership: String?,
                               subTypeCode: String?
    ): Parameters {
        val param = mutableListOf<ParametersParameter>();
        param.add(getParameter("resourceType", startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership, subTypeCode))
        param.add(getParameter("patient", startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership, subTypeCode))
        param.add(getParameter("use", startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership, subTypeCode))
        param.add(getParameter("subType", startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership, subTypeCode))

        if (startDate != null || endDate != null){
            param.add(getParameter("preAuthPeriod", startDate, endDate, patientFirstName, patientLastName, patientGender, patientSsin, io, ioMembership, subTypeCode))
        }
        return Parameters().apply {
            id = "Parameters$parameterId"
            parameter = param
        }
    }

    override fun getParameter(parameterName: String,
                              startDate: DateTime?,
                              endDate: DateTime?,
                              patientFirstName: String?,
                              patientLastName: String?,
                              patientGender: String?,
                              patientSsin: String?,
                              io: String?,
                              ioMembership: String?,
                              subTypeCode: String?
    ): ParametersParameter{
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        return ParametersParameter().apply {
            name = parameterName
            when{
                parameterName == "resourceType" -> valueString = "ClaimResponse"
                parameterName == "patient" -> valueReference = Reference().apply {
                    reference = "Patient/Patient1"
                }
                parameterName == "use" -> valueCode = "preauthorization"
                parameterName == "subType" -> valueCoding = Coding().apply {
                    system = CodingSystemEnum.AGREEMENT_TYPE.codingSystem
                    code = subTypeCode
                }
                parameterName == "preAuthPeriod" -> valuePeriod = Period().apply {
                    if(startDate != null){
                        start = startDate?.let { formatter.print(it) }
                    }

                   if(endDate != null){
                       end = endDate?.let { formatter.print(it) }
                   }
                }
            }
        }
    }

    override fun getInsurance(requestType: EagreementServiceImpl.RequestTypeEnum, insuranceRef: String?, display: String): ClaimInsurance{
        return ClaimInsurance(
            sequence = 1,
            focal = true,
            coverage = Reference(
                display = display
            )
        ).apply {
            if(requestType != EagreementServiceImpl.RequestTypeEnum.ASK && insuranceRef != null) preAuthRef = listOf(insuranceRef)
        }
    }

    override fun getBillablePeriod(startDate: DateTime): Period{
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        return Period(
            start = formatter.print(startDate)
        )
    }

    override fun getServicedDateItem(requestType: EagreementServiceImpl.RequestTypeEnum, pathologyDate: DateTime, pathologyCode: String?, sequenceNumber: Int): ClaimItem {
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        return ClaimItem(
            sequence = sequenceNumber,
            productOrService = CodeableConcept(
                coding = listOf(
                    Coding(
                        system = CodingSystemEnum.NIHDI_PHYSIO_PATHO_SITUATION_CODE.codingSystem,
                        code = pathologyCode
                    )
                )
            )
        ).apply {
            if(requestType == EagreementServiceImpl.RequestTypeEnum.ASK) servicedDate = formatter.print(pathologyDate)
        }
    }

    override fun getCodeItem(code: String): ClaimItem {
        return ClaimItem(
            productOrService = getCodableConcept(CodingSystemEnum.NIHDI_PHYSIO_PATHO_SITUATION_CODE.codingSystem, code)
        )
    }

    override fun getAdditionalNotes(additionalNotes: String): ClaimSupportingInfo{
        return ClaimSupportingInfo(
            sequence = 2,
            category = CodeableConcept().apply {
                coding = listOf(Coding().apply {
                    system = CodingSystemEnum.CLAIM_INFORMATION_CATEGORY.codingSystem
                    code = "info"
                })
            },
            valueString = additionalNotes
        )
    }

    override fun getPrescriptionInfos(quantity: Int, document: String, documentId: String, sctCode: String?, sctDisplay: String?): ServiceRequest{
        return ServiceRequest(
            identifier = listOf(
                Identifier(
                    value = "Prescription id",
                    system = CodingSystemEnum.UHMEP.codingSystem
                )
            ),
            status = "active",
            intent = "order",
            category = listOf(
                CodeableConcept(
                    coding = listOf(
                        Coding(
                            system = CodingSystemEnum.SCT.codingSystem,
                            code = sctCode,
                            display = sctDisplay
                        )
                    )
                )
            ),
            code = CodeableConcept(
                coding = listOf(
                    Coding(
                        system = CodingSystemEnum.SCT.codingSystem,
                        code = sctCode,
                        display = sctDisplay
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
                profile = listOf(MetaProfileEnum.BE_MESSAGEHEADER.metaProfile)
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
        messageEventSystem: EagreementServiceImpl.MessageEventSystemEnum,
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
        prescriberNihii: String?,
        prescriberFirstName: String?,
        prescriberLastName: String?,
        orgNihii: String?,
        organizationType: String?,
        prescription1: String?,
        prescription2: String?,
        agreementStartDate: DateTime?,
        agreementEndDate: DateTime?,
        agreementType: String?,
        numberOfSessionForPrescription1: Float?,
        numberOfSessionForPrescription2: Float?,
        insuranceRef: String?,
        pathologyCode: String?,
        pathologyStartDate: DateTime?,
        sctCode: String?,
        sctDisplay: String?,
        subTypeCode: String?,
        attachments: List<EagreementController.Attachment>?
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
                profile = when (requestType) {
                    EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST -> listOf(MetaProfileEnum.BE_AGREEMENTCONSULT.metaProfile)
                    else -> listOf(MetaProfileEnum.BE_AGREEMENTDEMAND.metaProfile)
                }
            )
            type = "message"
            timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"))
            entry = entries
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
                        messageEventSystem.eventSystem,
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

        if (prescriberNihii != null && prescriberFirstName != null && prescriberLastName != null) {
            val practitioner2 = JsonObject()
            practitioner2.addProperty("fullUrl", "urn:uuid:" + uuidGenerator.generateId())
            practitioner2.add(
                "resource",
                JsonParser().parse(
                    mapper.writeValueAsString(
                        getPractitioner(
                            "2",
                            prescriberNihii,
                            prescriberFirstName,
                            prescriberLastName
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
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(practitionerRole2)
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

        if (prescriberNihii != null) {
            val practitionerRole2 = JsonObject()
            practitionerRole2.addProperty("fullUrl", "urn:uuid:" + uuidGenerator.generateId())
            practitionerRole2.add(
                "resource",
                JsonParser().parse(mapper.writeValueAsString(getPractitionerRole("2", "persphysician"))).asJsonObject
            )
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(practitionerRole2)
        }

        if (orgNihii != null && organizationType != null) {
            val organization = JsonObject()
            organization.addProperty("fullUrl" , "urn:uuid:"+uuidGenerator.generateId())
            organization.add("resource", JsonParser().parse(mapper.writeValueAsString(getOrganization("1", orgNihii, organizationType))).asJsonObject)
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(organization)
        }

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
                            agreementStartDate,
                            agreementEndDate,
                            hcpNihii,
                            hcpFirstName,
                            hcpLastName,
                            patientSsin,
                            patientIo,
                            patientIoMembership,
                            subTypeCode
                        )
                    )
                ).asJsonObject
            )
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(parameter1)
        }
        //Service Request 1
        if (requestType == EagreementServiceImpl.RequestTypeEnum.ASK || requestType == EagreementServiceImpl.RequestTypeEnum.COMPLETE_AGREEMENT || requestType == EagreementServiceImpl.RequestTypeEnum.ARGUE || requestType == EagreementServiceImpl.RequestTypeEnum.EXTEND) {
            val serviceRequest1 = JsonObject()
            serviceRequest1.addProperty("fullUrl" , "urn:uuid:" + uuidGenerator.generateId())
            serviceRequest1.add("resource", JsonParser().parse(mapper.writeValueAsString(getServiceRequest("1", "", prescription1!!, "1", numberOfSessionForPrescription1!!, patientFirstName, patientLastName, patientGender, patientSsin, patientIo, patientIoMembership, sctCode, sctDisplay))).asJsonObject)
            serviceRequest1.getAsJsonObject("resource").getAsJsonObject("ServiceRequest").add("contained", JsonParser().parse(mapper.writeValueAsString( Binary(
                contentType = "application/pdf",
                data = prescription1,
                id = "annexSR1"
            ))).asJsonObject)
            serviceRequest1.getAsJsonObject("resource").getAsJsonObject("ServiceRequest").getAsJsonObject("quantityQuantity").addProperty("value", numberOfSessionForPrescription1.toInt())
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(serviceRequest1)
        }

        //Claim 1
        if (requestType == EagreementServiceImpl.RequestTypeEnum.ASK || requestType == EagreementServiceImpl.RequestTypeEnum.ARGUE || requestType == EagreementServiceImpl.RequestTypeEnum.CANCEL || requestType == EagreementServiceImpl.RequestTypeEnum.EXTEND || requestType == EagreementServiceImpl.RequestTypeEnum.COMPLETE_AGREEMENT) {
            val claim = this.getClaim(
                requestType,
                claimId = "1",
                subTypeCode = agreementType!!,
                agreementStartDate = DateTime(),
                insuranceRef = insuranceRef,
                pathologyCode = pathologyCode,
                pathologyStartDate = pathologyStartDate,
                provider = "PractitionerRole/PractitionerRole1",
                attachments = attachments
            )
            val parameter = JsonObject()
            parameter.addProperty("fullUrl" , "urn:uuid:"+uuidGenerator.generateId())
            parameter.add("resource", JsonParser().parse(mapper.writeValueAsString(claim)).asJsonObject)
            gson.getAsJsonObject("Bundle").getAsJsonArray("entry").add(parameter)
        }

        return gson
    }
}
