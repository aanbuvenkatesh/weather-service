#!/bin/bash -e

if [ -z "$DOCKER_ENV_LOCATION" ]; then export DOCKER_ENV_LOCATION=config ;fi

# Copy the environment file for docker to resolve
cp -f ${DOCKER_ENV_LOCATION}/config.env .env

# Now run Docker Compose
docker-compose -f weather-service.yaml build