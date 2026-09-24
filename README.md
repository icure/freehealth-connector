# Freehealth Connector
A streamlined mmu (Massively Multi User) version of the eHealth connector: a REST/JSON middleware in front of the Belgian eHealth platform and MyCareNet.

## Documentation

- [Documentation index and feature overview](docs/README.md)
- [Installation guide](docs/installation.md)
- API reference:
  [authentication & utilities](docs/api/authentication-and-utilities.md) ·
  [MyCareNet insurability & billing](docs/api/mycarenet-insurance-and-billing.md) ·
  [eAttest, Chapter IV & eAgreement](docs/api/attestations-and-agreements.md) ·
  [Recip-e, eHealthBox, Vaccinnet & RSW](docs/api/prescriptions-and-messaging.md) ·
  [hubs, consent & therapeutic links](docs/api/hubs-consent-and-therapeutic-links.md)

## Run via gradlew
```
./gradlew bootRun
```

The server listens on port 8090. The OpenAPI UI is at http://127.0.0.1:8090/swagger-ui.html (JSON at `/v3/api-docs`).

You need to set the MyCareNet licence (`mycarenet.license.username` & `mycarenet.license.password`) in `org.taktik.connector.technical.properties`, with credentials suited to the target environment. See the [installation guide](docs/installation.md#mycarenet-licence).

## Test config

File `src/test/resources/test.properties` (copy `test.template.properties`):
```
org.taktik.icure.keystore1.ssin=...
org.taktik.icure.keystore1.nihii=...
org.taktik.icure.keystore1.password=...
org.taktik.icure.keystore1.name=...
org.taktik.icure.keystore2.ssin=...
org.taktik.icure.keystore2.nihii=...
org.taktik.icure.keystore2.password=...
org.taktik.icure.keystore2.name=...
```
## How to use the Freehealth Connector

### Obtaining a token

Most of the endpoints require a valid token to be passed in the headers. You can obtain a token by calling the following endpoints:

```bash
FHC='https://fhcprd.icure.cloud'
SSIN='<your SSIN>'
FHC_PASS_PHRASE='<keystore passphrase>'
KEYSTORE_PATH='/path/to/your/keystore.p12'
KEYSTORE_ID="$(curl -s -X POST "$FHC/sts/keystore" \
 -H "accept: */*" -H "content-type: multipart/form-data" \
 -F "file=@$KEYSTORE_PATH;type=application/x-pkcs12" | jq -r .uuid)"
TOKEN_ID="$(curl -s -X GET "$FHC/sts/token?ssin=$SSIN" \
 -H "accept: */*" -H "X-FHC-passPhrase: $FHC_PASS_PHRASE" \
 -H "X-FHC-keystoreId: $KEYSTORE_ID" | jq -r .tokenId)"
```

This is actually made of two calls:

1. Uploading the keystore to the server, which returns a `keystoreId`.
2. Using the `keystoreId` to obtain a token for the given SSIN.

### Using the token

Once you have obtained a token, you can use it to access the endpoints by including it in the headers of your requests.

To create a prescription on Recip-e, you can use the following example:

```bash
curl "$FHC/recipe/v4?hcpQuality=persphysician&hcpNihii=$NIHII&hcpSsin=$SSIN&hcpName=$HCPNAME" \
  -H "content-type: application/json" \
  -H "x-fhc-keystoreid: $KEYSTORE_ID" \
  -H "x-fhc-passphrase: $FHC_PASS_PHRASE" \
  -H "x-fhc-tokenid: $TOKEN_ID" \
  --data-raw '{ "medications": ... }'
```

### Endpoints documentation

The endpoints are documented in the OpenAPI format and can be accessed at:

```https://fhcprd.icure.cloud/swagger-ui.html```
