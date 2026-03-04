ARG version
FROM --platform=$BUILDPLATFORM docker.taktik.be/icure/freehealth-connector:$version AS builder

FROM gcr.io/distroless/java21-debian12:nonroot
ARG version
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/fhc.jar
COPY misc/health-check/HealthCheck.class /app/HealthCheck.class
COPY --chown=nonroot:nonroot opt/ehealth /opt/ehealth

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/fhc.jar"]
