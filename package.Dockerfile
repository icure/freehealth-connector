ARG version
FROM --platform=$BUILDPLATFORM docker.taktik.be/icure/freehealth-connector:$version AS builder

FROM gcr.io/distroless/java21-debian12:nonroot
ARG version
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/fhc.jar
COPY misc/health-check/HealthCheck.class /app/health-check/HealthCheck.class
COPY --chown=nonroot:nonroot opt/ehealth /opt/ehealth

# The application defaults to 8090 (application.properties); containers listen on 8080
ENV SERVER_PORT=8080
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --start-period=60s --retries=3 \
  CMD ["java", "-cp", "/app/health-check", "HealthCheck", "http://localhost:8080/actuator/health"]

# JVM options can be passed with JDK_JAVA_OPTIONS (read by the java launcher; there is no shell to expand JAVA_OPTS)
ENTRYPOINT ["java", "-jar", "/app/fhc.jar"]
