# Freehealth Connector
A streamlined mmu (Massively Multi User) version of the eHealth connector

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

### Howto use recip-e

#### Create a new prescription

In order to create a new prescription, you need to call the following endpoint:

```
POST /api/prescriptions
```
