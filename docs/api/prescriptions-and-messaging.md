# Recip-e, eHealthBox, Vaccinnet and RSW

This chapter covers the FHC endpoints for electronic prescriptions (Recip-e), the eHealthBox secure mailbox, the Flemish Vaccinnet vaccination registry and the Walloon health network (Réseau Santé Wallon, RSW) FHIR gateway. Unless stated otherwise, every call uses a keystore uploaded through `/sts/keystore` (`X-FHC-keystoreId`), the passphrase of that keystore (`X-FHC-passPhrase`) and a SAML token obtained from `/sts/token` (`X-FHC-tokenId`). Identifiers in the examples below (`<ssin>`, `<nihii>`, `<rid>`) are placeholders.

- [Recip-e (`/recipe`)](#recip-e-recipe)
- [eHealthBox (`/ehbox`)](#ehealthbox-ehbox)
- [eHealthBox V3 (`/ehboxV3`)](#ehealthbox-v3-ehboxv3)
- [Vaccinnet (`/vaccinnet`)](#vaccinnet-vaccinnet)
- [RSW FHIR (`/rsw/fhir`)](#rsw-fhir-rswfhir)

## Recip-e (`/recipe`)

Wraps the Recip-e **prescriber** web service, version 4 (`endpoint.recipe.prescriber.v4`, UDDI `uddi:ehealth-fgov-be:business:recip-eprescriber:v4`), through `RecipeV4Service` / `PrescriberIntegrationModuleV4Impl`. It lets a prescriber (doctor, dentist, nurse, ...) create electronic prescriptions, list and read their own prescriptions for a patient, revoke them, change their visibility and feedback settings, notify a pharmacist, and read the feedback sent back by pharmacists. Request payloads are sealed for the Recip-e ETK; prescription content is encrypted with a KGSS key bound to the patient, and decrypted with KGSS on retrieval.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are required on every endpoint except `GET /recipe/gal/{galId}`, which takes no headers, and `GET /recipe/{rid}`, which takes only `X-FHC-keystoreId` and `X-FHC-passPhrase`. All authenticated endpoints also accept the optional headers `X-FHC-vendorName` and `X-FHC-packageVersion`, which identify the calling software to Recip-e (for creation endpoints, `vendorName`/`packageName` in the body take precedence over these headers).

**Endpoints**

Creation

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/recipe` | Create one prescription; returns a `Prescription` with its RID and the generated KMEHR message | `hcpQuality`, `hcpNihii`, `hcpSsin?`, `hcpName?`; body `PrescriptionRequest` |
| POST | `/recipe/v4` | Same as `POST /recipe` (identical implementation, explicit v4 path) | same as `POST /recipe` |
| POST | `/recipe/batch` | Create several prescriptions in one call, one per entry of `medicationsBatches`; created in parallel; returns `List<Prescription>` | `hcpQuality`, `hcpNihii`, `hcpSsin?`, `hcpName?`; body `PrescriptionsRequest` |

Consultation (prescriber side)

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/recipe/patient` | List the prescriber's open (not yet delivered, not revoked, not expired) prescriptions for a patient; returns `List<Prescription>` | `hcpNihii`, `patientId` (patient SSIN), `hcpQuality?`, `hcpSsin?`, `hcpName?` |
| GET | `/recipe/patient/all` | List the patient's prescriptions with filters and paging; returns `ListStructuredPrescriptionsResult` (decrypted content included) | `hcpNihii`, `patientId`, `prescriberId?`, `from?`, `toInclusive?` (epoch ms), `statuses?` (comma-separated `PrescriptionStatus` values), `expiringFrom?`, `expiringToInclusive?`, `pageYear?`, `pageMonth?`, `pageNumber?`, `hcpQuality?`, `hcpSsin?`, `hcpName?` |
| GET | `/recipe/{rid}/status` | Current status of a prescription (open, delivered, revoked, expired...) | path `rid`; `hcpNihii` |
| GET | `/recipe/prescription/{rid}` | Retrieve and decrypt the prescription; returns the KMEHR message as `RecipeKmehrmessageType` | path `rid`; `hcpNihii`, `hcpQuality?`, `hcpSsin?`, `hcpName?` |
| GET | `/recipe/{rid}` | Return a prescription with its feedbacks (`PrescriptionFullWithFeedback`) from the FHC in-memory cache; no call to Recip-e | path `rid`; headers `X-FHC-keystoreId`, `X-FHC-passPhrase` |

Lifecycle and settings

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| DELETE | `/recipe/{rid}` | Revoke a prescription; it can no longer be delivered | path `rid`; `hcpNihii`, `reason`, `hcpQuality?`, `hcpSsin?`, `hcpName?` |
| PUT | `/recipe/{rid}/feedback/{feedbackFlag}` | Allow (`true`) or forbid (`false`) pharmacist feedback on a prescription | path `rid`, `feedbackFlag`; `hcpNihii`, `hcpQuality?`, `hcpSsin?`, `hcpName?` |
| PUT | `/recipe/{rid}/vision` | Set who can see the prescription; returns `PutVisionResult` | path `rid`; `vision`, `visionOthers?` (`open`, `locked`, `gmd_prescriber`) |

Notifications and feedback

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/recipe/notify/{rid}` | Send a notification about a prescription to an executor (pharmacist); the prescription's KMEHR message is fetched and embedded in the notification together with the text | path `rid`; `hcpQuality`, `hcpNihii`, `hcpSsin`, `hcpName`, `patientId`, `executorId`, `text` (all required) |
| GET | `/recipe/all/feedbacks` | List all feedback messages sent by pharmacists on the prescriber's prescriptions; returns `List<Feedback>` | `hcpQuality?`, `hcpNihii?`, `hcpSsin?`, `hcpName?` |

Utilities

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/recipe/gal/{galId}` | Map a SAM galenic form code to the KMEHR administration unit code (`Code`); local lookup, no authentication | path `galId` |

**Notes**

- **Lifecycle.** A prescription is created with `POST /recipe` (or `/recipe/v4`, `/recipe/batch`), which returns its RID. The prescriber can then follow it with `/recipe/{rid}/status`, read it back with `/recipe/prescription/{rid}`, notify a pharmacist with `/recipe/notify/{rid}`, change its visibility (`/vision`) or feedback permission (`/feedback/{flag}`), and revoke it with `DELETE /recipe/{rid}`. Pharmacist feedback is read with `/recipe/all/feedbacks`.
- **Prescriber vs. executor.** This controller only implements the prescriber side of Recip-e. The executor (pharmacy) web service is not exposed. Executors appear only as the target of `/recipe/notify/{rid}` (`executorId`) and as the authors of the feedbacks returned by `/recipe/all/feedbacks`.
- **v4 vs. legacy.** All endpoints call the Recip-e v4 service. `POST /recipe` and `POST /recipe/v4` share the same code. The v1 prescriber endpoint (`endpoint.recipe.prescriber`) is still configured in the properties files, but this controller does not use it. No endpoint is annotated `@Deprecated`.
- **KMEHR version.** Prescriptions are built as KMEHR messages with standard `20190301` and a `samv2` external source (whose version is `samVersion` from the body), then validated against the Recip-e rules before they are sent. `GET /recipe/prescription/{rid}` returns the decrypted content deserialized with the KMEHR `20161201` Recip-e binding (`RecipeKmehrmessageType`).
- **`PrescriptionRequest` body.** `patient`, `hcp`, `feedback` and `medications` are required. The optional fields are `prescriptionType` (inferred from the medications when missing), `samVersion`, `deliveryDate` and `expirationDate` (fuzzy dates), `vision`, `visionOthers`, `lang`, and the vendor fields `vendorName`, `packageName`, `packageVersion`, `vendorEmail`, `vendorPhone`. The body also has `notification` and `executorId` fields, but the v4 creation code ignores them. To notify a pharmacist, call `/recipe/notify/{rid}` after creation. `PrescriptionsRequest` has the same fields, except that `medications` is replaced by `medicationsBatches: List<List<Medication>>`.
- **Ignored parameters.** Most endpoints accept `hcpSsin` and `hcpName` (and some accept `hcpQuality`), but these values are not forwarded to Recip-e. The prescriber identity comes from the SAML token and `hcpNihii`.
- **`GET /recipe/{rid}` is cache-only.** It returns the prescriptions and feedbacks that `GET /recipe/patient` stored in the FHC process's in-memory cache. The cache is partitioned by keystore: only prescriptions loaded with the same keystore are returned, and the passphrase must open that keystore. If `GET /recipe/patient` has not run with that keystore on the same FHC instance, the endpoint returns an empty body. Cache entries expire after 12 hours.

## eHealthBox (`/ehbox`)

Wraps the eHealthBox v3 consultation and publication web services (`endpoint.ehbox.consultation.v3`, `endpoint.ehbox.publication.v3`) through `EhboxService`. It is used to read the mailbox of the authenticated healthcare party or organisation, fetch full messages with their documents and annexes, send messages, and move or delete messages. This is the original API: the responses are unwrapped, so errors are reduced to a boolean or an `ErrorMessage`. For new integrations, prefer [`/ehboxV3`](#ehealthbox-v3-ehboxv3).

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are required on every endpoint. The mailbox that is read is the one identified by the SAML token.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/ehbox` | Box information (box id, quality, current and maximum size, messages in stand-by); returns `BoxInfo` | none |
| GET | `/ehbox/{boxId}` | List the messages of a box; returns `List<Message>` | path `boxId` (`INBOX`, `SENTBOX`, `BININBOX`, `BINSENTBOX`); `limit?` |
| GET | `/ehbox/{boxId}/{messageId}` | Full message (content, annexes) decrypted with the main keystore; returns `Message`, or an `ErrorMessage` on failure | path `boxId`, `messageId` |
| POST | `/ehbox/{boxId}` | Same as the GET list, but also tries the given alternate keystores when decrypting | path `boxId`; `limit?`; body `AltKeystoresList` |
| POST | `/ehbox/{boxId}/{messageId}` | Same as the GET full message, with alternate keystores | path `boxId`, `messageId`; body `AltKeystoresList` |
| POST | `/ehbox` | Send a message; returns `true` on success (status `100`) | body `DocumentMessage`; `publicationReceipt?`, `receptionReceipt?`, `readReceipt?` (default `false`) |
| POST | `/ehbox/move/from/{source}/to/{destination}` | Move messages between boxes; returns `Boolean` | path `source`, `destination`; body `List<String>` (message ids) |
| POST | `/ehbox/move/from/{source}` | Delete messages from a box (eHealthBox `deleteMessage`); returns `Boolean` | path `source`; body `List<String>` (message ids) |

**Notes**

- **ETK encryption for recipients.** When `DocumentMessage.encrypted` is `true`, FHC gets the ETK of each destination from the eHealth KeyDepot, using the `Addressee` fields `identifierType`, `id` and `applicationId`. It also adds the sender's own ETK, so the copy in `SENTBOX` can still be read. The document, the free text and the annexes are then sealed for all of these ETKs. If a recipient has no ETK, sending fails. Destinations whose `id` is `ALL` are not looked up in the KeyDepot. When `encrypted` is `false`, the content is sent unencrypted.
- **Alternate keystores.** `AltKeystoresList` is `{ "keystores": [ { "uuid": "<keystore-uuid>", "passPhrase": "..." } ] }`, and each keystore it lists must already be uploaded. Use it to read messages that were encrypted for a previous certificate of the same party.
- **Delete path.** Deletion uses `POST /ehbox/move/from/{source}` with no destination. It is not a `DELETE` request.
- **Lost message id.** `POST /ehbox` returns only a boolean, so the eHealthBox message id is lost. Use `POST /ehboxV3` to get it.

## eHealthBox V3 (`/ehboxV3`)

This controller uses the same `EhboxService` and the same eHealthBox v3 web services as `/ehbox`. It also adds sending to a secondary eHealthBox through the eH2eBox publication service (`endpoint.eh2ebox.publication.v3`, default UDDI `uddi:ehealth-fgov-be:business:eh2eboxpublication:v3`), and a query for message acknowledgment status. The main difference from `/ehbox` is the response format. Every call returns a response envelope (`MessagesResponse`, `MessageResponse`, `MessageOperationResponse`, `MessageStatusOperationResponse`), which carries the eHealth status code and any error details. `/ehbox` returns the bare payload instead.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are required on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/ehboxV3` | Box information; returns `BoxInfo` | none |
| GET | `/ehboxV3/{boxId}` | List the messages of a box; returns `MessagesResponse` | path `boxId`; `limit?` |
| GET | `/ehboxV3/{boxId}/{messageId}` | Full, decrypted message; returns `MessageResponse` | path `boxId`, `messageId` |
| POST | `/ehboxV3/{boxId}` | List messages, decrypting with alternate keystores | path `boxId`; `limit?`; body `AltKeystoresList` |
| POST | `/ehboxV3/{boxId}/{messageId}` | Full message, decrypting with alternate keystores | path `boxId`, `messageId`; body `AltKeystoresList` |
| POST | `/ehboxV3` | Send a message; returns `MessageOperationResponse` (`success`, `messageId`, `error`) | body `DocumentMessage`; `publicationReceipt?`, `receptionReceipt?`, `readReceipt?` |
| GET | `/ehboxV3/{messageId}/status/acks` | Publication, reception and read acknowledgments of a sent message; returns `MessageStatusOperationResponse` | path `messageId` |
| POST | `/ehboxV3/2ebox` | Send a message through the eH2eBox publication service; status `100` or `101` counts as success | body `DocumentMessage`; `publicationReceipt?`, `receptionReceipt?`, `readReceipt?` |
| POST | `/ehboxV3/move/from/{source}/to/{destination}` | Move messages between boxes; returns `MessageOperationResponse` | path `source`, `destination`; body `List<String>` |
| POST | `/ehboxV3/move/from/{source}` | Delete messages from a box; returns `MessageOperationResponse` | path `source`; body `List<String>` |

**Notes**

- ETK encryption, alternate keystores and deletion work as described for [`/ehbox`](#ehealthbox-ehbox).
- For `/2ebox`, destinations are sent without the first name and last name `User` element that the standard publication adds when both names are known.
- If a send fails with a SOAP fault, the fault's business code and message are mapped into `MessageOperationResponse.error`, and no HTTP error is returned. Other technical errors are reported with code `999`.

## Vaccinnet (`/vaccinnet`)

Wraps the Vaccinnet vaccination service v2.1 of the Flemish Agency for Care and Health (`endpoint.vaccinnet.vaccination.v2`, `https://www.vaccinnet.be/VaccinnetUPL/wupl/VaccinationService-2_1` in production). It is used to read a patient's registered vaccinations, register new vaccinations, and remove one.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` are required. Every call also needs the query parameters `softwareId` and `vaccinnetId`, the identifiers of the calling software and of the Vaccinnet user issued by Vaccinnet.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/vaccinnet/{patientId}` | Vaccinations of a patient given on or after a date; returns `GetVaccinationsResponseType` | path `patientId` (SSIN); `softwareId`, `vaccinnetId`, `since` (fuzzy date, e.g. `20240101`) |
| POST | `/vaccinnet/{patientId}` | Register vaccinations; returns `AddVaccinationsResponseType` | path `patientId`; `hcpNihii`, `hcpName`, `hcpQuality`, `hcpSupervisorNihii?`, `hcpSupervisorName?`, `hcpSupervisorQuality?`, `patientFirstName`, `patientLastName`, `patientDateOfBirth` (fuzzy date), `softwareId`, `vaccinnetId`, `patientGender?` (`male`, `female`, `unknown`, `changed`); body `List<VaccineInjection>` |
| DELETE | `/vaccinnet/{patientId}/{vaccinationId}` | Remove a registered vaccination; returns `RemoveVaccinationResponseType` | path `patientId`, `vaccinationId`; `softwareId`, `vaccinnetId` |

**Notes**

- Each `VaccineInjection` (`intendedCd`: CNK code, `intendedName`, `date`: fuzzy date, `batch`) becomes a separate KMEHR message (standard `20120701`), sent base64-encoded. Each message has a `vaccination` transaction addressed to the VAZG. If supervisor parameters are given, the supervisor is the author of the transaction. Otherwise the author is the HCP.
- The patient's sex in the generated KMEHR comes from `patientGender`. When the parameter is omitted it is sent as `unknown`; an unrecognised value returns an error.

## RSW FHIR (`/rsw/fhir`)

Queries the Réseau Santé Wallon FHIR proxy (the "careset" API) for a patient's data. The implementation (`RswFhirServiceImpl`) first requests an OAuth2 access token with the client-credentials grant from the RSW identity server (`.../is4acc/careset/v1/token`, scope `api.rsw.clientid.fhir.careset`). It then checks the returned JWT against the RSW JWKS. Next, it searches `AllergyIntolerance` resources by patient SSIN on `.../proxy/fhir/caresets/`. The response is a signed and encrypted S/MIME payload, which FHC decrypts with the private key from the caller's keystore. The endpoint returns the resulting FHIR `Bundle`. The RSW base URLs are read from the connector properties `endpoint.rsw.fhir` (FHIR proxy) and `endpoint.rsw.idp` (identity server). The acceptance file points them to `jacc.reseausantewallon.be`. The production file leaves them commented out, and the endpoint fails with an explicit error until they are set.

**Authentication:** no SAML token is used. The following headers are required:

- `X-FHC-keystoreId` and `X-FHC-passPhrase`: the keystore whose private key is used to decrypt the RSW response, which is encrypted for that actor.
- `X-FHC-RSW-actor-id`: the OAuth2 `client_id` assigned by RSW to the calling software or actor.
- `X-FHC-RSW-actor-secret`: the OAuth2 `client_secret` that goes with that client id.
- `X-FHC-RSW-actor-type`: the type of the encryption actor, sent to RSW as the `SENDER-ENCRYPTION-ACTOR-TYPE` header, together with the NIHII from the path as `SENDER-ENCRYPTION-ACTOR-ID`.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/rsw/fhir/{nihii}/{patientSsin}` | Get the patient's `AllergyIntolerance` careset from RSW as a FHIR `Bundle` | path `nihii` (requesting HCP, also sent as `client_nihii`), `patientSsin`; RSW headers above |

**Notes**

- When rate limiting is enabled (`fhc.ratelimit.enabled`), `/rsw/**` is its own group, limited to 10 requests per window.
- The CMS signature of the response is computed, but the result is not enforced.
