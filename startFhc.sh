#!/bin/bash

echo "Let's start the java app"
echo "$FHC_STAGE"

pwd

java -Dorg.taktik.connector.technical.config.location=/prod/org.taktik.connector.technical.properties -jar build/libs/freehealth-connector-53.0.53c4570716.jar

#java -Dorg.taktik.connector.technical.config.location=/acpt/org.taktik.connector.technical.properties -jar build/libs/freehealth-connector-53.0.53c4570716.jar