#!/bin/bash

echo "Let's start the java application"
echo "$FHC_STAGE"

pwd

java -Dorg.taktik.connector.technical.config.location=/acpt/org.taktik.connector.technical.properties -jar build/libs/freehealth-connector-0.3.620-047d03ce5b-dirty.jar