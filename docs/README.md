# Freehealth Connector documentation

The Freehealth Connector (FHC) is a multi-user REST/JSON middleware in front of the Belgian eHealth platform and MyCareNet. It embeds a trimmed, "massively multi-user" fork of the official eHealth Java connector. Software vendors call plain JSON endpoints. FHC builds, signs, encrypts and sends the SOAP/KMEHR/FHIR messages, and returns the decoded responses.

## Contents

- [Installation guide](installation.md): build, configuration, Docker, Helm, clustering, tests
- API reference, by domain:
  - [Authentication, administration and directories](api/authentication-and-utilities.md): STS (keystores and SAML tokens), admin/BCP, crypto, schematron, DAAS, APB, addressbook, national registry (ConsultRN, RnConsult)
  - [MyCareNet: insurability, billing and GMD](api/mycarenet-insurance-and-billing.md): GenIns, Member Data (MDA), Tarification, eFact, Medical House Membership (MHM), Mediprima / Mediprima UMA, GMD (DMG)
  - [eAttest, Chapter IV and eAgreement](api/attestations-and-agreements.md): eAttest v1/v2/v3, Chapter IV, eAgreement
  - [Recip-e, eHealthBox, Vaccinnet and RSW](api/prescriptions-and-messaging.md): electronic prescriptions, secure messaging, vaccinations, RSW FHIR
  - [Hubs, patient consent and therapeutic links](api/hubs-consent-and-therapeutic-links.md): intermuso hubs, national consent and therapeutic link services
- [Spring Boot 3.5 / virtual threads migration notes](../MIGRATION_TO_SPRING_3_5_5.md)

The live OpenAPI description is served by every instance at `/swagger-ui.html` and `/v3/api-docs`.

## Feature overview

| Domain | Base path | eHealth / MyCareNet service |
|---|---|---|
| Security token service | `/sts` | eHealth STS (SAML 1.1 holder-of-key tokens), keystore management |
| Administration | `/admin` | BCP (business continuity) endpoint status and control |
| Crypto | `/crypto` | ETEE encryption/decryption with ETKs |
| Schematron | `/schematron` | Local validation of XML (e.g. MultiMediaTT) against bundled schematrons |
| DAAS | `/daas` | Data Attribute Service |
| APB | `/apb` | Pharmacists' association services |
| Addressbook | `/ab` | eHealth addressbook (professionals and organisations) |
| National registry | `/consultrn`, `/rnconsult` | ConsultRN / RN consult (person search, SSIN history) |
| Insurability | `/genins` | MyCareNet GenIns (general insurability) |
| Member data | `/mda` | MyCareNet Member Data (asynchronous insurability) |
| Tarification | `/tarif` | MyCareNet tarification consultation |
| eFact | `/efact` | MyCareNet third-party-payer invoicing (flat-file batches) |
| Medical houses | `/mhm` | MyCareNet medical house membership |
| Mediprima | `/mediprima`, `/mediprimaUma` | CPAS/OCMW coverage (Mediprima, urgent medical aid) |
| GMD | `/gmd` | Global Medical File (DMG) consultation, notification, registration |
| eAttest | `/eattest`, `/eattestv2`, `/eattestv3` | Electronic attestations of care |
| Chapter IV | `/chap4` | Chapter IV prior authorisation (reimbursement agreements) |
| eAgreement | `/eagreement` | FHIR-based eAgreement (physiotherapy, etc.) |
| Recip-e | `/recipe` | Electronic prescriptions |
| eHealthBox | `/ehbox`, `/ehboxV3` | Secure messaging between healthcare actors |
| Vaccinnet | `/vaccinnet` | Vaccination registry |
| RSW FHIR | `/rsw/fhir` | Réseau Santé Wallon FHIR API |
| Hubs | `/hub` | Intermuso hubs (transactions, SUMEHR, medication schemes, access rights) |
| Consent | `/consent` | National patient consent |
| Therapeutic links | `/therlink` | National therapeutic link register |

## How a request works

```
Client ──JSON/HTTP──▶ Controller ──▶ Service ──▶ eHealth connector (business/technical) ──SOAP/WS-Security──▶ eHealth / MyCareNet
                         │               │
                         │               └─ keystore, SAML token, ETK caches (Hazelcast)
                         └─ headers X-FHC-keystoreId / X-FHC-passPhrase / X-FHC-tokenId
```

The code is organised in three layers:

1. **Controllers** (`org.taktik.freehealth.middleware.web.controllers`): one Spring MVC controller per eHealth service. They validate input and read the identity headers.
2. **Services** (`org.taktik.freehealth.middleware.service`, implementations in `service.impl`): build the eHealth requests, handle signatures, encryption, KMEHR/FHIR mapping and response decoding.
3. **Connectors** (`org.taktik.connector.business.*`, `org.taktik.connector.technical.*`, Java): the embedded eHealth connector (SOAP clients, WS-Security, ETEE, timestamping, endpoint distribution/BCP).

Supporting packages: `dto` (REST payloads), `domain` (internal models), `format/efact` (eFact flat-file writer/reader), `mapper` (Jackson-based bean mapper replacing Orika), `dao` (CouchDB user store), `hazelcast` (rate-limit serialisers).

## Identity and authentication

FHC does not store eHealth certificates. Each caller brings its own:

1. **Upload the keystore.** `POST /sts/keystore` (multipart, PKCS#12) returns a `uuid`, the *keystoreId*. The keystore is kept in memory (Hazelcast, 18 h time to live) and can be re-uploaded at any time.
2. **Obtain a SAML token.** `GET /sts/token?ssin=<ssin>` with headers `X-FHC-keystoreId` and `X-FHC-passPhrase` returns a `tokenId` (12 h time to live in FHC; the eHealth token itself has its own validity).
3. **Call any service** with the three headers:

   | Header | Content |
   |---|---|
   | `X-FHC-keystoreId` | UUID returned by `/sts/keystore` |
   | `X-FHC-passPhrase` | Keystore passphrase (never stored) |
   | `X-FHC-tokenId` | UUID returned by `/sts/token` |

   The healthcare party's identity (NIHII, SSIN, quality, name) is passed as query parameters of each endpoint.

```bash
FHC=http://localhost:8090
KEYSTORE_ID=$(curl -s -F "file=@/path/to/keystore.p12;type=application/x-pkcs12" "$FHC/sts/keystore" | jq -r .uuid)
TOKEN_ID=$(curl -s "$FHC/sts/token?ssin=<SSIN>" \
  -H "X-FHC-keystoreId: $KEYSTORE_ID" -H "X-FHC-passPhrase: <passphrase>" | jq -r .tokenId)

curl -s "$FHC/genins/<patientSsin>?hcpNihii=<NIHII>&hcpSsin=<SSIN>&hcpName=<name>" \
  -H "X-FHC-keystoreId: $KEYSTORE_ID" -H "X-FHC-passPhrase: <passphrase>" -H "X-FHC-tokenId: $TOKEN_ID"
```

Optionally, clients can also send **HTTP Basic** credentials. They identify an FHC *user* (a static user from properties or a CouchDB document), which carries the vendor's MyCareNet package licence and other vendor credentials. Without it, the licence configured on the server is used. See [Installation › Users and Basic authentication](installation.md#users-and-basic-authentication).

## Cross-cutting features

- **Virtual threads and HTTP/2.** Requests run on JDK 21 virtual threads on Jetty 12, so long eHealth SOAP calls do not tie up platform threads.
- **Distributed in-memory state.** Hazelcast holds keystores, tokens, ETKs, KGSS keys and rate-limit counters, shared across a cluster.
- **Rate limiting.** Optional, cluster-wide, per keystore and per endpoint group, with standard `X-RateLimit-*` headers and HTTP 429 responses. See [Installation › Rate limiting](installation.md#rate-limiting).
- **BCP (business continuity).** The embedded endpoint distributor polls the eHealth BCP status every 60 s and switches to fallback endpoints when eHealth announces a failover. `/admin/bcp` exposes the current state.
- **Trust material.** eHealth CA, TSL, truststore and timestamping (TSA) keystores for acceptance and production are bundled and extracted to `/opt/ehealth` at startup.
- **Observability.** `/actuator/health`, Micrometer metrics (`http.server.requests` percentiles, rate-limit counters), per-request execution-time logging, and propagation of the caller's `X-User-Agent`/`User-Agent` and `X-From` headers to eHealth.
- **Compression.** JSON/XML responses of at least 1 KB are gzip-compressed.

## Environments

A running instance targets **either** eHealth acceptance **or** production, selected with the system property `org.taktik.connector.technical.config.location`. The default is acceptance. See [Installation › Choosing the eHealth environment](installation.md#choosing-the-ehealth-environment-acceptance--production).
