# eAttest, Chapter IV and eAgreement

This chapter covers the FHC endpoints that deal with attestations of care and prior authorisations sent to Belgian insurance organisations (IOs) through MyCareNet. eAttest transmits an electronic attestation of care (the electronic equivalent of the paper "attestation de soins donnés") and returns the IO's acknowledgement and invoicing number. Chapter IV handles prior-authorisation requests for Chapter IV reimbursable medication (KMEHR-based, through the eHealth Chapter IV services). eAgreement is the FHIR-based MyCareNet agreement service, which FHC currently uses for physiotherapy agreements. All endpoints return JSON.

- [eAttest v1, legacy alias (`/eattest`)](#eattest-v1-legacy-alias-eattest)
- [eAttest v2 (`/eattestv2`)](#eattest-v2-eattestv2)
- [eAttest v3 (`/eattestv3`)](#eattest-v3-eattestv3)
- [Chapter IV (`/chap4`)](#chapter-iv-chap4)
- [eAgreement (`/eagreement`)](#eagreement-eagreement)

### How eAttest v1, v2 and v3 relate

| Base path | Controller | Service used | MyCareNet protocol | Status |
|---|---|---|---|---|
| `/eattest` | `EattestController` | `EattestV2Service` | eAttest v2 (`endpoint.eattestv2`) | Legacy alias. No cancellation; `treatmentReason` is always sent empty. |
| `/eattestv2` | `EattestV2Controller` | `EattestV2Service` | eAttest v2 (`endpoint.eattestv2`) | Legacy. Adds cancellation and `treatmentReason`. |
| `/eattestv3` | `EattestV3Controller` | `EattestV3Service` | eAttest v3 (`endpoint.eattestv3`) | Current. Only version that receives new features. |

The `/eattest` controller does not call the older `EattestService` (v1) implementation. It injects `EattestV2Service` and calls `sendAttestV2`, so `/eattest` and `/eattestv2` reach the same MyCareNet v2 service. The v2 endpoint defaults to the UDDI key `uddi:ehealth-fgov-be:business:mycareneteattest:v2` and the v3 endpoint to `uddi:ehealth-fgov-be:business:mycareneteattest:v3`. The v3-only features are the provider quality (`hcpQuality`), RCAM routing, `attemptNbr`, `decisionReference`, `inputReference`, and the refined error extraction described below.

All three share the rate-limit group `eattest` (`fhc.ratelimit.groups.eattest.path-patterns=/eattest/**,/eattestv2/**,/eattestv3/**`).

**Response types (all eAttest versions).** Endpoints ending in `/verbose` return `SendAttestResultWithResponse`, which includes the MyCareNet conversation and errors. The other endpoints return `SendAttestResult` (`acknowledge`, `invoicingNumber`, `attest`). The request body `Eattest` holds `codes: List<EattestCode>`, one entry per attested act.

## eAttest v1, legacy alias (`/eattest`)

This path wraps the MyCareNet eAttest service through `EattestV2Service`, so it is protocol v2 despite its name. It exists for clients written against the original path. It can only send attestations. Use `/eattestv3` for new integrations.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are all required.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/eattest/send/{patientSsin}/verbose` | Send an attestation and return the full response | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `hcpCbe`, `patientFirstName`, `patientLastName`, `patientGender`; `date?`, `traineeSupervisorSsin?`, `traineeSupervisorNihii?`, `traineeSupervisorFirstName?`, `traineeSupervisorLastName?`, `guardPostNihii?`, `guardPostSsin?`, `guardPostName?`; body `Eattest` |
| POST | `/eattest/send/{patientSsin}` | Send an attestation and return the simplified result | same as above |

**Notes**
- The controller accepts `date` but does not pass it to the service, which receives `null`.
- The controller always sends `treatmentReason` as an empty string.

## eAttest v2 (`/eattestv2`)

This controller wraps MyCareNet eAttest v2 (`endpoint.eattestv2`, SOAP `SendAttestation` / `CancelAttestation` in protocol `be.fgov.ehealth.mycarenet.attest.protocol.v2`). Compared with `/eattest`, it adds `treatmentReason` and cancellation of a previously sent attestation. It is legacy and has been superseded by v3.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are all required.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/eattestv2/send/{patientSsin}/verbose` | Send an attestation and return the full response | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `hcpCbe`, `patientFirstName`, `patientLastName`, `patientGender`; `treatmentReason?`, `date?`, `traineeSupervisor{Ssin,Nihii,FirstName,LastName}?`, `guardPost{Nihii,Ssin,Name}?`; body `Eattest` |
| POST | `/eattestv2/send/{patientSsin}` | Send an attestation and return the simplified result | same as above |
| DELETE | `/eattestv2/send/{patientSsin}` | Cancel an attestation and return the simplified result | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `hcpCbe`, `patientFirstName`, `patientLastName`, `patientGender`, `eAttestRef`, `reason`; `date?`, `traineeSupervisor{Ssin,Nihii,FirstName,LastName}?`; no body |
| DELETE | `/eattestv2/send/{patientSsin}/verbose` | Cancel an attestation and return the full response | same as above |

**Notes**
- The controller accepts `date` on every endpoint but does not pass it to the service, which receives `null`.
- The cancel endpoints do not accept guard-post parameters.

## eAttest v3 (`/eattestv3`)

This controller wraps MyCareNet eAttest v3 (`endpoint.eattestv3`, SOAP actions `urn:be:fgov:ehealth:mycarenet:attest:protocol:v3:SendAttestation` and `...:CancelAttestation`). It is the current version. Compared with v2, it adds the provider quality (including physiotherapists), routing to RCAM, retries of the same attestation through an attempt number, a reference to a prior IO decision (Chapter IV or eAgreement), and reuse of an `inputReference` for duplicates.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are all required.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/eattestv3/send/{patientSsin}/verbose` | Send an attestation and return the full response | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `hcpCbe`, `patientFirstName`, `patientLastName`, `patientGender`; `hcpQuality?`, `isPatientRcam?`, `date?`, `treatmentReason?`, `traineeSupervisor{Ssin,Nihii,FirstName,LastName}?`, `guardPost{Nihii,Ssin,Name}?`, `attemptNbr?`, `decisionReference?`, `inputReference?`; body `Eattest` |
| POST | `/eattestv3/send/{patientSsin}` | Send an attestation and return the simplified result | same as above |
| DELETE | `/eattestv3/send/{patientSsin}` | Cancel an attestation and return the simplified result | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `hcpCbe`, `patientFirstName`, `patientLastName`, `patientGender`, `eAttestRef`, `reason`; `date?`, `attemptNbr?`, `traineeSupervisor{Ssin,Nihii,FirstName,LastName}?`; no body |
| DELETE | `/eattestv3/send/{patientSsin}/verbose` | Cancel an attestation and return the full response | same as above |

**Notes**
- **`hcpQuality`.** When this parameter is absent, FHC derives it as `guardpost` if `guardPostNihii` is set and `doctor` otherwise. It is mapped to the KMEHR author `cd-hcparty` as follows: `doctor` becomes `persphysician`, `guardpost` becomes `guardpost`, `physiotherapist` becomes `persphysiotherapist`, and any other value becomes `persphysician`. The same value is sent as the NIHII `quality` in the MyCareNet `CareProvider`. With `guardpost`, the guard-post NIHII is used as the request author.
- **Physiotherapists (kiné).** Physiotherapists send `hcpQuality=physiotherapist`; the mapping to `persphysiotherapist` was added by commit `6b2c1fb9b`. Commit `63db87753` (MS-11873, eAttest kiné) then fixed how `decisionReference` is encoded, which makes it usable for kiné attestations. It used to be sent as a `CD` (`CD-CONTENT` LOCAL) and is now sent as a KMEHR `ID` with `S="LOCAL"`, `SV="1.0"` and `SL="OAreferencesystemname"`, in an item of type `decisionreference`. Use it to reference the IO decision or agreement the attestation relies on, for example a physiotherapy eAgreement.
- **`isPatientRcam=true`** sets `mutuality="rcam"` on the routing care receiver, which sends the attestation to the RCAM/HZIV. The default is `false`.
- **`attemptNbr`** is sent as the `urn:be:cin:nippin:attemptNbr` attribute and defaults to `1`. Increment it when you resend the same attestation after a failed attempt. It is also accepted on cancellation.
- **`inputReference` (duplicata support).** The send endpoints accept an optional `inputReference`. If it is provided, FHC uses it as the MyCareNet `CommonInput.inputReference` instead of generating a new one, so a duplicate can reuse the reference of the original eAttest. If it is absent, FHC generates a new reference as before. The cancel endpoints always generate a new reference.
- **Error extraction.** When mapping acknowledgement errors, FHC takes the error code from `CD-ERROR`, then from the MyCareNet refusal scheme (`CD_REFUSAL_MYCARENET`), and finally from the first `cd` present. If the error URL/XPath resolves to an element that has no entry in the known eAttest error table, FHC still returns a `MycarenetError` with the code, the path, the offending value, and the description supplied by MyCareNet as `msgFr`/`msgNl`. If MyCareNet supplies no description, the messages fall back to `Erreur <code>` / `Fout <code>`. Previously, unknown codes were dropped.
- The cancel endpoints accept `date`, but the controller passes `null` to the service. On the send endpoints, `date` is forwarded.
- The `ACPT` test flag (`isTest`) is derived from whether `endpoint.genins` contains `-acpt`.

## Chapter IV (`/chap4`)

This controller wraps the eHealth Chapter IV services: `ChapterIVAgreementConsultation/v1` (`endpoint.ch4.consultation.v1`) and `ChapterIVAgreementAdmission/v1` (`endpoint.ch4.admission.v1`). The requests are KMEHR messages encrypted for the Chapter IV key depot (`chapterIV.keydepot.*`). Chapter IV covers prior authorisation by the IO's medical advisor for medication reimbursed under Chapter IV of the reimbursement list. Each request targets a Chapter IV paragraph (and optionally verses) of a given CIVICS version.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are all required.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/chap4/consult/{patientSsin}` | Consult the patient's existing Chapter IV agreements and requests | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientDateOfBirth`, `patientFirstName`, `patientLastName`, `patientGender`; `civicsVersion?`, `paragraph?`, `start?`, `end?`, `reference?` |
| POST | `/chap4/new/{patientSsin}/{civicsVersion}/{requestType}/{paragraph}` | Send an agreement request (new, extension, annex, and so on) | path `patientSsin`, `civicsVersion`, `requestType`, `paragraph`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientDateOfBirth`, `patientFirstName`, `patientLastName`, `patientGender`; `verses?` (comma-separated), `incomplete?`, `start?`, `end?`, `decisionReference?`, `ioRequestReference?`; body `List<Appendix>` |
| DELETE | `/chap4/cancel/{patientSsin}` | Cancel a pending agreement request | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientDateOfBirth`, `patientFirstName`, `patientLastName`, `patientGender`; `decisionReference?`, `iorequestReference?` |
| DELETE | `/chap4/close/{patientSsin}` | Close (end) a granted agreement | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientDateOfBirth`, `patientFirstName`, `patientLastName`, `patientGender`, `decisionReference` |

All four endpoints return `AgreementResponse`.

**Notes**
- **`requestType`** must be one of the `RequestType` enum values: `newrequest`, `complimentaryannex`, `extension`, `noncontinuousextension`, `cancellation` or `closure`. The value is parsed with `RequestType.valueOf`, so the case must match. Cancellation and closure normally go through the dedicated `DELETE` endpoints, which build `cancellation` and `closure` transactions.
- `Appendix` has the fields `verseSeq?`, `documentSeq?`, `data?` (bytes, base64 in JSON), `mimeType?` and `path?`.
- On `consult` and `new`, if `start` is omitted, the controller sets it to 15 days ago (epoch milliseconds, start of day). `incomplete` defaults to `false`.
- `patientDateOfBirth` is interpreted as a fuzzy date (`YYYYMMDD`).
- Watch the parameter names: cancellation uses `iorequestReference` (lower-case `r`), while `new` uses `ioRequestReference`.
- The Chapter IV flows in FHC are consult, new/extend/annex, cancel and close. There is no argue flow in Chapter IV.

## eAgreement (`/eagreement`)

This controller wraps the MyCareNet eAgreement service (`endpoint.agreement`, `https://services.ehealth.fgov.be/MyCareNet/eAgreement/v1` in production). Asynchronous responses are retrieved through the MyCareNet Generic Async channel for eAgreement (`GenAsyncServiceImpl("eagreement")`, `endpoint.genericasync.eagreement.v1`). Unlike Chapter IV, the payload is a FHIR Bundle (`Claim` / `ClaimResponse`, `use=preauthorization`) built by `EagreementServiceUtilsImpl`. It is the successor to the KMEHR-based agreement flows. In FHC, it is implemented for physiotherapy agreements: the practitioner role is `persphysiotherapist`, and pathologies are coded with the `nihdi-physiotherapy-pathologysituationcode` code system. The agreement subtype is passed in `agreementType` (or `subTypeCode` for consultation).

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are all required. If no SAML token can be obtained, the endpoint returns HTTP 401.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/eagreement/askAgreement` | Ask for a new agreement (`claim-ask`) | `hcpQuality`, `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `prescriberNihii`, `prescriberFirstName`, `prescriberLastName`, `patientFirstName`, `patientLastName`, `patientGender`, `pathologyStartDate`, `pathologyCode`, `sctCode`, `prescriptionDate`; `sctDisplay?`, `patientSsin?`, `patientIo?`, `patientIoMembership?`, `orgNihii?`, `organizationType?`, `agreementStartDate?`, `agreementEndDate?`, `agreementType?`, `numberOfSessionForPrescription1?`, `numberOfSessionForPrescription2?`; body `List<Attachment>?` |
| POST | `/eagreement/consultList` | Consult the patient's agreements (FHIR search, `search-type`) | `hcpQuality`, `hcpNihii`, `hcpName`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientFirstName`, `patientLastName`, `patientGender`, `subTypeCode`; `insuranceRef?`, `patientSsin?`, `patientIo?`, `patientIoMembership?`, `orgNihii?`, `organizationType?`, `agreementStartDate?`, `agreementEndDate?`, `agreementType?`; no body |
| POST | `/eagreement/cancelAgreement` | Cancel an agreement (`claim-cancel`) | `hcpQuality`, `hcpNihii`, `hcpName`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientFirstName`, `patientLastName`, `patientGender`, `insuranceRef`, `prescriptionDate`; `patientSsin?`, `patientIo?`, `patientIoMembership?`, `orgNihii?`, `organizationType?`, `agreementType?`; no body |
| POST | `/eagreement/extendAgreement` | Extend an existing agreement (`claim-extend`) | same as `askAgreement`, plus required `insuranceRef`; `sctCode` is optional here |
| POST | `/eagreement/completeAgreement` | Complete a pending agreement with extra information (`claim-completeAgreement`) | `hcpQuality`, `hcpNihii`, `hcpName`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `prescriberNihii`, `prescriberFirstName`, `prescriberLastName`, `patientFirstName`, `patientLastName`, `patientGender`, `insuranceRef`, `prescriptionDate`; `sctCode?`, `sctDisplay?`, `patientSsin?`, `patientIo?`, `patientIoMembership?`, `orgNihii?`, `organizationType?`, `agreementType?`, `numberOfSessionForPrescription1?`, `numberOfSessionForPrescription2?`; body `List<Attachment>?` |
| POST | `/eagreement/argueAgreement` | Argue (contest) a refused or pending agreement (`claim-argue`) | same as `completeAgreement` |
| POST | `/eagreement/async/getMessages` | Fetch asynchronous `eAgreement-response` messages (at most 100) | `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `hcpSpeciality`; no body |
| POST | `/eagreement/async/confirmMessage` | Confirm (acknowledge) received asynchronous messages | `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `hcpSpeciality`; body `List<String>` (message references) |

The six claim endpoints return `EAgreementResponse`, `async/getMessages` returns `EAgreementList`, and `async/confirmMessage` returns `Boolean`.

**Notes**
- **Flows.** The flows are ask, extend, complete, argue, cancel, consultList, then async getMessages and confirmMessage. Every claim endpoint except `askAgreement` identifies the target agreement by the IO's `insuranceRef` (required). On `consultList`, `insuranceRef` is only an optional filter.
- **Referral when arguing (MS-15407, commit `f09030238`).** The FHIR `Claim` now carries `referral = ServiceRequest/ServiceRequest1` for `ARGUE`, in addition to `ASK`, `COMPLETE_AGREEMENT` and `EXTEND`. An argue request therefore includes the prescription (`ServiceRequest`) reference even though the prescription parameters are optional.
- **Date parameters** (`pathologyStartDate`, `prescriptionDate`, `agreementStartDate`, `agreementEndDate`) are integers in `yyyyMMdd` format. For ask and extend, the billable period is computed from `agreementStartDate`.
- **Patient identification.** `patientSsin`, `patientIo` and `patientIoMembership` are all optional. Provide either the SSIN or the IO and membership number.
- **Attachments.** `Attachment` is `{type, data}`. Entries of type `prescription1` and `prescription2` are extracted as the two prescriptions, and all other entries are sent as additional attachments (free-text attachments are allowed).
- **`orgNihii` and `organizationType`.** The organisation is added to the Bundle only when both are provided.
- `hcpName` is required on `consultList`, `cancelAgreement`, `completeAgreement` and `argueAgreement`, but the controller does not forward it to the service.
- On the async endpoints, `hcpSpeciality` is passed to the service as `hcpQuality`.
- **Error mapping.** `MissingTokenException` returns 401, `IllegalArgumentException` returns 400, and `SOAPFaultException` returns 502.

### Chapter IV and eAgreement compared

| | Chapter IV (`/chap4`) | eAgreement (`/eagreement`) |
|---|---|---|
| Domain in FHC | Chapter IV reimbursable medication (paragraph and verses of a CIVICS version) | Physiotherapy agreements (pathology situation codes, `persphysiotherapist`) |
| Format | KMEHR, encrypted for the Chapter IV key depot | FHIR Bundle (`Claim` / `ClaimResponse`) |
| Upstream | `ChapterIVAgreementConsultation/v1`, `ChapterIVAgreementAdmission/v1` | `MyCareNet/eAgreement/v1` and Generic Async `eagreement` |
| Request | `POST /chap4/new/...` with `requestType=newrequest` | `askAgreement` |
| Extend | `requestType=extension` or `noncontinuousextension` | `extendAgreement` |
| Add information | `requestType=complimentaryannex` | `completeAgreement` |
| Contest | not available | `argueAgreement` |
| Cancel | `DELETE /chap4/cancel/...` | `cancelAgreement` |
| Close | `DELETE /chap4/close/...` | not available |
| Consult | `GET /chap4/consult/...` | `consultList` |
| Asynchronous responses | not applicable (synchronous) | `async/getMessages`, `async/confirmMessage` |

The decision or agreement reference returned by either service can be passed as `decisionReference` to `/eattestv3/send/...`.
