# Freehealth Connector
A streamlined mmu (Massively Multi User) version of the eHealth connector

Scalability: only vertically, NOT horizontally


## Run via gradlew
```
./gradlew bootRun

go to http://127.0.0.1:8080/api/index.html for documentation

```
You need to set the mycarenet license info in org.taktik.connector.technical.properties

(mycarenet.license.username & mycarenet.license.password). Make sure you have credentials adapted to the environment.

## Test config

File test/resources/test.properties:
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

Most of the endpoints require a valid token to be passed in the headers. You can obtain a token by calling the following endpoint:

```bash
SSIN='78010136212'
FHC_PASS_PHRASE='********'
KEYSTORE_PATH='/path/to/your/keystore.p12'
KEYSTORE_ID="$(curl -s -X POST "https://fhcprd.icure.cloud/sts/keystore" \
 -H "accept: */*" -H "content-type: multipart/form-data" \
 -F "file=@$KEYSTORE_PATH;type=application/x-pkcs12" | jq -r .uuid)"
TOKEN_ID=curl -X GET "https://fhcprd.icure.cloud/sts/token?ssin=$SSIN" \
 -H "accept: */*" -H "X-FHC-passPhrase: $FHC_PASS_PHRASE" \
 -H "X-FHC-keystoreId: $KEYSTORE_ID"
```

This call is actually made of two calls:

1. Uploading the keystore to the server, which returns a `keystoreId`.
2. Using the `keystoreId` to obtain a token for the given SSIN.

### Using the token

Once you have obtained a token, you can use it to access the endpoints by including it in the headers of your requests:

To create a prescription on recipe, you can use the following example:

```bash
curl "https://fhcprd.icure.cloud/recipe/v4?hcpQuality=persphysician&hcpNihii=$NIHII&hcpSsin=$SSIN&hcpName=$HCPNAME" \
  -H "content-type: application/json" \
  -H "x-fhc-keystoreid: $KEYSTORE_ID" \
  -H "x-fhc-passphrase: Ztf993pf" \
  -H "x-fhc-tokenid: 86a9c30c-3de5-4c39-9482-8a9c71e26165" \
  --data-raw '{ "medications": ... }'
```

### Endpoints documentation

The endpoints are documented in the OpenAPI format and can be accessed at:

```https://fhcprd.icure.cloud/api/index.html```
