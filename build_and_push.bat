@echo off

REM echo Building Java/Kotlin Freehealth-connector...
REM .\gradlew build

set "IMAGE_NAME=freehealth-connector"
set "TAG=develop"
set "JFROG_URL=cgm-be-compufit-docker.oci.jfrog.cgm.ag"

if "%JFROG_URL%"=="" (
  echo ERROR: JFROG_URL is required.
  echo.
  echo Set it by passing it as the 3rd argument, e.g.:
  echo   %~nx0 %IMAGE_NAME% %TAG% cgm-be-compufit-docker.oci.jfrog.cgm.ag
  pause
  exit /b 1
)

for /f "tokens=1 delims=/" %%A in ("%JFROG_URL%") do set "JFROG_REGISTRY=%%A"

set "LOCAL_TAG=%IMAGE_NAME%:%TAG%"
set "REMOTE_TAG=%JFROG_URL%/%IMAGE_NAME%:%TAG%"

echo Building image: %LOCAL_TAG%
docker buildx build -t %LOCAL_TAG% .
if errorlevel 1 (
  echo ERROR: docker build failed.
  pause
  exit /b 1
)

echo Tagging image: %REMOTE_TAG%
docker tag %LOCAL_TAG% %REMOTE_TAG%
if errorlevel 1 (
  echo ERROR: docker tag failed.
  pause
  exit /b 1
)

echo Pushing image: %REMOTE_TAG%
docker push %REMOTE_TAG%
if errorlevel 1 (
  echo ERROR: docker push failed.
  pause
  exit /b 1
)

echo Done.

pause
