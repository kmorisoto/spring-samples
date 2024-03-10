.PHONY: build
build:
	docker build -t custom-run -f ./run.Dockerfile .
	./gradlew bootBuildImage --runImage=custom-run --pullPolicy IF_NOT_PRESENT

.PHONY: run
run:
	docker run -p 8081:8081 --rm -it custom-run-image:0.0.1-SNAPSHOT

.PHONY: brun
br:
	make build
	make run
