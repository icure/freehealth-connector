# Tokens derived from a SAML token

A FHC session starts with a SAML holder-of-key (HOK) token from the eHealth I.AM STS (see [Security Token Service](authentication-and-utilities.md#security-token-service-sts)). That token authenticates the SOAP calls FHC makes to eHealth and MyCareNet. Some eHealth services do not accept it: web applications expect a browser session, and REST APIs expect an OAuth2/OpenID Connect access token. `SSOService` provides three ways to get one of those tokens from a session that already has a SAML token:

| Token                                  | FHC endpoint                        | `SSOService` method            | eHealth endpoint                                                                                          | Format                                             | Represents                                                                 |
|----------------------------------------|-------------------------------------|--------------------------------|-----------------------------------------------------------------------------------------------------------|----------------------------------------------------|----------------------------------------------------------------------------|
| SAML bearer assertion for the I.AM IDP | `GET /sts/token/bearer`             | `getLegacySSOBearerToken`      | I.AM STS SSO service `IAM/SingleSignOnService/v1`, assertion consumed by `/idp/profile/SAML2/Bearer/POST` | Base64 SAML 2.0 `Response` with a bearer assertion | The care provider of the SAML token                                        |
| I.AM Connect M2M access token          | `GET /sts/token/oauth2/{cbe}/{kid}` | `getM2MOauth2Token`            | `/auth/realms/M2M/protocol/openid-connect/token`                                                          | JWT (OAuth2 access token)                          | An organisation (client `cbe-{cbe}`), no end user                          |
| I.AM Connect healthcare access token   | `POST /sts/token/exchange/iam`      | `exchangeSamlTokenForIamToken` | `/auth/realms/healthcare/protocol/openid-connect/token`                                                   | JWT (OAuth2 access token, optional refresh token)  | The care provider of the SAML token, acting through an I.AM Connect client |

The three tokens are not interchangeable. Each one is accepted only by the services built on the matching I.AM component: the IDP for classic web applications, and I.AM Connect for REST APIs, in either the M2M realm or the healthcare realm.

- [Starting point: the SAML HOK token](#starting-point-the-saml-hok-token)
- [SAML bearer assertion (legacy SSO to the I.AM IDP)](#saml-bearer-assertion-legacy-sso-to-the-iam-idp)
- [I.AM Connect M2M access token](#iam-connect-m2m-access-token)
- [I.AM Connect healthcare access token (token exchange)](#iam-connect-healthcare-access-token-token-exchange)
- [Endpoint validation](#endpoint-validation)
- [Sources](#sources)

## Starting point: the SAML HOK token

`GET /sts/token/{quality}` requests a SAML 1.1 holder-of-key assertion from the I.AM STS (`IAM/SecurityTokenService/v1`, UDDI key `iamsaml11tokenservice:v1`). The assertion carries the care provider's identity and certified attributes (quality, NIHII, ...). It is bound to the `authentication` key of the keystore: each SOAP request must be signed with that key.

**Unlocks:** the SOAP web services protected by the I.AM STS. This covers every eHealth and MyCareNet service that FHC wraps, such as GenIns, Member Data, eFact, eAttest, Chapter IV, GMD, Recip-e, eHealthBox, hubs, consent and therapeutic links (see the other API reference chapters). It does not unlock web applications or REST APIs, which is why the three tokens below exist.

All three tokens below need a valid SAML token in the FHC cache (`X-FHC-tokenId`).

## SAML bearer assertion (legacy SSO to the I.AM IDP)

**FHC endpoint:** `GET /sts/token/bearer`, method `getLegacySSOBearerToken`.

**How it is obtained:** FHC sends a WS-Trust `RequestSecurityToken` to the I.AM STS single sign-on service, authenticated with the SAML HOK token (`SingleSignOnServiceImpl`). The request asks for a `Bearer` key type, with `AppliesTo` set to the IDP assertion consumer. The STS returns a SAML 2.0 bearer assertion signed by eHealth. FHC wraps it in a SAML 2.0 `Response`, with a `Destination` attribute when the `destination` parameter is set, and returns it base64-encoded in `BearerToken.token`.

|                                                                        | Acceptance                                                         | Production                                                    |
|------------------------------------------------------------------------|--------------------------------------------------------------------|---------------------------------------------------------------|
| STS SSO service (`endpoint.sts.sso`, UDDI `iamsinglesignonservice:v1`) | `https://services-acpt.ehealth.fgov.be/IAM/SingleSignOnService/v1` | `https://services.ehealth.fgov.be/IAM/SingleSignOnService/v1` |
| IDP assertion consumer (`AppliesTo`)                                   | `https://wwwacc.ehealth.fgov.be/idp/profile/SAML2/Bearer/POST`     | `https://www.ehealth.fgov.be/idp/profile/SAML2/Bearer/POST`   |

**How it is used:** the client's browser POSTs the returned value as the `SAMLResponse` form field to the IDP assertion consumer URL, with an optional `RelayState` pointing to the target application. The IDP then opens a browser session for the user, as if they had logged in.

**Unlocks:** the web applications of the eHealth I.AM federation, meaning server-side web applications protected by the I.AM IDP (eHealth platform online services and partner web applications registered as service providers). Once the IDP session exists, the user moves between these applications without logging in again. eHealth does not publish a consolidated list of these applications: an application belongs to the federation when it is registered with the IDP ("joined IDP protected service" form). The assertion is not an API credential: no REST or SOAP service accepts it.

**Lifetime:** short (at most 10 minutes, per the cookbook). The assertion must be used right away.

The cookbook also describes an *artifact* variant, which returns a single-use URL instead of an assertion. `SingleSignOnServiceImpl` supports it (`SsoProfile.SAML2_ARTIFACT`), but the REST endpoint always uses the POST profile.

## I.AM Connect M2M access token

**FHC endpoint:** `GET /sts/token/oauth2/{cbe}/{kid}`, method `getM2MOauth2Token`.

**How it is obtained:** an OAuth2 `client_credentials` grant on the M2M realm, with `private_key_jwt` client authentication. FHC signs the client assertion (RS256, header `kid` = `{kid}`) with the `authentication` key of the **organisation keystore** configured on the HTTP Basic user (`orgKeystoreAccUuid`/`orgKeystoreProdUuid`). The client id is `cbe-{cbe}`, and the audience is the realm URL.

|                                    | Acceptance                                                                       | Production                                                                  |
|------------------------------------|----------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| Token endpoint                     | `https://api-acpt.ehealth.fgov.be/auth/realms/M2M/protocol/openid-connect/token` | `https://api.ehealth.fgov.be/auth/realms/M2M/protocol/openid-connect/token` |
| Issuer (`iss` of the access token) | `https://api-acpt.ehealth.fgov.be/auth/realms/M2M`                               | `https://api.ehealth.fgov.be/auth/realms/M2M`                               |

**Represents:** the organisation, not a person. Per the Mobile integration specifications (section 4.2), the client-credentials flow is only available to organisation and institution clients and does not authenticate an end user. The SAML token of the session is checked to exist but is not sent to eHealth. The care provider's identity is therefore **not** in this token.

**Unlocks:** the REST APIs on the M2M realm that the organisation's client is registered for. The scopes requested in the M2M client registration form limit those APIs, as defined by each API's onboarding documentation. Unlike the healthcare form, the M2M form does not list applications, and eHealth publishes no consolidated list of M2M APIs: each API's onboarding documentation states which realm it uses. One example: according to eHealth Atlas, hospital pharmacies use their own M2M client on the Recip-e FHIR API.

**Limitations:**
- FHC always builds the client id as `cbe-{cbe}`. Clients registered under another identifier type in the M2M form (NIHII-based institutions, EHP, ...) can't be used through this endpoint.
- The client assertion is always signed with RS256 and requires an RSA key. An organisation keystore with an EC `authentication` key fails with "Cannot find private key for signature".
- The response is the raw nimbus `TokenResponse`.

## I.AM Connect healthcare access token (token exchange)

**FHC endpoint:** `POST /sts/token/exchange/iam`, method `exchangeSamlTokenForIamToken`.

**How it is obtained:** an OAuth2 token exchange (RFC 8693) on the healthcare realm, as described in the "STS to IAM Connect" cookbook (sections 6.3 and 8):

| Form field             | Value                                                                                                                                                                                                        |
|------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `grant_type`           | `urn:ietf:params:oauth:grant-type:token-exchange`                                                                                                                                                            |
| `client_id`            | The I.AM Connect healthcare client (`clientId` parameter)                                                                                                                                                    |
| `subject_token_type`   | `urn:ietf:params:oauth:token-type:saml1` (or `saml2`, chosen from the assertion namespace)                                                                                                                   |
| `subject_token`        | The cached SAML HOK assertion, base64url-encoded                                                                                                                                                             |
| `actor_token_type`     | `urn:ietf:params:oauth:token-type:jwt`                                                                                                                                                                       |
| `actor_token`          | A JWT signed with the holder-of-key (`authentication`) key: RS256 for RSA keys, ES256/ES384/ES512 for EC keys. Claims: `iss` = client id, `sub` = SSIN, `aud` = `urn:be:fgov:ehealth:sts:1_0`, `iat`, `jti`. |
| `requested_token_type` | `urn:ietf:params:oauth:token-type:access_token`, or `refresh_token` when `refreshable=true`                                                                                                                  |

|                                    | Acceptance                                                                              | Production                                                                         |
|------------------------------------|-----------------------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| Token endpoint                     | `https://api-acpt.ehealth.fgov.be/auth/realms/healthcare/protocol/openid-connect/token` | `https://api.ehealth.fgov.be/auth/realms/healthcare/protocol/openid-connect/token` |
| Issuer (`iss` of the access token) | `https://api-acpt.ehealth.fgov.be/auth/realms/healthcare`                               | `https://api.ehealth.fgov.be/auth/realms/healthcare`                               |

**Represents:** the care provider identified in the SAML token (`ssin` claim, with their active profile in `userProfile`), acting through the calling I.AM Connect client (`azp`).

**Unlocks:** the REST APIs of the healthcare realm that are listed in the token's `aud` claim and authorised by its `scope` claim. These depend on what the client is registered for. The healthcare client registration form lists the applications for which a client can be onboarded:
- eHealth Box (REST)
- RCT
- Vitalink
- Vaccinnet
- Vaccicard
- RSW
- RSB
- UHMEP
- PSS

According to eHealth Atlas, which cites the Recip-e FHIR cookbook, healthcare-realm clients of the UHMEP FHIR API must use the token exchange.

For example, a token obtained on acceptance for the `medispring-caregiver` client had:
- `aud`: `medispring-caregiver`, `nihdi-uhmep-api`, `ehealth-pseudo-api`;
- `scope`: `iam:profile:professional profile ssin pseudo:api:identify iam:connect:exchange pseudo:api:pseudonymize nihdi:uhmep:hcp nihdi:uhmep:pseudo iam:authz`.

That token unlocks the UHMEP API (`nihdi-uhmep-api`) and the eHealth pseudonymisation API (`ehealth-pseudo-api`).

**Lifetime:** 300 seconds for the access token on acceptance. With `refreshable=true`, a refresh token is returned as well, and the client renews the access token with a regular OAuth2 `refresh_token` grant on the same token endpoint.

**Preconditions (set up on the eHealth side, not in FHC):**
- The client must be known and active in the healthcare realm, and be granted permission to exchange tokens from the eHealth STS.
- The user must already be known in the realm, meaning they logged in once with any client. Otherwise eHealth returns `invalid_token - user not found`.
- The user must have consented to the client. Otherwise eHealth returns `invalid_client - Client requires user consent` or `invalid_scope`. In both cases the user has to go through the normal I.AM Connect browser login once.

The opposite conversion, an I.AM Connect access token exchanged for a SAML HOK token (I.AM eXchange), is a separate eHealth service. FHC does not implement it.

## Endpoint validation

The URLs used by `SSOServiceImpl` and the connector configuration were checked against the eHealth documentation and the live services on 22/10/2026:

| Token                   | Check                                                                                                                                                  | Result                                                                                                                                               |
|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| SAML bearer assertion   | `endpoint.sts.sso` (`uddi/uddi-default.properties`) and the IDP consumer URLs in `SingleSignOnServiceImpl`, compared with the cookbook (section 5.4.2) | Same URLs for acceptance and production                                                                                                              |
| M2M access token        | OpenID discovery (`/auth/realms/M2M/.well-known/openid-configuration`) on acceptance and production                                                    | `issuer` and `token_endpoint` match the URLs used by `getM2MOauth2Token`. `client_credentials` and `private_key_jwt` are supported.                  |
| Healthcare access token | OpenID discovery (`/auth/realms/healthcare/.well-known/openid-configuration`) on acceptance and production                                             | `issuer` and `token_endpoint` match the URLs used by `exchangeSamlTokenForIamToken`. `urn:ietf:params:oauth:grant-type:token-exchange` is supported. |
| Healthcare access token | End-to-end exchange on acceptance (`STSControllerTest`), with the access token's signature verified against the realm's `jwks_uri`                     | RS256 signature valid, `iss` equal to the discovery `issuer`, token not expired                                                                      |

The bearer assertion and the M2M access token were not obtained live: only their configuration and the realm's discovery document were checked. Obtaining an M2M token needs a registered M2M client and its organisation keystore, and the test configuration has neither.

## Sources

- eHealth platform, [I.AM (Identity & Access Management)](https://www.ehealth.fgov.be/ehealthplatform/fr/service-i.am-identity-access-management): the I.AM components (IDP, STS, Connect, AA) and when each one applies.
- eHealth platform, *IAM STS to IDP/IAM Connect – Cookbook (SSO from fat to thin client)*, v1.5, 11/09/2026: the bearer assertion flow (section 5), the token exchange (sections 6 and 7), the actor token (section 8) and the error codes (section 10.2).
- eHealth platform, *I.AM Connect – Mobile integration – Technical specifications*, v1.13: realms, environments, client types and client authentication.
- eHealth platform, *IAM Connect – Healthcare realm client registration request form*, v2.2, and *M2M client registration form*, v2.1: onboarding applications, scopes, client identifiers.
- eHealth platform, *STS – WS-Trust – Cookbook*, v1.2: the SAML HOK token.
- eHealth platform, *IAM eXchange – Technical specifications*, v1.4: the OIDC → SAML HOK conversion.
- eHealth Atlas, [I.AM en I.AM Connect](https://ehealth-atlas.be/i-am-connect/): a third-party secondary source, used for the UHMEP token-exchange requirement and the M2M clients of hospital pharmacies on the Recip-e FHIR API.
