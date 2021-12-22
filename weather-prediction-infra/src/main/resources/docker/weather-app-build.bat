@echo off

if not defined DOCKER_ENV_LOCATION set DOCKER_ENV_LOCATION=config

REM Copy the environment file for docker to resolve
copy %DOCKER_ENV_LOCATION%\config.env .env > NUL

REM Now run Docker Compose
docker compose -f weather-service.yaml build