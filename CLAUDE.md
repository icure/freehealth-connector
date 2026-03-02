# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Freehealth Connector is a multi-user REST API middleware that wraps the Belgian eHealth platform services. It's a Spring Boot application written in Kotlin that exposes REST endpoints for healthcare operations like electronic prescriptions (Recipe), electronic attestations (eattest), ebox messaging (ehbox), patient consent, insurance (genins), and more.

## Build & Run Commands

```bash
./gradlew bootRun           # Run the app (port 8090, API docs at /api/index.html)
./gradlew build             # Build the project
./gradlew test              # Run all tests
./gradlew test --tests "org.taktik.freehealth.middleware.web.controllers.RecipeControllerTest"  # Single test class
./gradlew ktlint            # Check Kotlin code style
./gradlew ktlintFormat      # Auto-fix Kotlin code style
```

Requires JDK 21. Kotlin version is 2.2.10, Spring Boot 3.5.5.

## Architecture

### Three-Layer Design

1. **Controllers** (`middleware/web/controllers/`) — Spring REST controllers. Each eHealth service has its own controller (e.g., `RecipeController`, `STSController`, `EhboxController`). Controllers receive keystore/token IDs via HTTP headers (`X-FHC-keystoreId`, `X-FHC-passPhrase`, `X-FHC-tokenId`).

2. **Services** (`middleware/service/` interfaces, `middleware/service/impl/` implementations) — Business logic layer. Service interfaces are in `service/`, implementations in `service/impl/`. These orchestrate calls to the lower-level connector layer.

3. **Connectors** (`org.taktik.connector.business/` and `org.taktik.connector.technical/`) — Low-level integration with eHealth platform SOAP services. The `business/` package contains domain-specific connectors (recipe, eattest, ehbox, etc.). The `technical/` package has cross-cutting concerns like validation.

### Supporting Packages

- `middleware/dto/` — Data transfer objects for REST API request/response
- `middleware/domain/` — Domain models
- `middleware/format/efact/` — eFact (electronic invoicing) message format reader/writer
- `middleware/mapper/` — Object mappers (Orika-based, configured in `MapperConfiguration.kt`)
- `middleware/dao/` — Data access (CouchDB-backed user/auth store)

### Authentication Flow

The STS (Security Token Service) controller handles keystore uploads and token generation. Keystores (PKCS12) are uploaded and cached in-memory (never persisted to disk), referenced by UUID. Most endpoints require `keystoreId`, `passPhrase`, and `tokenId` headers.

### Key Configuration

- `application.properties` — Server config (port 8090), JWT settings, MyCarEnet timezone
- `src/main/resources/acpt/` and `src/main/resources/prod/` — Environment-specific eHealth trust stores
- `compiled/` — Pre-compiled eHealth JAXB classes from various connector versions and Recipe SDK versions
- Custom Maven repos: `repo.ehealth.fgov.be` (eHealth artifacts), `maven.taktik.be` (Taktik internal)

### eHealth Services Mapped

Each Belgian eHealth platform service typically has a controller + service + connector triplet:
- **STS**: Security token service (keystore management, SAML tokens)
- **Recipe**: Electronic prescriptions (v4)
- **Ehbox/EhboxV3**: Secure messaging
- **Eattest/EattestV2/EattestV3**: Electronic attestations
- **GenIns**: General insurability checks
- **DMG**: Global Medical File management
- **Chapter4/Eagreement**: Prior authorization agreements
- **Hub**: Health data hubs
- **Efact**: Electronic invoicing
- **Consent**: Patient consent management
- **TherLink**: Therapeutic link management
- **Addressbook/ConsultRn/RnConsult**: National registry lookups

## Test Configuration

Tests require a `test/resources/test.properties` file with real eHealth keystores and credentials (SSIN, NIHII, password). Tests hit actual eHealth test/acceptance environments — they are integration tests, not unit tests.

## Codebase Conventions

- License: GNU AGPL — all source files carry the AGPL header
- Hazelcast 5.3 is used for distributed caching (`HazelcastConfiguration.kt`, `CacheConfiguration.kt`)
- Swagger/SpringFox provides API documentation
- The project uses Gradle 8.13 with Kotlin DSL (`build.gradle.kts`, `settings.gradle.kts`)
- Version catalog in `libs.versions.toml` manages Spring Boot, Kotlin, and BouncyCastle versions
- Jakarta EE namespace (jakarta.*) replaces javax.* throughout (XML binding, validation, SOAP, WS)
- BouncyCastle uses `jdk18on` variant (bcprov-jdk18on, bcmail-jdk18on) version 1.71.1
- Jetty is the embedded servlet container (Tomcat is excluded); HTTP/2 enabled via jetty-http2-server
- Virtual threads support (targeting JDK 21)