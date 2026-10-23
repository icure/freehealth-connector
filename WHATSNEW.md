# What's New

This file summarises the user-facing changes to the Freehealth Connector REST API, with short usage notes. FHC has no numbered releases: each entry is a merged pull request, titled by its number and merge date. The most recent change is at the top. The full API reference is the Swagger UI at `/api/index.html`.

---

## #128 (pending)

### `/consultrn` is deprecated: use `/rnconsult`

The v1 National Registry endpoints are marked deprecated in the OpenAPI doc. Each has a v2 replacement, which calls the current eHealth RN PersonService and CBSS PersonService:

| `/consultrn` | Replacement |
|---|---|
| `GET /consultrn/{ssin}` | `GET /rnconsult/bySsin/{ssin}` |
| `GET /consultrn/{dateOfBirth}/{lastName}` | `GET /rnconsult/phonetically/{dateOfBirth}/{lastName}` |
| `POST /consultrn` | `POST /rnconsult` |
| `GET /consultrn/history/{ssin}` | `GET /rnconsult/history/{ssin}` |

`/rnconsult` also offers `GET /rnconsult/verifyId`, which checks an eID or ISI+ card.

### `/rnconsult` accepts what `/consultrn` accepted

- **Phonetic search:** `matchingType` is optional again. It defaults to `IGNORE_GIVENNAME` without a `firstName` and to `COMPLETE_FIRST_GIVENNAME` otherwise. `tolerance` and `limit` default to 0 and 20.
- **Gender:** only `MALE`/`M` and `FEMALE`/`F` are sent. Any other value, or none, means no gender. A search without a gender used to return only women, and a registration without one registered a woman.
- **Register:** `nationalityCode` is sent only when provided, `language` is optional, and a SOAP fault fills the `error` field.

A residential `cityCode` cannot be sent: the v2 schema only accepts a foreign residential address, which has no city code.

---

## #125 (2026-10-22)

### Exchanging a SAML token for an IAM Connect JWT: `POST /sts/token/exchange/iam`

Exchanges a SAML holder-of-key token already cached in FHC for a JWT from the IAM Connect `healthcare` realm. FHC builds the actor token and signs it with the keystore's holder-of-key key: RS256 for RSA keys, ES256/ES384/ES512 for EC keys. Clients no longer need to extract the private key and sign the actor token themselves, which failed for EC keystores.

```
POST /sts/token/exchange/iam?clientId=<IAM Connect client id>&refreshable=true
X-FHC-keystoreId: …
X-FHC-passPhrase: …
X-FHC-tokenId: …
```

- `ssin` is optional and defaults to the certificate holder SSIN found in the SAML assertion.
- With `refreshable=true`, a refresh token is returned along with the access token.
- The response is an `IamTokenResult`: access token, token types, `expiresIn`, refresh token, `refreshExpiresIn`, scope, and absolute `validity`/`refreshValidity` in epoch millis.
- An eHealth 400 is returned as 400. Other upstream errors become 502, so an eHealth 401 is not mistaken for an FHC authentication failure.

Every user must have consented once to the IAM Connect client, or eHealth answers `invalid_client - Client requires user consent`.

---

## #124 (2026-10-14)

### #120, #122 and #123 on `main`

Brings the changes of [#120](#120-2026-10-05), [#122](#122-2026-10-12) and [#123](#123-2026-10-13) from `master` onto `main`.

---

## #123 (2026-10-13)

### eAttest v3 cancellation for guard posts and other qualities

`DELETE /eattestv3/send/{patientSsin}` and its `/verbose` variant take new parameters:

- `hcpQuality`: the care provider quality (`doctor`, `guardpost`…). It defaults to `guardpost` when `guardPostNihii` is set, and to `doctor` otherwise.
- `guardPostNihii`, `guardPostSsin`, `guardPostName`: optional. When the quality is `guardpost`, the guard post is the author of the cancellation instead of the physician.

---

## #122 (2026-10-12)

### eFact ET 52: agreement number accepted without eID data

An `agreementNumber` without an `eidItem` is no longer refused for physiotherapists. The record is written with the identity zones at their defaults. The agreement number must still be exactly 20 digits.

---

## #120 (2026-10-05)

### eAgreement and MDA mailboxes: one bad message no longer fails the batch

When `POST /eagreement/async/getMessages` or `POST /mda/async/messages` gets a message that cannot be decompressed, decrypted or parsed, that message is returned on its own with a `genericErrors` entry whose `faultCode` is `DECODING_ERROR`. The other messages are still returned; the whole call used to fail. The failing stage and the first bytes of the payload are logged.

400 and 500 responses are now also logged with their stack trace.

---

## #116 (2026-09-24)

### Multimediatt schematron of 2026-07-03

The multimediatt validation uses the eHealth schematron dated 2026-07-03:

- For recipients `0820563481` and `1990002015`, the `endmoment` may be up to 12 months (minus one day) after the `beginmoment`, instead of 3 months.
- `beginmoment` and `endmoment` must contain a `date`.

---

## #113 (2026-09-09)

### eFact ACC endpoint

On acceptance, eFact async invoicing now calls `https://pilot.mycarenet.be/mycarenet-ws/async/generic/hcpfac_12`.

---

## #112 (2026-09-02)

### eAttest v3: more complete error reporting

Errors in eAttest v3 acknowledgements are reported even when they carry a MyCareNet refusal code, or another code, instead of an error code. An error that matches no known location in the request is returned with its code and with eHealth's description as message, instead of being dropped.

---

## #111 (2026-09-02)

### eAttest v3 duplicates: `inputReference`

A duplicate eAttest must reuse the `inputReference` of the original. `POST /eattestv3/send/{patientSsin}` and its `/verbose` variant take an optional `inputReference` query parameter. Without it, a new reference is generated, as before.

---

## #110 (2026-08-28)

### eAgreement: referral on argue requests

`ARGUE` requests now include the referral (`ServiceRequest`), as `ASK`, `COMPLETE_AGREEMENT` and `EXTEND` requests already did.

---

## #109 (2026-08-31)

### eFact ET 52 Z 19: the agreement number

Physiotherapists must put the eAgreement number in ET 52 zone 19, which could not be produced until now: every `567011` was rejected with 521904. `InvoiceItem` has a new optional `agreementNumber`, exactly 20 digits:

```json
{ "codeNomenclature": 567011, "agreementNumber": "12345678901234567890", "eidItem": { … } }
```

An ET 52 is written as soon as `eidItem` or `agreementNumber` is set; when both are, they share one record. Without an agreement number, zone 19 is twenty zeroes, so existing records are unchanged byte for byte.

---

## #104 (2026-07-30)

### eAttest v3 for physiotherapists: decision reference

The decision reference is sent as an `id` of the item instead of a `cd`, as MyCareNet expects for physiotherapy attests.

---

## #103 (2026-07-02)

### eAgreement: fetching responses

`POST /eagreement/async/getMessages` uses the eAgreement async service (it was using the MDA one), sends the reply ETK and asks for `eAgreement-response` messages. Each `EAgreementBatchResponse` has a new `value` field holding the FHIR `Bundle` of the response, or the whole decrypted payload when there is no bundle.

---

## #102 (2026-07-17)

### eFact ET 52 Z 17: justification document number

`EIDItem` has a new numeric `justificationDocumentNumber` (`0` by default), written in ET 52 zone 17 (25 positions). Zone 16, the read value, is now 15 positions long. `deviceType` `0` (unknown) is accepted.

---

## #101 (2026-06-09)

### `GET /admin/bcp` without the admin role

The business continuity (BCP) status endpoint no longer requires `ROLE_ADMIN`.

---

## #100 (2026-06-04)

### eFact ET 52: mandatory identity check

ET 52 follows the new INAMI instructions for the identity document check. `EIDItem` fields:

- `readType` (zone 9): `1` chip, `2` barcode, `3` datamatrix, `4` manual, `A` electronic (itsme).
- `deviceType` (zone 10): `1` eID, `2` ISI, `3` ISI+, `4` Kids-ID, `5` foreigner card, `6` itsme, `7` vignette.
- `vignetteReason` (zone 11): only with `deviceType` `7`.
- `manualEntryReason` (zone 3): required with `readType` `4`. For the deferred reasons (3 to 6 and 8), `readDate` must be null and `readHour` 0.
- `readValue` (zone 16): the value read from the document. The field was called `readvalue`.

Invalid combinations are refused with a 400 before anything is written.

---

## #99 (2026-04-01)

### Cluster-wide rate limiting

FHC can limit the number of requests per keystore, shared across the cluster through Hazelcast. It is off by default; enable it with `fhc.ratelimit.enabled=true`.

- The limit applies per endpoint group (`/genins/**`, `/mda/**`, `/eattest*/**`…) over a 60 second window: 50 requests for `genins`, `mda`, `ehbox`, `eattest`, `recipe` and `tarif`, 10 for the other groups, and 20 for requests without a keystore or token header.
- Responses carry `X-RateLimit-Limit`, `X-RateLimit-Remaining` and `X-RateLimit-Reset`. Over the limit, FHC answers `429` with `Retry-After`.
- `/sts/keystore` and `/actuator/**` are not limited.

Spring Boot is upgraded to 3.5.13.

---

## #97 (2026-04-01)

### Mediprima UMA requests

Mediprima UMA (`/mediprimaUma`) requests and responses carry the right XML namespace, which eHealth requires.

---

## #96 (2026-02-18)

### MDA for re-education centres

`reeducation` is accepted as care provider quality by MDA requests. Like `guardpost` and `medicalhouse`, it sends the 11-digit NIHII of the organisation.
