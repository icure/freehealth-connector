# Installation guide

This guide covers building, configuring and running the Freehealth Connector (FHC) locally, as a Docker container and on Kubernetes with the bundled Helm chart.

- [Prerequisites](#prerequisites)
- [Build](#build)
- [Run locally](#run-locally)
- [Choosing the eHealth environment (acceptance / production)](#choosing-the-ehealth-environment-acceptance--production)
- [Configuration reference](#configuration-reference)
- [Docker](#docker)
- [Kubernetes (Helm)](#kubernetes-helm)
- [Clustering and scaling](#clustering-and-scaling)
- [Verifying the installation](#verifying-the-installation)
- [Running the tests](#running-the-tests)
- [Deployment notes](#deployment-notes)

## Prerequisites

| Requirement | Version / notes |
|---|---|
| JDK | 21 (the build uses a Kotlin JVM toolchain 21 and `sourceCompatibility = 21`) |
| Gradle | Provided by the wrapper (`./gradlew`, Gradle 8.13) — no local install needed |
| Network access to Maven repositories | Maven Central, `https://maven.taktik.be/content/groups/public` and `https://repo.ehealth.fgov.be/artifactory/maven2` (see `settings.gradle.kts`). Both custom repositories are read anonymously. |
| eHealth credentials | A PKCS#12 eHealth certificate (keystore) for each healthcare party or organisation that will call eHealth, plus its passphrase. These are uploaded at runtime through `/sts/keystore`; nothing is baked into the server. |
| MyCareNet package licence | Required for every MyCareNet service (GenIns, MDA, eFact, eAttest, Chapter IV, eAgreement, GMD, MHM, Tarification, Mediprima). See [MyCareNet licence](#mycarenet-licence). |
| CouchDB (optional) | Only needed if you want HTTP Basic authentication backed by per-user records. See [Users and Basic authentication](#users-and-basic-authentication). |

Main stack: Spring Boot 3.5.13, Kotlin 2.2, Jetty 12 (Tomcat is excluded) with HTTP/2, virtual threads, Hazelcast 5 for in-memory state, SpringDoc OpenAPI. Versions are managed in `libs.versions.toml`.

## Build

```bash
./gradlew bootJar -x test      # executable jar in build/libs/
./gradlew build                # compile + tests (tests need real eHealth credentials, see below)
```

The first build downloads the eHealth connector artifacts and the pre-compiled JAXB bindings in `compiled/`; it needs a lot of memory (`gradle.properties` sets `-Xmx10240m` for the Gradle daemon).

## Run locally

```bash
./gradlew bootRun
# or
java -jar build/libs/freehealth-connector-*.jar
```

The server listens on **port 8090** by default (`server.port` in `src/main/resources/application.properties`). Override it with `--server.port=8080`, `-Dserver.port=8080` or the `SERVER_PORT` environment variable.

On startup, `MiddlewareApplication` copies the trust material shipped in the jar (`caCertificateKeystore.jks`, `truststore.jks`, `tsacertificate.jks`, `tslostore.jks` for both `acpt` and `prod`) into **`/opt/ehealth/{acpt,prod}/`**. The process therefore needs write access to `/opt/ehealth`. The connector configuration refers to these files through `KEYSTORE_DIR=/opt/ehealth/`.

Once started:

| URL | What |
|---|---|
| `http://localhost:8090/swagger-ui.html` | Interactive OpenAPI UI (SpringDoc) |
| `http://localhost:8090/v3/api-docs` | OpenAPI 3 JSON |
| `http://localhost:8090/actuator/health` | Health endpoint (`UP`/`DOWN`, no details) |

## Choosing the eHealth environment (acceptance / production)

The target eHealth environment is selected **per JVM**, not per request. The eHealth connector layer reads its endpoints, trust stores and licence settings from a single properties file:

| Environment | Properties file (classpath) |
|---|---|
| Acceptance (default) | `/acpt/org.taktik.connector.technical.properties` |
| Production | `/prod/org.taktik.connector.technical.properties` |

Select it with the system property `org.taktik.connector.technical.config.location` (read by `ConfigFactory`):

```bash
java -Dorg.taktik.connector.technical.config.location=/prod/org.taktik.connector.technical.properties \
     -jar build/libs/freehealth-connector-*.jar
```

If the property is absent, the server talks to **acceptance**. Run one FHC deployment per environment.

Several services derive their `isTest` flag from the endpoint URL (an endpoint containing `-acpt` is considered a test environment), so do not mix acceptance endpoints into the production file.

## Configuration reference

Spring properties can be set in `application.properties`, as `--key=value` arguments, as `-Dkey=value` system properties or as environment variables (Spring relaxed binding: `fhc.ratelimit.enabled` → `FHC_RATELIMIT_ENABLED`).

### Server

| Property | Default | Meaning |
|---|---|---|
| `server.port` | `8090` | HTTP port |
| `server.http2.enabled` | `true` | HTTP/2 (h2c) on Jetty |
| `server.compression.*` | enabled, ≥ 1 KB, JSON/XML/text | Response compression |
| `spring.threads.virtual.enabled` | `true` | Serve requests on JDK 21 virtual threads |
| `mycarenet.timezone` | `Europe/Brussels` | Time zone used to build MyCareNet dates |
| `management.endpoints.web.exposure.include` | `health` | Only `/actuator/health` is exposed |
| `fhc.cors.allowed-origin-patterns` | `*` | Comma-separated origin patterns allowed by CORS (credentials are allowed) |

### MyCareNet licence

Every MyCareNet request carries a package licence (username/password issued by MyCareNet to the software vendor). FHC resolves it in this order:

1. The licence attached to the authenticated user (`mcnLicense` / `mcnPassword`, see next section), if the request uses HTTP Basic authentication.
2. Otherwise the connector properties `mycarenet.license.username` and `mycarenet.license.password` from the environment's `org.taktik.connector.technical.properties` file (every `*.package.license.*` entry refers to them).

The acceptance file ships with a username only; the production file ships with neither. Provide them for your deployment, for example by building with a customised properties file or by pointing `org.taktik.connector.technical.config.location` at your own copy (templates: `src/main/resources/{acpt,prod}/org.taktik.connector.technical.template.properties`).

### Users and Basic authentication

eHealth access is authorised by the keystore/token headers (see [STS](api/authentication-and-utilities.md)), and the Spring Security chain permits every path. On top of that, clients **may** send HTTP Basic credentials. They identify an FHC user, which carries a MyCareNet licence and other vendor credentials (APB, FTM, organisation keystores):

| Property | Meaning |
|---|---|
| `freehealth.authentication.username` / `password` | A single static user configured in properties |
| `freehealth.authentication.mcn-license` / `mcn-password` | MyCareNet licence attached to that static user |
| `org.taktik.connector.couchdb.url` | CouchDB URL for other users (default `http://127.0.0.1:5984`) |
| `org.taktik.connector.couchdb.db-name` | Database holding user documents (default `fhc-users`) |
| `org.taktik.connector.couchdb.username` / `password` | CouchDB credentials. **Set them explicitly**, because the built-in default is not meant for production. |

Any user name other than the static one is treated as a UUID and loaded from `<couchdb url>/<dbName>/<uuid>`. The document fields match `middleware/dao/User.kt` (`passwordHash` is BCrypt, `mcnLicense`, `mcnPassword`, `mcnPackageName`, `apbCustomerId`, …). User documents are cached for 2 minutes.

### Hazelcast (in-memory state)

Keystores, SAML tokens, ETKs, KGSS keys and rate-limit counters live in an embedded Hazelcast instance. They are never written to disk.

| Map | Time to live |
|---|---|
| Keystores | 18 h |
| SAML tokens | 12 h |
| ETKs (encryption tokens) | 8 h |
| Long-lived ETKs | 3 years |
| KGSS keys | 12 h |
| Rate-limit counters | 120 s |

| Property | Meaning |
|---|---|
| `icure.hazelcast.group-name` | Hazelcast cluster name. Instances with the same name that discover each other share their state. |

Standard Hazelcast configuration (e.g. `hazelcast.yaml` or the `hazelcast.*` system properties) controls discovery. By default, Hazelcast uses multicast discovery.

### Rate limiting

A servlet filter (`RateLimitFilter`) rate-limits requests. Counters are kept in Hazelcast, so limits hold cluster-wide. The filter is **disabled by default**.

| Property | Default | Meaning |
|---|---|---|
| `fhc.ratelimit.enabled` | `false` | Turn the filter on |
| `fhc.ratelimit.key-header` | `X-FHC-keystoreId` | Header that identifies a caller |
| `fhc.ratelimit.window-seconds` | `60` | Window length |
| `fhc.ratelimit.max-requests` | `100` | Limit per caller per window for paths not in a group |
| `fhc.ratelimit.anonymous-limit` | `20` | Limit per client IP when the key header is absent |
| `fhc.ratelimit.excluded-paths` | `/sts/keystore,/actuator/**` | Paths never limited |
| `fhc.ratelimit.groups.<name>.path-patterns` | — | Ant patterns belonging to a group |
| `fhc.ratelimit.groups.<name>.max-requests` | `100` | Limit for the group |
| `fhc.ratelimit.groups.<name>.window-seconds` | global window | Optional per-group window |

`application.properties` predefines one group per controller: 50 requests/min for the high-frequency services (GenIns, MDA, eHealthBox, eAttest, Recip-e, Tarification) and 10 requests/min for everything else. Every response carries `X-RateLimit-Limit`, `X-RateLimit-Remaining` and `X-RateLimit-Reset`. When a caller exceeds the limit, the server responds with HTTP 429, a `Retry-After` header and a JSON body `{"error":"rate_limit_exceeded",...}`. The Micrometer metrics are `fhc.ratelimit.requests{result=allowed|denied}` and `fhc.ratelimit.check.duration`.

`fhc.ratelimit.fallback-key-header` (set to `X-FHC-tokenId` in `application.properties`) names a second header used as the caller key when `key-header` is absent. Callers with neither header are limited per client IP with `anonymous-limit`.

## Docker

Two Dockerfiles are provided:

- `build.Dockerfile`: builds the jar in a `gradle:8.13-jdk21` image (`./gradlew :bootJar -x test`).
- `package.Dockerfile`: copies the jar from the build image into `gcr.io/distroless/java21-debian12:nonroot` and runs `java -jar /app/fhc.jar`. It sets `SERVER_PORT=8080`, exposes 8080, declares a `HEALTHCHECK` on `/actuator/health` (using the bundled `misc/health-check/HealthCheck.class`), and prepares `/opt/ehealth` for the `nonroot` user.

```bash
# package.Dockerfile starts FROM docker.taktik.be/icure/freehealth-connector:$version (the build image)
docker build -f build.Dockerfile -t docker.taktik.be/icure/freehealth-connector:local .
docker build -f package.Dockerfile --build-arg version=local -t freehealth-connector:local .

docker run --rm -p 8080:8080 \
  -e JDK_JAVA_OPTIONS="-Dorg.taktik.connector.technical.config.location=/prod/org.taktik.connector.technical.properties -Xmx1g" \
  freehealth-connector:local
```

The distroless image has no shell, so pass JVM options with `JDK_JAVA_OPTIONS`, which the `java` launcher reads by itself (`JAVA_OPTS` would be ignored). To listen on another port, override `SERVER_PORT`.

The CI pipeline (`ci/cloudbuild.yaml`) builds and pushes `docker.taktik.be/icure/freehealth-connector:<git version>` for `linux/amd64` and `linux/arm64`, and publishes the Helm chart to `helm.taktik.be`.

## Kubernetes (Helm)

The chart is in `deployment/helm/freehealth-connector`.

```bash
helm install fhc deployment/helm/freehealth-connector \
  --set images.freehealthconnector.tag=<version> \
  --set-string images.freehealthconnector.javaOpts="-Dorg.taktik.connector.technical.config.location=/prod/org.taktik.connector.technical.properties -Xmx1536m"
```

The chart sets `SERVER_PORT` to `servicePort` and passes `javaOpts` (plus the JDWP agent when `debug` is on) in `JDK_JAVA_OPTIONS`. The readiness and liveness probes call `/actuator/health` on the `http` container port.

Important values (`values.yaml`):

| Value | Default | Notes |
|---|---|---|
| `images.freehealthconnector.repository` / `tag` | `docker.taktik.be/icure/freehealth-connector` / chart version | Pulls with the `regsecret` image pull secret |
| `images.freehealthconnector.replicaCount` | `1` | See [Clustering and scaling](#clustering-and-scaling) |
| `images.freehealthconnector.servicePort` | `8080` | Container port; also exported as `SERVER_PORT` |
| `images.freehealthconnector.javaOpts` | empty | JVM options (environment selection, heap size, ...) |
| `images.freehealthconnector.memoryRequest` / `memoryLimit` | `1Gi` / `2Gi` | Keep the heap (`-Xmx`) below the limit |
| `images.freehealthconnector.debug` | `false` | `true` opens a JDWP port 5005; never enable in production |
| `images.freehealthconnector.env` | — | Map of extra environment variables (e.g. `FHC_CORS_ALLOWED_ORIGIN_PATTERNS`) |
| `images.freehealthconnector.readiness` / `liveness` | `true` | HTTP probes on `/actuator/health` |
| `ingress.enabled`, `ingress.hosts`, `ingress.tls` | disabled | Standard ingress settings |

## Clustering and scaling

Keystores and tokens live in Hazelcast. A client that uploads a keystore on one instance and then calls another instance only works if the instances form a Hazelcast cluster, or if the load balancer uses sticky sessions. When running several replicas:

- give them the same `icure.hazelcast.group-name`, and
- configure a discovery mechanism that works in your network. Multicast is usually unavailable on Kubernetes; use the Hazelcast Kubernetes discovery or a static member list.

Rate-limit counters are also shared through the cluster, so limits apply globally rather than per replica.

## Verifying the installation

```bash
curl -s http://localhost:8090/actuator/health           # {"status":"UP"}
curl -s http://localhost:8090/v3/api-docs | jq '.paths | length'

# Upload a keystore and obtain a SAML token (see api/authentication-and-utilities.md)
KEYSTORE_ID=$(curl -s -F "file=@/path/to/keystore.p12;type=application/x-pkcs12" \
  http://localhost:8090/sts/keystore | jq -r .uuid)
curl -s "http://localhost:8090/sts/token?ssin=<SSIN>" \
  -H "X-FHC-keystoreId: $KEYSTORE_ID" -H "X-FHC-passPhrase: <passphrase>"
```

A token response with a `tokenId` means the connector can reach the eHealth STS with your certificate.

## Running the tests

Most tests are integration tests that call the real eHealth acceptance environment. They need:

1. `src/test/resources/test.properties` created from `test.template.properties`, with the SSIN, NIHII, passphrase and file name of one or more eHealth test keystores (`org.taktik.icure.keystore1.*`, `keystore2.*`, …).
2. The corresponding `.p12` files where the tests expect them.

```bash
./gradlew test
./gradlew test --tests "org.taktik.freehealth.middleware.web.controllers.EfactFlatcoreOfflineTest"   # offline, no credentials
scripts/parse-test-results.sh                                                                        # summarise build/test-results
```

`test-baseline-*.txt|yml` record the expected pass/fail baseline. Some tests are known to fail independently of your changes (e.g. three `EagreementServiceUtilsTest` cases).

## Deployment notes

- **Ports.** The application defaults to 8090 when run directly (`bootRun`, `java -jar`). The Docker image and the Helm chart set `SERVER_PORT=8080`.
- **API documentation.** `/swagger-ui.html` and `/v3/api-docs`. The old `/api/index.html` redirects to `/swagger-ui.html`.
- **`/opt/ehealth` must be writable.** The trust stores are extracted there at startup.
- **CORS.** By default every origin is allowed, with credentials. Restrict it with `fhc.cors.allowed-origin-patterns` (comma-separated patterns such as `https://*.example.com`) when the API is exposed publicly.
