# Hubs, patient consent and therapeutic links

This chapter covers the parts of the Freehealth Connector (FHC) that give access to shared patient data in Belgium. Clinical documents (SUMEHR, medication schemes, reports, ...) are stored on the regional hubs (RSW, Abrumet/RSB, CoZo, Vitalink) and reached through the eHealth hub/metahub (IntraHub v3) protocol, exposed under `/hub`. Access to those documents requires a registered patient consent (informed consent for electronic data sharing) and a therapeutic link between the care provider and the patient. Both can be managed either at hub level (`/hub/consent`, `/hub/therlink`) or directly with the national eHealth Consent and Therapeutic Link services (`/consent`, `/therlink`).

- [Hub (`/hub`)](#hub-hub)
- [Consent (`/consent`)](#consent-consent)
- [Therapeutic links (`/therlink`)](#therapeutic-links-therlink)

## Hub (`/hub`)

Wraps the eHealth "hubservices core v3" (IntraHub) SOAP protocol implemented by each Belgian regional hub. It is used to register patients in a hub, manage hub-level patient consents and therapeutic links, list, read, publish and revoke KMEHR transactions and transaction sets (medication schemes), manage per-document access rights and read the patient audit trail. Every call is sent to the hub whose service URL is given in `endpoint`; with `isGlobal=true`, a transaction search is propagated by that hub to the other hubs.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are required on every endpoint except `POST /hub/convertKmehrXMLtoJSON`, which takes no header.

### Common parameters

All endpoints except `POST /hub/convertKmehrXMLtoJSON` take the following query parameters. They are not repeated in the tables below.

| Parameter | Required | Description |
|---|---|---|
| `endpoint` | yes | Service URL of the target hub (see the table of hubs below). |
| `hcpLastName`, `hcpFirstName` | yes | Name of the requesting care provider. |
| `hcpNihii` | yes | NIHII of the requesting care provider. It is a path variable for `/hcpconsent/{hcpNihii}` and `/therlink/{hcpNihii}/{patientSsin}`, a query parameter elsewhere. |
| `hcpSsin` | yes | SSIN of the requesting care provider. |
| `hcpZip` | yes | Postal code of the care provider's work address; FHC derives the NIS code from it. |
| `hubPackageId` | no | End-user software identifier assigned by the hub (usually different in acceptance and production). Falls back to the `hub.package.id` configuration property, then to `ACC_`. |

The care provider type sent in the request author (`persphysician`, `persnurse`, `persdentist`, `persphysiotherapist`, `persmidwife`) is derived from the quality of the SAML token.

### Hubs referenced in the code

| Hub | Example `endpoint` | Hub id (`hubId` / `externalHubId`) | Transaction id `sl` |
|---|---|---|---|
| RSW (Réseau Santé Wallon) | `https://hub.reseausantewallon.be/HubServices/IntraHub/V3/IntraHub.asmx` | `1990000035` | `RSWID` |
| Abrumet / RSB (Réseau Santé Bruxellois) | `https://hub.abrumet.be/hubservices/intrahub/v3/intrahub.asmx` | `1990000728` | `RSBID` |
| CoZo | `https://services.cozo.be/IntrahubService/servicev3.asmx` | `1990000134` | not mapped |
| Vitalink | `https://vitalink.ehealth.fgov.be/vpmg/vitalink-gateway/IntraHubService` | `1990001916` | `vitalinkuri` |

### Endpoints

#### Patients and care provider

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/hub/patient/{lastName}/{patientSsin}` | Create or update the patient in the hub. Returns the patient as known by the hub. | path `lastName`, `patientSsin`; query `firstName`, `gender` (`Gender`), `dateOfBirth` (YYYYMMDD) |
| GET | `/hub/patient/{patientSsin}` | Read the patient record from the hub. | path `patientSsin` |
| GET | `/hub/hcpconsent/{hcpNihii}` | Read the care provider's own consent with the hub, i.e. whether the provider is allowed to use it. Returns `HcPartyConsentDto`. | path `hcpNihii` |

#### Patient consent (hub level)

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/hub/consent/{patientSsin}` | Register the patient's consent in the hub. | path `patientSsin`; `patientEidCardNumber?`, `patientIsiCardNumber?` (proof of presence) |
| GET | `/hub/consent/{patientSsin}` | Read the patient's consent. Returns a `Consent`, or an empty body if none is registered. | path `patientSsin` |
| DELETE | `/hub/consent/{patientSsin}` | Revoke the patient's consent. | path `patientSsin`; `patientEidCardNumber?`, `patientIsiCardNumber?` |

#### Therapeutic links (hub level)

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/hub/therlink/{hcpNihii}/{patientSsin}` | Register a therapeutic link between the provider and the patient. | path `hcpNihii`, `patientSsin`; `patientEidCardNumber?`, `patientIsiCardNumber?`, `therLinkType?` (default `gpconsultation`), `from?`, `to?` |
| GET | `/hub/therlink/{hcpNihii}/{patientSsin}` | List the therapeutic links for the provider and patient. Returns `TherapeuticLinkMessageDto`. | path `hcpNihii`, `patientSsin`; `therLinkType?`, `from?`, `to?` |
| DELETE | `/hub/therlink/{hcpNihii}/{patientSsin}` | Revoke a therapeutic link. | path `hcpNihii`, `patientSsin`; `patientEidCardNumber?`, `patientIsiCardNumber?`, `therLinkType?` (default `gpconsultation`) |

#### Transactions (documents, SUMEHR, ...)

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/hub/list/{patientSsin}` | List the patient's transactions. Returns `List<TransactionSummaryDto>` (ids, codes, author, dates). | path `patientSsin`; `from?`, `to?`, `authorNihii?`, `authorSsin?`, `isGlobal?` (default `false`), `breakTheGlassReason?`, `transactionTypes?` (list of CD-TRANSACTION codes) |
| GET | `/hub/t/{ssin}/{sv}/{sl}` | Read one transaction as a KMEHR XML message (`application/xml`). | path `ssin`, `sv`, `sl`; query `id`; `breakTheGlassReason?`, `externalHubId?`, `externalHubName?` |
| GET | `/hub/t/{ssin}/{sv}/{sl}/kmehr` | Same as above, with the `Kmehrmessage` serialised as JSON. | same as above |
| POST | `/hub/t/{hubId}/{patientSsin}` | Publish a transaction. Body: KMEHR message, `application/xml`. Returns `PutTransactionResponseDto` (assigned ids and errors). | path `hubId` (number), `patientSsin`; body KMEHR XML; `hubApplication?` (legacy, leave empty) |
| DELETE | `/hub/t/{ssin}/{sv}/{sl}` | Revoke (delete) a transaction the provider is entitled to remove. Returns the hub response as an XML string. | path `ssin`, `sv`, `sl` (`RSWID`, `RSBID` or `vitalinkuri`); query `id`; `breakTheGlassReason?` |

#### Transaction sets (medication schemes)

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/hub/ts/{ssin}/{sv}/{sl}` | Read a transaction set, typically the medication scheme, as KMEHR XML. | path `ssin`, `sv`, `sl`; query `id`; `breakTheGlassReason?`, `externalHubId?`, `externalHubName?` |
| GET | `/hub/ts/{ssin}/{sv}/{sl}/kmehr` | Same as above, with the `Kmehrmessage` serialised as JSON. | same as above |
| POST | `/hub/ts/{hubId}/{patientSsin}` | Publish a transaction set (medication scheme). Body: KMEHR message, `application/xml`. Returns `PutTransactionSetResponse`. | path `hubId` (number), `patientSsin`; body KMEHR XML; `hubApplication?` (legacy) |

#### Access rights

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/hub/access` | Allow or disallow another care provider's access to a transaction. Returns `PutAccessRightResponse`. | `sv`, `sl`, `value` (transaction id), `accessRight` (`allow` / `disallow`), `accessNihii?`, `accessSsin?` |
| GET | `/hub/access` | Read the access rights set on a transaction. Returns `GetAccessRightResponse`. | `sv`, `sl`, `value` |
| DELETE | `/hub/access` | Remove an access right from a transaction. Returns `RevokeAccessRightResponse`. | `sv`, `sl`, `value`, `accessNihii?`, `accessSsin?` |

#### Audit trail and utilities

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/hub/trail` | Read the audit trail (who accessed what). Returns `GetPatientAuditTrailResponse`. | `ssin?`, `from?`, `to?`, `authorNihii?`, `authorSsin?`, `sv?`, `sl?`, `id?`, `breakTheGlassReason?`, `isGlobal?` |
| POST | `/hub/convertKmehrXMLtoJSON` | Local utility: parse a KMEHR XML body and return it as JSON. No eHealth call. | body KMEHR XML (`application/xml`) |

### Notes

- **Dates** (`dateOfBirth`, `from`, `to`) are `Long` values in `YYYYMMDD` format, for example `20250101`.
- **Transaction identifiers.** `GET /hub/list/{patientSsin}` returns each transaction's KMEHR `ids`. The `LOCAL` id supplies `sv`, `sl` and `value`; pass `value` as `id` (or `value` for `/hub/access`) to the read, revoke and access-rights endpoints.
- **SUMEHR and medication schemes** have no dedicated endpoints. A SUMEHR is a KMEHR transaction (published with `POST /hub/t/...`, found with `transactionTypes=sumehr`). A medication scheme is a transaction set (`/hub/ts/...`).
- **Hub id and application for writes.** In `POST /hub/t/{hubId}/...` and `POST /hub/ts/{hubId}/...`, `hubId` and `hubApplication` identify the hub's encryption token (ETK, identifier type EHP) in the eHealth KeyDepot. The message is encrypted for that ETK. `hubApplication` defaults to an empty string.
- **Reading from another hub.** Set `externalHubId` and `externalHubName` when the transaction is held by a hub other than the one addressed by `endpoint`. They are added to the transaction author as the hub party.
- **Revoking transactions** only supports `sl` values `RSWID`, `RSBID` and `vitalinkuri`. The hub id is derived from `sl`, and any other value is rejected with an error.
- **Break the glass.** `breakTheGlassReason` requests access without a therapeutic link. It must be 10 to 200 characters long, otherwise the request is rejected.
- **Proof of presence.** For hub consent and therapeutic-link registration or revocation, `patientEidCardNumber` / `patientIsiCardNumber` are sent as additional patient identifiers (`EID-CARDNO` / `ISI-CARDNO`).
- **Therapeutic link dates.** `from` and `to` on `GET /hub/therlink/...` are accepted but currently not sent to the hub; a code comment mentions an RSW bug.
- **Author filter in `GET /hub/list`.** As implemented, the filter only uses `authorNihii` when `authorSsin` is also supplied. With `authorNihii` alone, an empty INSS identifier is sent. Supply both or neither.
- **Audit trail.** `authorNihii` and `authorSsin` must both be supplied for the author filter to apply. `isGlobal` is accepted but currently unused.
- `POST /hub/access` declares `consumes = application/xml` although it takes no body; send the request with that content type.

## Consent (`/consent`)

Wraps the national eHealth Consent service (`urn:be:fgov:ehealth:consent:protocol:v1`, endpoint property `endpoint.wsconsent`). It records the patient's informed consent to the electronic exchange of health data at federal level, which the hubs rely on. It is the national counterpart of `/hub/consent`. `/hub/consent` goes through a regional hub (the `endpoint` parameter), while `/consent` calls eHealth directly and needs no hub parameters.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are required on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/consent/{patientSsin}` | Register the patient's consent. Returns `ConsentMessageDto`. | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientFirstName`, `patientLastName`; `eidCardNumber?`, `isiCardNumber?` |
| GET | `/consent/{patientSsin}` | Read the patient's current consent. Returns `ConsentMessageDto`. | path `patientSsin`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientFirstName`, `patientLastName` |
| POST | `/consent/revoke` | Revoke an existing consent. Returns `ConsentMessageDto`. | body `ConsentTypeDto`; `hcpNihii`, `hcpSsin`, `hcpFirstName`, `hcpLastName`; `eidCardNumber?`, `isiCardNumber?` |

**Notes**

- Registration always sends a consent of type `retrospective` (CD-CONSENT). The signing date is the current time.
- To revoke a consent, first read it with `GET /consent/{patientSsin}`, then send its `consent` (`ConsentTypeDto`: `patient`, `cds`, `signdate`, `revokedate`, `author`) as the body of `POST /consent/revoke`.
- `ConsentMessageDto` carries a completion flag and the eHealth errors. When registration succeeds, it also contains the registered consent.
- `eidCardNumber` / `isiCardNumber` are optional. They are added to the patient identifiers as proof of presence.

## Therapeutic links (`/therlink`)

Wraps the national eHealth Therapeutic Link service (`urn:be:fgov:ehealth:therlink:protocol:v1`, endpoint property `endpoint.therlink`). It records, queries and revokes the therapeutic relationship between a care provider and a patient at federal level. It is the national counterpart of `/hub/therlink`, which registers the same kind of link through a regional hub.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are required on every endpoint.

**Endpoints**

In the table, "HCP/patient identity" stands for the query parameters `hcpSsin`, `hcpFirstName`, `hcpLastName`, `patientFirstName`, `patientLastName`, plus `hcpNihii` and `patientSsin` when they are not in the path. All of them are required.

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/therlink/check/{patientSsin}/{hcpNihii}` | Check whether a therapeutic link exists. Returns a `HasTherapeuticLinkMessage` (completion flag, boolean `result`, errors). | path `patientSsin`, `hcpNihii`; HCP/patient identity; `eidCardNumber?`, `isiCardNumber?`, `startDate?`, `endDate?`, `type?` (default `gpconsultation`) |
| GET | `/therlink/{patientSsin}/{hcpNihii}` | List the therapeutic links between the provider and the patient. Returns `TherapeuticLinkMessageDto`. | path `patientSsin`, `hcpNihii`; HCP/patient identity; `eidCardNumber?`, `isiCardNumber?`, `startDate?`, `endDate?`, `type?`, `sign?` |
| POST | `/therlink/query` | List therapeutic links matching a template link. Returns `TherapeuticLinkMessageDto`. | body `TherapeuticLinkDto`; `sign?` |
| POST | `/therlink/check` | Check that a given link exists. Returns the matching `TherapeuticLinkDto`, or an empty body. | body `TherapeuticLinkDto` |
| POST | `/therlink/register` | Register a therapeutic link. Returns `TherapeuticLinkMessageDto`. | HCP/patient identity (including `hcpNihii`, `patientSsin`); `eidCardNumber?`, `isiCardNumber?`, `start?`, `end?`, `therLinkType?` (default `gpconsultation`), `comment?`, `sign?`, `proofType?` |
| POST | `/therlink/revoke/{patientSsin}/{hcpNihii}` | Find the link described by the parameters and revoke it. Returns `TherapeuticLinkMessageDto`. | path `patientSsin`, `hcpNihii`; HCP/patient identity; `eidCardNumber?`, `isiCardNumber?`, `start?`, `end?`, `therLinkType?`, `comment?`, `sign?`, `proofType?` |
| POST | `/therlink/revoke` | Revoke the link given in the body. Returns `TherapeuticLinkMessageDto`. | body `TherapeuticLinkDto`; `sign?`, `proofType?` |

**Notes**

- `TherapeuticLinkDto` has the fields `patient` (`KmehrPatientDto`), `hcParty` (`HcPartyDto`), `type`, `startDate`, `endDate` (epoch milliseconds), `comment` and `status`.
- **Proof of presence.** `proofType` takes a `ProofTypeValues` constant name: `EIDREADING`, `SISREADING`, `ISIREADING`, `EIDSIGNING`, `EIDENCODING_HOUSECALL`, `EIDENCODING_NOCARD` or `EIDENCODING_TECHPROBLEM`. If it is omitted, FHC sends `eidreading` when an `eidCardNumber` is present and `isireading` when an `isiCardNumber` is present. With no card number, no proof is sent.
- `sign` is accepted, but it does not currently change the proof that is sent.
- The provider type in the link (for example `persphysician`, `persnurse`) is derived from the SAML token quality, as for the hub endpoints.
- **National or hub level.** Use `/consent` and `/therlink` to work with the federal eHealth registries. Use `/hub/consent/...` and `/hub/therlink/...` when the regional hub expects consent and links to be recorded through its own interface.
