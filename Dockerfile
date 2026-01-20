FROM adoptopenjdk/openjdk8:x86_64-alpine-jre8u462-b08
LABEL maintainer="Compufit"
LABEL version="0.0.1"
LABEL description="FreeHealth Connector"

ADD build/libs/freehealth-connector-0.0.0-dev.jar freehealth.jar
ADD config.json /opt/freehealth/config.json

CMD ["sh", "-c", "java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -DServer.port=8080 ${JAVA_OPTIONS} -jar /freehealth.jar"]

EXPOSE 8080
EXPOSE 5005