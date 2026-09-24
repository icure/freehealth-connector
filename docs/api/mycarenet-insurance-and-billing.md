# MyCareNet: insurability, billing and GMD

This chapter covers the FHC controllers that talk to the insurance organisations (mutualities) through MyCareNet and the related eHealth services: insurability checks (GenIns, MemberData), fee consultation (Tarification), third-party-payer invoicing (eFact), medical-house membership (MHM), social-welfare coverage for uninsured patients (Mediprima and Mediprima UMA) and the Global Medical File (GMD/DMG). Each controller builds the MyCareNet request (Kmehr, flat file or SAML attribute query), signs or encrypts it with the caller's keystore, sends it under the caller's SAML token and maps the answer to a JSON DTO. Unless stated otherwise, dates passed as `Long` are epoch milliseconds, `io` is the three-digit insurance organisation code (for example `306`), `ioMembership` is the patient's registration number with that organisation, and `hcpNihii` / `hcpSsin` identify the caller. Values such as `<nihii>` or `<ssin>` in this chapter are placeholders.

- [General insurability (`/genins`)](#general-insurability-genins)
- [Member data (`/mda`)](#member-data-mda)
- [Tarification (`/tarif`)](#tarification-tarif)
- [eFact electronic invoicing (`/efact`)](#efact-electronic-invoicing-efact)
- [Medical house membership (`/mhm`)](#medical-house-membership-mhm)
- [Mediprima (`/mediprima`)](#mediprima-mediprima)
- [Mediprima UMA (`/mediprimaUma`)](#mediprima-uma-mediprimauma)
- [Global Medical File (`/gmd`)](#global-medical-file-gmd)

All authenticated endpoints expect the three FHC headers: `X-FHC-keystoreId` (UUID of a keystore uploaded through `/sts/keystore`), `X-FHC-passPhrase` (its passphrase) and `X-FHC-tokenId` (UUID of a SAML token obtained through `/sts/token`). A missing header is rejected by Spring with 400; an unknown or expired token is reported as 401 (`MissingTokenException`), an `IllegalArgumentException` as 400 and a SOAP fault from the remote service as 502 (see `web/ExceptionHandlers.kt`).

**GenIns or MemberData?** Both answer the question "is this patient insured, with which organisation, under which conditions, on this date?". GenIns (`/genins`) calls the older eHealth *GenericInsurability* v1 web service and returns a flat `InsurabilityInfoDto`. MemberData (`/mda`) calls the MyCareNet *MemberData* service, a SAML 2 attribute query. It supports *facets* that choose which categories of data are returned, it has an asynchronous batch mode (through GenAsync) for bulk checks such as a medical house's monthly membership list, and it returns the richer `MemberDataResponse`. New integrations should prefer MemberData. GenIns is kept for existing clients.

## General insurability (`/genins`)

Wraps the eHealth GenericInsurability v1 service (`endpoint.genins`, `uddi:ehealth-fgov-be:business:genericinsurability:v1`) through the connector's `GenInsServiceImpl.getInsurability`. It returns the insurability of a patient for a period as `InsurabilityInfoDto`: identity, `insurabilities` (per-period insurer and membership data), `generalSituation`, `paymentByIo`, `specialSocialCategory`, `medicalHouseInfo`, `hospitalizedInfo`, `transfers`, plus `errors` / fault fields. The patient is identified by SSIN or by `io` + `ioMembership`.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/genins/{ssin}` | Insurability of a patient identified by SSIN | path `ssin`; query `hcpNihii`, `hcpSsin`, `hcpName`, `hcpQuality?` (default `doctor`), `date?`, `endDate?`, `hospitalized?` |
| GET | `/genins/{io}/{ioMembership}` | Insurability of a patient identified by insurance organisation and membership number | path `io`, `ioMembership`; same query parameters as above |

**Notes**
- `date` and `endDate` are epoch milliseconds. `date` defaults to now and `endDate` defaults to `date`, so by default the query covers a single instant.
- The request is always sent with `isTest = false`. The acceptance/production switch is commented out in `GenInsServiceImpl`.

## Member data (`/mda`)

Wraps the MyCareNet MemberData service. The synchronous endpoints call `endpoint.memberdata` (default `uddi:ehealth-fgov-be:business:mycarenetmemberdata:v1`) with a SAML attribute query built from the patient identifier, the period, the optional `requestType` and the optional facets. The `/mda/async/*` endpoints go through MyCareNet GenAsync (`endpoint.genericasync.mda.v1`). The request blob is encrypted for MyCareNet's ETK, which is looked up in KeyDepot through `memberdata.keydepot.*`, default CBE 820563481 / `MYCARENET`. The answer is fetched later, then confirmed.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/mda/{ssin}` | Member data for a patient identified by SSIN (no facets) | path `ssin`; query `hcpNihii`, `hcpName`, `hcpSsin?`, `hcpQuality?` (default `doctor`), `date?`, `endDate?`, `hospitalized?`, `requestType?` |
| POST | `/mda/{ssin}` | Same as above, restricted to the facets in the body | path `ssin`; same query parameters; body `List<FacetDto>` |
| GET | `/mda/{io}/{ioMembership}` | Member data for a patient identified by insurance organisation and membership number | path `io`, `ioMembership`; same query parameters as `GET /mda/{ssin}` |
| POST | `/mda/{io}/{ioMembership}` | Same as above, with facets | path `io`, `ioMembership`; same query parameters; body `List<FacetDto>` |
| POST | `/mda/async/request` | Submit an asynchronous (batch) member data request; returns a `GenAsyncResponse` | query `hcpNihii`, `hcpName`, `hcpQuality?` (default `medicalhouse`), `hcpSsin?`, `date?`, `endDate?`, `hospitalized?`, `requestType?` (default `information`); body `MemberDataBatchRequestDto` |
| POST | `/mda/async/messages` | Fetch pending asynchronous answers; returns `MemberDataList` | query `hcpNihii`, `hcpName`, `hcpQuality?`, `hcpSsin?`, `messageNames?` (query list, not body) |
| POST | `/mda/async/confirm/messages` | Confirm processed answers so they are not delivered again | query `hcpNihii`, `hcpName`, `hcpQuality?`, `hcpSsin?`; body `List<String>` (message references) |
| POST | `/mda/async/confirm/acks` | Confirm processed acknowledgements | query `hcpNihii`, `hcpName`, `hcpQuality?`, `hcpSsin?`; body `List<String>` (ack hashes) |

**Notes**
- Default period: `date` defaults to the start of today in `mycarenet.timezone` (Europe/Brussels), and `endDate` defaults to the start of the following day. Both are epoch milliseconds.
- `hospitalized` is forwarded only by the two GET variants. The POST (facet) variants accept the parameter but do not pass it to the service.
- The default `hcpQuality` is `doctor` for the synchronous calls and `medicalhouse` for `/mda/async/request`.
- Async flow: `POST /mda/async/request`, then poll `POST /mda/async/messages`, then `POST /mda/async/confirm/messages` and `POST /mda/async/confirm/acks` with the references and hashes found in the answers.

## Tarification (`/tarif`)

Wraps the MyCareNet Tarification consultation service (`endpoint.mcn.tarification`, `uddi:ehealth-fgov-be:business:mycarenettarification:v1`). The caller sends a list of INAMI/RIZIV nomenclature codes for a patient and gets back the reimbursement, the patient share and the supplements that apply on the given date, as `TarificationConsultationResult`. The request is a Kmehr message sent in a signed MyCareNet blob.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId`.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/tarif/{ssin}` | Consult tariffs for a list of nomenclature codes | path `ssin` (patient); query `hcpFirstName`, `hcpLastName`, `hcpNihii`, `hcpSsin`, `date?`, `gmdNihii?`, `justification?`, `traineeSupervisorSsin?`, `traineeSupervisorNihii?`, `traineeSupervisorFirstName?`, `traineeSupervisorLastName?`, `guardPostNihii?`, `guardPostSsin?`, `anatomy?`, `relatedService?`; body `List<String>` (nomenclature codes) |

**Notes**
- `date` uses the `YYYYMMDD` format as a number (for example `20260115`), not epoch milliseconds. It defaults to now.
- `anatomy` and `relatedService` take either one value, applied to every code, or a comma-separated list. A list must have as many entries as there are codes in the body, otherwise the call fails.
- The endpoint does not return an HTTP error for business failures. SOAP faults are converted into `errors` entries, translated through `/be/errors/ConsultTarifErrors.json` when the code is known. Any other exception becomes an `errors` entry with code `999999`. Clients must check `errors` on every response.
- `/mediprima/consultTarificationMediprima/{patientSsin}` is the equivalent call for Mediprima patients.

## eFact electronic invoicing (`/efact`)

Wraps MyCareNet eFact (third-party-payer invoicing) over GenAsync. Batches are posted to `endpoint.genericasync.invoicing.v1` (the `hcpfac_12` channel). Mediprima batches, sent to the CPAS/OCMW through federation `690`, use `endpoint.genericasync.invoicing-mediprima.v1`. FHC turns the JSON `InvoicesBatch` into the INAMI/RIZIV fixed-width flat file (350-character records, see `format/efact/BelgianInsuranceInvoicingFormatWriter`), signs it (XAdES), posts it, and parses the insurers' answers back into `Record`/`Zone` structures.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId` on `/efact/batch`, the message retrieval endpoints and the confirmation endpoints. **No headers** are needed for `/efact/flat`, `/efact/flat/test` and `/efact/flatcore`: they only render the file locally.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/efact/batch` | Render, sign and send an invoice batch; returns `EfactSendResponse` | body `InvoicesBatch` |
| POST | `/efact/flat` | Render the complete flat file (production flags) as `text/plain`, without sending | body `InvoicesBatch` |
| POST | `/efact/flat/test` | Same, with test flags | body `InvoicesBatch` |
| POST | `/efact/flatcore` | Render the core of the file (ET 10 to ET 90) with metadata, offline; returns `FlatFileWithMetadata` JSON | body `InvoicesBatch` |
| GET | `/efact/{nihii}/{language}` | Fetch pending eFact answers and tAcks; returns `List<EfactMessage>` | path `nihii`, `language` (language of the parsed record descriptions); query `ssin`, `firstName`, `lastName`, `limit?` |
| GET | `/efact/mediprima/{nihii}/{language}` | Same, for Mediprima invoicing | same as above |
| PUT | `/efact/confirm/acks/{nihii}` | Confirm received tAcks | path `nihii`; query `ssin`, `firstName`, `lastName`; body `List<String>` (value hashes) |
| PUT | `/efact/mediprima/confirm/acks/{nihii}` | Confirm received Mediprima tAcks | same as above |
| PUT | `/efact/confirm/msgs/{nihii}` | Confirm received eFact messages | path `nihii`; query `ssin`, `firstName`, `lastName`; body `List<String>` (value hashes) |
| PUT | `/efact/mediprima/confirm/msgs/{nihii}` | Confirm received Mediprima messages | same as above |

In the message and confirmation endpoints, `nihii`, `ssin`, `firstName` and `lastName` identify the invoicing care provider.

**Notes**

*Send flow (`POST /efact/batch`).*
1. The request must include `numericalRef` (at most 14 digits), `sender`, `batchRef`, `fileRef` and `uniqueSendNumber`. Every invoice must have `invoiceNumber`, `invoiceRef`, `ioCode` and `patient`. A missing field returns 400.
2. Characters `! @ # & / \` are removed from sender and patient names.
3. The complete flat file is rendered: records 200/300, then the core (ET 10, then ET 20 / 50 / 51 / 52 / 80 for each invoice, then ET 90), then one 400 record per insurer and a closing 960000 record. Credit notes are written first, then invoices grouped by destination insurer.
4. Test mode follows `endpoint.mcn.tarification`: when that URL contains `-acpt`, the file is sent with the test codes (92 in the header, `9991999`) and `isTest = true`.
5. The file is posted as message `HCPFAC`, or as `ECM-HCPFAC` when `ioFederationCode` is `690` (Mediprima), with WS-Addressing `to = urn:nip:destination:io:<ioFederationCode>`. The input reference is `numericalRef` padded to 14 digits.
6. `EfactSendResponse` contains `success` (true when the tAck result is `urn:nip:tack:result:major:success`), `inputReference`, `tack`, the rendered file (`detail`), its parsed `records`, and the raw SOAP exchange (`mycarenetConversation`).

*Retrieval and confirmation.*
- `GET /efact/{nihii}/{language}` queries message types `HCPFAC`, `HCPAFD` and `HCPVWR`. The Mediprima variant queries `ECM-HCPFAC`, `ECM-HCPAFD` and `ECM-HCPVWR`.
- The service fetches at most 64 messages per call, fewer if `limit` is lower. On a socket timeout it divides the batch size by 4 and retries. On a "Not enough time" fault it waits 30 s and retries, up to 8 attempts.
- Each `EfactMessage` has a `hashValue`. Messages have a `name` equal to the message type, the decompressed flat `detail`, the parsed records in `message`, and the `messageReference`. Transport acknowledgements have `name = "tAck"` and carry the `tAck`.
- After processing, send the `hashValue`s back: `confirm/msgs` for messages and `confirm/acks` for tAcks. Unconfirmed items are delivered again on the next call.

*Offline rendering (`/flat`, `/flat/test`, `/flatcore`).*
- These endpoints need no keystore, no token and no network access, so clients can use them to preview, archive or test a batch.
- `/efact/flatcore` returns `{ "flatFile": "...", "metadata": { amount, recordsCount, codes, codesPerOAMap, amountPerOAMap, recordsCountPerOAMap } }`. The flat file holds only the core records (ET 10 to ET 90), without the 200/300 header and the 400/960000 trailers. Unlike `/flat`, it does not require `fileRef`.
- Validation errors from the format writer are raised as `IllegalArgumentException` and returned as 400 before anything is sent. `EfactFlatcoreOfflineTest` relies on this.

*Record 52 (eID / identity document capture).* An ET 52 is written after an item's ET 50 when `InvoiceItem.eidItem` or `InvoiceItem.agreementNumber` is set. When both are set, they go into the same record. `EIDItem` fields:

| Field | ET 52 zone | Values / rule |
|---|---|---|
| `readType` | Z 9 | `1` chip (default), `2` barcode, `3` datamatrix, `4` manual, `A` electronic (itsme) |
| `deviceType` | Z 10 | `1` eID (default), `2` ISI, `3` ISI+, `4` Kids-ID, `5` foreigner card, `6` itsme, `7` vignette, `0` unknown |
| `manualEntryReason` | Z 3 | required when `readType = 4`, range 1-8. Reasons 3, 4, 5, 6 and 8 are *deferred*: `readDate` must be null and `readHour` 0, and the zones are written as zeroes. |
| `readDate` | Z 6a/6b | required unless deferred. It is a FuzzyValues date, for example `20260729000000`. It defaults to the current time when the object is created without it. |
| `readHour` | Z 12/13 | `HHMM`, validated as 00-23 / 00-59 |
| `vignetteReason` | Z 11 | 0-9, and must be 0 unless `deviceType = 7` |
| `readValue` | Z 16 | the value that was read (15 positions) |
| `justificationDocumentNumber` | Z 17 | number of the justification document (25 positions, always written, default 0) |

Every rule is checked before the record is written, and a violation returns 400. Clients should send `readValue`. The legacy spelling `readvalue` is still accepted as an alias on input; responses always use `readValue`.

*Physiotherapy agreement number (ET 52 Z 19).* `InvoiceItem.agreementNumber` is written to ET 52 zone 19 (positions 132-151). This is the agreement number that an insurer returns through eAgreement. INAMI annexe 26.4 has required it for physiotherapists since 01/05/2022, and insurers reject a missing number, for example on code 567011, with error 521904.
- The value must be exactly 20 digits (XXX mutuality + 15 digits + 2 check digits, modulo 97). The check digits are not verified. Any other length or a non-digit character returns 400 ("expected exactly 20 digits"), and the error message does not repeat the value.
- When the sender's `professionCode` is `50` (physiotherapist), an agreement number without `eidItem` is refused with 400, because annexe 26.4 makes Z 9 and Z 10 mandatory with no exception. If the card was not read, use `readType = 4` with a deferred `manualEntryReason`, or `deviceType = 7`.
- For other professions, an agreement number alone produces an ET 52 whose eID zones keep their defaults.
- When `agreementNumber` is null, Z 19 is written as twenty zeroes and the record is identical to the one produced before the field existed.
- Do not confuse it with `insuranceRef`/`insuranceRefDate`, which produce an ET 51 (zone 42), not an ET 52.

## Medical house membership (`/mhm`)

Wraps the MyCareNet MedicalHouseMembership v1 service (`endpoint.mcn.medicalhousemembership`, production `https://services.ehealth.fgov.be/MyCareNet/MHM/v1`). A medical house (maison médicale / wijkgezondheidscentrum) uses it to register a patient's subscription with the patient's insurer, to cancel a subscription, or to notify the end of one. Requests are sent as signed blobs (XAdES-T). The test flag follows the endpoint URL (`-acpt`).

**Authentication:** `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/mhm/sendSubscription` | Register a patient's subscription; returns `StartSubscriptionResultWithResponse` | query `hcpNihii`, `hcpName`, `patientFirstName`, `patientLastName`, `patientGender`, `startDate`, `signatureType`, `isTrial?`, `patientSsin?`, `io?`, `ioMembership?`, `isRecovery?`, `isTestForNotify?` |
| POST | `/mhm/cancelSubscription` | Cancel a subscription; returns `CancelSubscriptionResultWithResponse` | query `hcpNihii`, `hcpName`, `patientFirstName`, `patientLastName`, `patientGender`, `reference`, `patientSsin?`, `io?`, `ioMembership?` |
| POST | `/mhm/notifySubscriptionClosure` | Notify the end of a subscription; returns `EndSubscriptionResultWithResponse` | query `hcpNihii`, `hcpName`, `patientFirstName`, `patientLastName`, `patientGender`, `reference`, `endDate`, `reason`, `decisionType`, `patientSsin?`, `io?`, `ioMembership?` |

**Notes**
- `hcpNihii` and `hcpName` identify the medical house. Identify the patient by `patientSsin` or by `io` + `ioMembership`.
- `startDate` and `endDate` are integers in `YYYYMMDD` format.
- `reference` is the subscription reference returned by `sendSubscription`.
- `isTrial`, `isRecovery` and `isTestForNotify` default to false.
- This controller has its own error handlers: a missing token returns 401, an `IllegalArgumentException` returns 400, and a SOAP fault returns 502, each with a plain-text message.

## Mediprima (`/mediprima`)

Mediprima is the system through which the CPAS/OCMW (public social welfare centres) cover medical care for people without health insurance. `consultMediprima` calls the eHealth Mediprima consultation v2 service (`endpoint.mediprimav2.consultation`, default `uddi:ehealth-fgov-be:business:mediprimaconsult:v2`, SOAP action `consultCarmedIntervention`). `consultTarificationMediprima` calls MyCareNet TarificationMediPrima (`endpoint.mcn.tarification.mediprima`). Mediprima invoices are sent through `/efact/batch` with `ioFederationCode = 690`, and their answers are read through the `/efact/mediprima/*` endpoints.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/mediprima/consultMediprima/{patientSsin}` | Consult the CPAS/OCMW coverage decision for a patient; returns `MediprimaMdaResponse` | path `patientSsin`; query `hcpQuality`, `hcpNihii`, `hcpSsin`, `hcpName`, `startDate?`, `endDate?`, `referenceDate?` |
| POST | `/mediprima/consultTarificationMediprima/{patientSsin}` | Consult Mediprima tariffs for nomenclature codes; returns `TarificationMediprimaConsultationResult` | path `patientSsin`; query `hcpFirstName`, `hcpLastName`, `hcpNihii`, `hcpSsin`, `date`, `traineeSupervisorSsin?`, `traineeSupervisorNihii?`, `traineeSupervisorFirstName?`, `traineeSupervisorLastName?`, `guardPostSsin?`, `guardPostNihii?`; body `List<String>` (codes) |

**Notes**
- In `consultMediprima`, `hcpQuality` is required (unlike GenIns and MDA, it has no default). `startDate`, `endDate` and `referenceDate` are epoch milliseconds and each defaults to the start of today (Europe/Brussels).
- In `consultTarificationMediprima`, `date` is required and uses the `YYYYMMDD` format. Unlike `/tarif`, this endpoint has no `gmdNihii`, `justification`, `anatomy` or `relatedService` parameters.

## Mediprima UMA (`/mediprimaUma`)

Wraps the eHealth MediPrima UMA v1 service (`endpoint.mediprimauma`, production `https://services.ehealth.fgov.be/MediPrima/UMA/v1`). A physician uses it to create, search and delete Urgent Medical Aid (*Aide Médicale Urgente* / *Dringende Medische Hulp*) attestations. The attestation is what allows the CPAS/OCMW to cover care for a person without legal residence.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/mediprimaUma/sendUrgentMedicalAidAttestation/{patientSsin}` | Create an UMA attestation | path `patientSsin`; query `hcpNihii`, `hcpSsin`, `hcpName`, `hcpFirstName`, `medicalCover`, `startDate`, `endDate` |
| POST | `/mediprimaUma/searchUrgentMedicalAidAttestation/{patientSsin}` | Search existing attestations | path `patientSsin`; query `hcpNihii`, `hcpSsin`, `hcpName`, `hcpFirstName`, `medicalCover?`, `startDate?`, `endDate?`, `attestationNumber?` |
| POST | `/mediprimaUma/deleteUrgentMedicalAidAttestation/{patientSsin}` | Delete an attestation | path `patientSsin`; query `hcpNihii`, `hcpSsin`, `hcpName`, `hcpFirstName`, `attestationNumber` |

**Notes**
- `hcpName` is sent as the physician's last name.
- Dates use the `YYYYMMDD` format. If a date is missing or cannot be parsed, it is silently replaced by the start of today (Europe/Brussels), and this also happens for the optional search dates. Validate dates on the client side.
- Deletion uses POST, not DELETE.

## Global Medical File (`/gmd`)

Wraps the services around the Global Medical File (DMG / GMD, *Dossier Médical Global* / *Globaal Medisch Dossier*):
- Consultation calls eHealth GlobalMedicalFileConsultation v1 (`endpoint.dmg.consultation.v1`).
- Notification calls GlobalMedicalFileNotification v1 (`endpoint.dmg.notification.v1`).
- Registration of a GP as GMD holder calls MyCareNet Registration v1 (`endpoint.mcn.registration`, SOAP action `RegisterToMycarenetService`).
- The list of patients whose GMD the GP holds, and the insurers' GMD messages, come through MyCareNet GenAsync (`endpoint.genericasync.dmg.v1`).

The test flag follows the corresponding endpoint URL (`-acpt`).

**Authentication:** `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/gmd/register/{oa}` | Register the GP as GMD holder with an insurer, with the bank account for GMD fees; returns `DmgRegistration` | path `oa`; query `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `bic`, `iban` |
| GET | `/gmd` | Consult the GMD status of a patient (current holder and period); returns `DmgConsultation` | query `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientSsin?`, `patientGender?`, `oa?`, `regNrWithMut?`, `requestDate?` |
| POST | `/gmd/notify/{nomenclature}` | Notify a GMD opening or extension, triggered by the given nomenclature code; returns `DmgNotification` | path `nomenclature`; query `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientSsin?`, `oa?`, `regNrWithMut?`, `patientFirstName?`, `patientLastName?`, `patientGender?`, `requestDate?`, `traineeSupervisorSsin?`, `traineeSupervisorNihii?`, `traineeSupervisorFirstName?`, `traineeSupervisorLastName?` |
| POST | `/gmd/reqlist` | Ask asynchronously for the list of patients whose GMD the GP holds; returns `GenAsyncResponse` | query `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `oa?` (all insurers when omitted), `requestDate?` |
| POST | `/gmd/messages` | Fetch pending GMD messages; returns `DmgsList` | query `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`; body `List<String>?` (message names, optional) |
| POST | `/gmd/confirm/messages` | Confirm processed GMD messages | query `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`; body `List<String>` (message hashes) |
| POST | `/gmd/confirm/acks` | Confirm processed acknowledgements | query `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`; body `List<String>` (ack hashes) |

**Notes**
- Identify the patient by `patientSsin`, or by `oa` + `regNrWithMut` (registration number with the insurer).
- `requestDate` is in epoch milliseconds and defaults to now.
- The GMD path is `/gmd`, even though the controller class is named `DmgController`.
- `DmgsList` returned by `/gmd/messages` groups everything that came back: `lists` (answers to `/gmd/reqlist`), `inscriptions`, `closures` and `extensions` (GMD changes that the insurers push, for example when another GP takes over a patient) and `acks`. Each message carries a `valueHash` that must be sent back to `/gmd/confirm/messages` or `/gmd/confirm/acks`, otherwise it is delivered again.
- Typical list flow: `POST /gmd/reqlist`, then (after the insurers have processed it) `POST /gmd/messages`, then the two confirmation calls.
- Registration and consultation errors are mapped through the `/be/errors/Dmg*Errors.json` catalogues.
