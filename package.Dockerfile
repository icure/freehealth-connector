ARG version
FROM --platform=$BUILDPLATFORM docker.taktik.be/icure/minimal-ktor-server:$version AS builder

FROM gcr.io/distroless/java21-debian12:nonroot
ARG version
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app
# Expose the port
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
