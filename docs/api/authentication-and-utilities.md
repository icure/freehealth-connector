# Authentication, administration and directories

This chapter covers the parts of the Freehealth Connector (FHC) that the other services depend on. The Security Token Service controller (`/sts`) is where every client session starts: it accepts the care provider's eHealth PKCS#12 keystore and turns it into a SAML token that the other controllers use to call eHealth and MyCareNet. A STOMP websocket lets a client-side eID card act as a keystore. The chapter also covers the operational endpoints (`/admin`), the helpers for eHealth end-to-end encryption (`/crypto`) and Schematron validation (`/schematron`), and the lookup services: the eHealth Data Attribute Service (`/daas`), the APB/FTM token broker (`/apb`), the eHealth Addressbook (`/ab`) and the National Registry, reached through the v1 API (`/consultrn`) and the v2 API (`/rnconsult`).

- [Security Token Service (`/sts`)](#security-token-service-sts)
- [STS over STOMP websocket (`/ws`)](#sts-over-stomp-websocket-ws)
- [Administration (`/admin`)](#administration-admin)
- [Crypto (`/crypto`)](#crypto-crypto)
- [Schematron validation (`/schematron`)](#schematron-validation-schematron)
- [Data Attribute Service (`/daas`)](#data-attribute-service-daas)
- [APB / FTM tokens (`/apb`)](#apb--ftm-tokens-apb)
- [Addressbook (`/ab`)](#addressbook-ab)
- [National Registry v1 (`/consultrn`)](#national-registry-v1-consultrn)
- [National Registry v2 (`/rnconsult`)](#national-registry-v2-rnconsult)

### The FHC headers

Most FHC endpoints read their credentials from three HTTP headers:

| Header | Content | Obtained from |
|---|---|---|
| `X-FHC-keystoreId` | UUID of a keystore that was uploaded earlier | `POST /sts/keystore` |
| `X-FHC-passPhrase` | Password of that PKCS#12 keystore | The user. FHC never stores it. |
| `X-FHC-tokenId` | UUID of a SAML token held in the FHC token cache | `GET /sts/token/{quality}` or `POST /sts/token` |

FHC also accepts optional HTTP Basic authentication. The credentials are checked against a CouchDB user database (`CouchdbUserDetailsService`). No path requires it, because every request matcher in `SecurityConfig` is `permitAll`. Some endpoints use the authenticated principal when one is present: `/admin` needs `ROLE_ADMIN` for its write operations, `/apb` can use per-user APB/FTM credentials, and `/sts/token/oauth2` uses a per-user organisation keystore.

Exceptions are translated by `ExceptionHandlers`:

| Exception | HTTP status |
|---|---|
| `MissingKeystoreException` (unknown or expired keystore), `MissingTokenException` (unknown or expired token) | `401` |
| `IllegalArgumentException` (for example a wrong passphrase or an unsupported quality) | `400` |
| SOAP fault from eHealth | `502` |
| `TechnicalConnectorException` | Status of its category |
| Any other exception | `500` |

## Security Token Service (`/sts`)

This controller wraps the eHealth IAM SAML 1.1 token service (`endpoint.sts`, UDDI `iamsaml11tokenservice:v1`) through `STSServiceWsTrustImpl`. It requests *holder-of-key* SAML assertions, which are signed with the authentication certificate in the uploaded keystore. It also wraps the eHealth single sign-on service (`endpoint.sts.sso`), which exchanges a SAML token for a bearer token, and the eHealth M2M OAuth2 realm (`https://api[-acpt].ehealth.fgov.be/auth/realms/M2M`). FHC decides between acceptance and production by checking whether `endpoint.sts` contains `-acpt`.

**Authentication:** it depends on the endpoint. `POST /sts/keystore` needs no header. The other endpoints need the headers listed in the table.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/sts/keystore` | Upload a PKCS#12 keystore. Returns `UUIDType` (`{"uuid": ...}`), which is the keystoreId. | multipart `file`. No header. |
| GET | `/sts/keystore/{keystoreId}/info` | Decrypt the keystore and return its certificate information as `CertificateInfo` (`validity` = notAfter in epoch ms, `type`, `id`, `application`, `owner`). | path `keystoreId`. Header `X-FHC-passPhrase`. |
| GET | `/sts/keystore/check` | Return `true` if the keystore is still in the keystore map. | Header `X-FHC-keystoreId`. |
| GET | `/sts/token/{quality}` | Obtain a SAML token, or reuse the current one. Returns `SamlTokenResult` (`tokenId`, `token` as the SAML XML, `timestamp`, `validity`, `quality`). | path `quality`, query `ssin`. Headers `X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId?` (the previous token). |
| GET | `/sts/token` | Deprecated form of the previous endpoint. The quality comes from boolean flags. | query `ssin`, `isMedicalHouse?`, `isGuardPost?`, `isSortingCenter?`. Headers `X-FHC-keystoreId`, `X-FHC-passPhrase?`, `X-FHC-tokenId?`. |
| POST | `/sts/token` | Register a SAML assertion that was obtained elsewhere, under a tokenId chosen by the caller. | body: SAML assertion XML (`String`). Header `X-FHC-tokenId`. Query `quality?` (default `doctor`). |
| GET | `/sts/token/check` | Return `true` if the token is cached and its `NotOnOrAfter` is still in the future. | Header `X-FHC-tokenId`. |
| GET | `/sts/token/bearer` | Exchange the SAML token for a bearer token through eHealth SSO (SAML2 POST profile). Returns `BearerToken` (`{"token": ...}`). | query `ssin`, `destination?`. Headers `X-FHC-tokenId`, `X-FHC-keystoreId`, `X-FHC-passPhrase`. |
| GET | `/sts/token/oauth2/{cbe}/{kid}` | Obtain an OAuth2 access token from the eHealth M2M realm with a client-credentials grant and a `private_key_jwt` assertion for client `cbe-{cbe}`. Returns the Nimbus `TokenResponse`. | path `cbe`, `kid`. Headers `X-FHC-tokenId`, `X-FHC-keystoreId`, `X-FHC-passPhrase`. HTTP Basic user required. |

**The authentication flow**

1. **Upload the keystore.** Call `POST /sts/keystore` with the eHealth PKCS#12 file (`file` form field). FHC does not decrypt the file at this stage and does not write it to disk. It stores the raw bytes in the Hazelcast map `ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.KEYSTORES` and returns its keystoreId. The keystoreId is `UUID.nameUUIDFromBytes(bytes)`, so uploading the same file again returns the same UUID, and a client can upload again whenever it gets a `401`.
2. **Get a token.** Call `GET /sts/token/{quality}?ssin=<SSIN or organisation number>` with `X-FHC-keystoreId` and `X-FHC-passPhrase`. FHC decrypts the keystore with the passphrase. It then checks that the keystore's encryption key matches the care provider's ETK in the eHealth KeyDepot; if they differ, the call returns `400`. Next, it requests a SAML assertion with a 24-hour validity. The response carries a new random `tokenId`, and the token is stored in `ORG.TAKTIK.FREEHEALTH.MIDDLEWARE.TOKENS`.
3. **Call the other controllers.** Send `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` on every call. The services rebuild the SAML token from the cache and sign with the keystore.
4. **Refresh the token.** Call `GET /sts/token/{quality}` again and put the current token in `X-FHC-tokenId`. FHC returns the cached token unchanged when all of the following are true: the quality is the same, the token is not expired, and more than 50% of its lifetime remains. Otherwise it requests a new token, which gets a **new** tokenId. If eHealth STS returns a technical error, FHC returns the previous token (it can be `null`) and raises no error.

**Supported `quality` values:** `doctor`, `nurse`, `dentist`, `physiotherapist`, `logopedist`, `dietician`, `clinicalpsychologist`, `optician`, `podologist`, `midwife` take the care provider's SSIN in `ssin`. `medicalhouse`, `guardpost`, `sortingcenter`, `officedoctors`, `groupofnurses`, `reeducation` take the organisation's NIHII. `enterprise` takes a CBE number and `ehp` takes an EHP number. Any other value returns `400` ("unsupported quality"). The quality is stored with the token, and other services read it back from the token, for example to set the requesting care provider type.

**Lifetimes and storage**

| Item | Where it is held | Lifetime |
|---|---|---|
| Uploaded keystore (still encrypted with its passphrase) | Hazelcast map `...KEYSTORES` | TTL of **18 h** from upload. LRU eviction under heap pressure. |
| SAML token | Hazelcast map `...TOKENS` | TTL of **12 h** in the map, even though the assertion is requested for 24 h |
| Decrypted `KeyStore` object | Local Guava cache, keyed by (keystoreId, passphrase) | Expires 1 h after last access, at most 2000 entries |
| ETKs from the KeyDepot | Hazelcast maps `...ETK` / `...LONGLIVEDETK` | 8 h / 3 years |

The Hazelcast maps are replicated across the FHC cluster nodes (one asynchronous backup, reads from backups allowed) and live only in memory. When a node restarts, its keystores and tokens are gone, and clients must upload the keystore again. Because the token TTL is shorter than the keystore TTL, a session that runs longer than 12 h has to request a new token, and one that runs longer than 18 h has to upload the keystore again.

**Notes**

- `/sts/keystore/check` looks only at the uploaded keystore map. It returns `false` for a keystoreId backed by a remote eID card (see the next section).
- `/sts/token/check` does not contact eHealth. It only compares the cached `validity` with the current time.
- `POST /sts/token` reads the validity from the assertion's `NotOnOrAfter`. Operations that decrypt the keystore, such as signing requests, still need a keystoreId whose certificate matches the imported token.
- The OAuth2 endpoint needs a principal authenticated with HTTP Basic whose user record defines the organisation keystore (`orgKeystoreAccUuid`/`orgKeystoreAccPassword` or `orgKeystoreProdUuid`/`orgKeystoreProdPassword`). If that keystore is not in memory, FHC loads it from the user database (`org-keystore-acc` / `org-keystore-prod`) and uploads it. The SAML token must also be valid; otherwise the call returns `400`.
- The `destination` of `/sts/token/bearer` is XML-escaped before it is sent to the SSO service. The `ssin` query parameter is required, but the service does not use it.

## STS over STOMP websocket (`/ws`)

`STSStompController` has no REST routes. It connects a client-side eID (BeID) card to FHC, so that the card's authentication key can sign requests in place of an uploaded PKCS#12 keystore. `WebSocketConfiguration` registers a STOMP endpoint at `/ws`. The endpoint gives every websocket session a random UUID as its principal, uses the application destination prefix `/app` and uses a simple broker on `/topic`.

**Authentication:** none. `/ws/**` is `permitAll`, and the session UUID serves as the credential.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| STOMP SEND | `/app/sts` | Register the session. The session UUID is added to the Hazelcast set `...KEYSTORECONNECTIONS`, and `{"action":"connected","keystoreId":"<uuid>"}` is published on `/topic/sts/<uuid>`. | none |
| STOMP SEND | `/app/msg` | Return the card's answer to a request that FHC published on `/topic/sts/<uuid>`. | body `StompMessage` (`{"content": "<base64>"}`) |

**Notes**

- The flow runs as follows. The client connects to `/ws`, subscribes to `/topic/sts/<uuid>` and sends to `/app/sts`. It then uses the returned UUID as `X-FHC-keystoreId`. When `STSServiceImpl.getKeyStore` finds a registered connection for that id, it builds a `RemoteKeystore` backed by a `RemoteBeIDProvider`. Card operations (APDUs) go to the client over `/topic/sts/<uuid>`, and the client returns the answers through `/app/msg`.
- If the connection is held by another cluster node, FHC relays through Hazelcast (`RelayCardConnection`).
- When the websocket disconnects, FHC removes the registration, and the keystoreId stops working.

## Administration (`/admin`)

These are operational endpoints of the FHC instance itself. They do not call eHealth. They change logback levels at runtime, and they show or refresh the Business Continuity Plan (BCP) endpoint distributor of the connector, which switches eHealth services to fallback endpoints during outages.

**Authentication:** the FHC headers are not used. `POST /admin/loglevel/{loglevel}` and `POST /admin/bcp` require an HTTP Basic principal with `ROLE_ADMIN`. Without one they throw `IllegalAccessException`, which is returned as `500`. `GET /admin/bcp` performs no check and needs no authentication.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/admin/loglevel/{loglevel}` | Set the log level of a logger or package. Returns `ok`, or an error text for an unknown level (text/plain). | path `loglevel` (`TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, case-insensitive), query `package` |
| GET | `/admin/bcp` | Show the BCP status as `EndpointDistributorStatusDto` (`mustPoll`, `isBcpMode`, `default` and `active` service-to-endpoint maps). | none |
| POST | `/admin/bcp` | Force an immediate BCP endpoint update (`EndpointUpdater.forceUpdate()`). | none |

**Notes**

- `GET /admin/bcp` recently stopped requiring `ROLE_ADMIN`, so monitoring can poll it. This came from PR #101.
- Log level changes affect only the node that receives the request and do not persist across a restart.

## Crypto (`/crypto`)

This controller exposes eHealth end-to-end encryption (the connector's `Crypto` seal/unseal with the `WITH_NON_REPUDIATION` policy). To encrypt, FHC fetches the addressee's ETKs from the eHealth KeyDepot, adds the sender's own ETK so that the sender can decrypt the message too, and signs with the uploaded keystore. To decrypt, FHC unseals the message with the keystore's decryption keys.

**Authentication:** `X-FHC-keystoreId` and `X-FHC-passPhrase`. No SAML token is needed.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/crypto/encrypt/{identifier}/{id}` | Seal raw bytes for an addressee. | path `identifier` (an `IdentifierType` name such as `NIHII`, `SSIN`, `CBE`, `EHP`), path `id`, query `applicationId?`. Body `application/octet-stream`. |
| POST | `/crypto/encryptFile/{identifier}/{id}` | Same as the previous endpoint, for a multipart upload. | same path and query parameters. Multipart `plainData`. |
| POST | `/crypto/decrypt` | Unseal raw bytes addressed to the keystore owner. | body `application/octet-stream` |
| POST | `/crypto/decryptFile` | Same as the previous endpoint, for a multipart upload. | multipart file (`encryptedData`) |

**Notes**

- Every endpoint returns `application/octet-stream`.
- `identifier` must match an `IdentifierType` enum constant exactly. An unknown value causes `IllegalArgumentException`, which is returned as `400`.
- ETKs are cached in Hazelcast for 8 h (`...ETK`).

## Schematron validation (`/schematron`)

This is a local validation helper. FHC does not call eHealth for it. It validates an XML document against a Schematron rule set bundled in `src/main/resources/org/taktik/freehealth/middleware/schematron/`. The only rule set currently bundled is `multimediatt` (Multi-eMediAtt incapacity certificates).

**Authentication:** `X-FHC-tokenId` only. The token must be valid (`checkTokenValid`); otherwise the call returns `401`.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| POST | `/schematron/validate/{schema}` | Validate the document. Returns `SchematronValidationResult`. | path `schema` (for example `multimediatt`). Body XML (`application/xml`). |

**Notes**

- FHC removes every character other than `A-Z` and `a-z` from `schema` before it resolves `<schema>.schematron.xml`.

## Data Attribute Service (`/daas`)

This controller wraps the eHealth Data Attribute Service (`endpoint.daas.attribute.v1`, `https://services.ehealth.fgov.be/DAtaAttributeService/v1`). FHC sends a SAML 2.0 `AttributeQuery` for the attribute `urn:be:fgov:person:ssin:multemediatt:routing`. The attribute gives the routing of a patient's incapacity-for-work certificate (Multi-eMediAtt): the destinations, with their channel and dataset.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase`.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/daas/din/{nihii}/{ssin}` | Get the certificate routing for a patient and an incapacity period. Returns `DaasResponse` (`destinations` as a list of maps, `context` as a map, `status`). | path `nihii` (doctor), path `ssin` (patient). Query `dateOfBirth` (`yyyyMMdd` as an integer), `from` and `to` (epoch ms or FuzzyDate), `cause`, `prolongation`, `total`. All are required. |

**Notes**

- The issuer is set to `urn:be:fgov:person:ssin:ehealth:1.0:doctor:nihii11:<nihii>`, so the call assumes a doctor token.
- The incapacity period (`from`, `to`) is sent as the subject confirmation's `NotBefore` and `NotOnOrAfter`.
- A `TechnicalConnectorException` from the service is returned as `400`.

## APB / FTM tokens (`/apb`)

This controller brokers OAuth2 client-credentials tokens from the Association Pharmaceutique Belge identity server (`https://auth.apb.be/connect/token`). It is not an eHealth service. With scope `production`, the token gives access to the APB product information APIs. With scope `tools`, it gives access to the FTM (Formulaire Thérapeutique Magistral) APIs. The client then calls the APB APIs directly with the returned access token.

**Authentication:** none of the FHC headers. If the request is authenticated with HTTP Basic and the user has `apbCustomerId`/`apbPassword` (or `ftmCustomerId`/`ftmPassword`), FHC uses those credentials. Otherwise it uses the instance-wide properties `apb.username`/`apb.password` (or `ftm.username`/`ftm.password`).

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/apb/token/bearer` | Get an APB access token (scope `production`). Returns the Nimbus `TokenResponse`. | none |
| GET | `/apb/token/bearer/ftm` | Get an FTM access token (scope `tools`). Returns the Nimbus `TokenResponse`. | none |

## Addressbook (`/ab`)

This controller wraps the eHealth Addressbook v1 (`endpoint.addressbook`, `https://services.ehealth.fgov.be/AddressBook/v1`), the directory of healthcare professionals and organisations. The results are mapped to FHC's `HealthcareParty` DTO, with names, NIHII, SSIN, CBE or EHP, profession codes, work addresses (with e-mail telecoms) and eHealthBox identifiers. Clients use it to find recipients, for example for eHealthBox messages or referral letters.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/ab/search/hcp/{lastName}` | Search professionals by last name. Returns `List<HealthcareParty>`. | path `lastName`, query `firstName?`, `type?` (profession, default `PHYSICIAN`) |
| GET | `/ab/search/org/{name}` | Search organisations by name. Returns `List<HealthcareParty>`. | path `name`, query `type?` (institution type, default `HOSPITAL`) |
| GET | `/ab/hcp/nihii/{nihii}` | Get the contact information of a professional by NIHII. Returns `HealthcareParty`. | path `nihii`, query `language?` (default `fr`) |
| GET | `/ab/hcp/ssin/{ssin}` | Get the contact information of a professional by SSIN. Returns `HealthcareParty`. | path `ssin`, query `quality?`, `language?` (default `fr`) |
| GET | `/ab/org/nihii/{nihii}` | Get the contact information of an organisation by NIHII. Returns `HealthcareParty`. | path `nihii`, query `language?` |
| GET | `/ab/org/cbe/{cbe}` | Get the contact information of an organisation by CBE number. Returns `HealthcareParty`. | path `cbe`, query `language?` |
| GET | `/ab/org/ehp/{ehp}` | Get the contact information of an organisation by EHP number. Returns `HealthcareParty`. | path `ehp`, query `language?` |

**Notes**

- Search results are capped at 100 entries (`maxElements = 100`). FHC applies `firstName` as a case-insensitive prefix filter on its side, after it receives the results.
- The NIHII in a search result is the NIHII of the `PHYSICIAN` profession when the person has one, and otherwise the first profession's NIHII.
- For `/ab/hcp/ssin/{ssin}`, `quality` (a profession code, default `PHYSICIAN`) selects which of the person's professional records provides the NIHII, addresses and profession codes. `language` selects the language of street, municipality and country names, and of organisation names.

## National Registry v1 (`/consultrn`)

This is the first-generation API to the National Registry (Rijksregister / Registre National) through eHealth's ConsultRN services: `endpoint.consultrn.identifyperson`, `endpoint.consultrn.phoneticsearch` and `endpoint.consultrn.manageperson` (registration of persons in the BIS registry). The SSIN history comes from the eHealth SSIN history service (`endpoint.ssinhistory`). Clients use it to identify a patient from an SSIN, find an SSIN from identity data, or create a BIS number for a person who is not in the registry.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/consultrn/{ssin}` | Identify a person by SSIN. Returns `SearchBySSINReplyDto`. | path `ssin` |
| GET | `/consultrn/history/{ssin}` | Get the current SSIN and its replacement history. Returns `ConsultCurrentSsinResponse`. | path `ssin` |
| GET | `/consultrn/{dateOfBirth}/{lastName}` | Phonetic search. Returns `SearchPhoneticReplyDto`. | path `dateOfBirth` (`yyyyMMdd`), `lastName`. Query `firstName?`, `middleName?`, `gender?` (default `UNKNOWN`), `tolerance?` (default 0), `limit?` (default 20). |
| POST | `/consultrn` | Register a person, which creates a BIS number. Returns `RegisterPersonResponseDto`. | body `PersonMid` |

**Notes**

- Business errors from eHealth are not raised as HTTP errors. The eHealth reply is returned with its status, and for `POST /consultrn` it carries `businessAnomalies`. If the person already exists, the response contains one `FATAL` anomaly with the eHealth status code (or `999999`).
- `identify` queries with quality code `6` over a period that starts today and lasts 2 years.
- New integrations should use `/rnconsult`.

## National Registry v2 (`/rnconsult`)

This is the second-generation API to the National Registry through the eHealth ConsultRN v2 services. `endpoint.consultrnv2.personservice` handles the SSIN and phonetic searches. `endpoint.consultrnv2.cbsspersonservice` handles person registration (BIS). The SSIN history service is `endpoint.ssinhistory`, and the eHealth IdSupport v2 service (`endpoint.idsupport.v2`) checks an identity document. The results are mapped to `RnConsult*` DTOs.

**Authentication:** `X-FHC-keystoreId`, `X-FHC-tokenId` and `X-FHC-passPhrase` on every endpoint.

**Endpoints**

| Method | Path | Purpose | Key parameters |
|---|---|---|---|
| GET | `/rnconsult/bySsin/{ssin}` | Search a person by SSIN. Returns `RnConsultSearchPersonBySsinResponseDto`. | path `ssin` |
| GET | `/rnconsult/phonetically/{dateOfBirth}/{lastName}` | Phonetic search. Returns `RnConsultSearchPersonPhoneticallyResponseDto`. | path `dateOfBirth` (`yyyyMMdd`), `lastName`. Query `firstName?`, `middleName?`, `matchingType?`, `gender?`, `countryCode?` (default 0), `cityCode?`, `tolerance?`, `limit?`. |
| POST | `/rnconsult` | Register a person, which creates a BIS number. Returns `RnConsultRegisterPersonResponseDto`. | body `RnConsultPersonMid` |
| GET | `/rnconsult/history/{ssin}` | Get the current SSIN and its replacement history. Returns `ConsultCurrentSsinResponse`. | path `ssin` |
| GET | `/rnconsult/verifyId` | Check whether an identity document is valid for the person. Returns `VerifyIdResponse`. | query `ssin?`, `cardNumber?`, `barCoded?` |

**Notes**

- The controller has its own exception handlers. `MissingTokenException` returns `401`, `IllegalArgumentException` returns `400`, and a SOAP fault returns `502`. Each returns the message as plain text rather than an `ExceptionDto`.
- With `matchingType=IGNORE_GIVENNAME` and no `firstName`, FHC sends a blank given name, because the XSD requires one.
- `verifyId` sends the legal context `patient insurance validation`. It always includes the `ssin` and `cardNumber` identifiers, and includes `barCoded` only when it is not empty.
