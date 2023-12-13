FROM paketobuildpacks/run-jammy-base
USER root
RUN apt-get update && apt-get install -y busybox
USER cnb
