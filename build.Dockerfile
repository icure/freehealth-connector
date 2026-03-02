# Build stage
FROM gradle:8.13-jdk21 AS builder

WORKDIR /app

# Copy gradle files
COPY libs.versions.toml build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradle ./gradle

# Download dependencies
RUN gradle dependencies --no-daemon

# Copy source code
COPY src ./src

# Build the application
RUN ./gradlew :bootJar -x test
