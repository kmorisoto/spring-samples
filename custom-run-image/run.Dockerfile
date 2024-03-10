FROM paketobuildpacks/run-jammy-base
USER root
RUN apt-get update && apt-get install -y postgresql-client-14
USER cnb
