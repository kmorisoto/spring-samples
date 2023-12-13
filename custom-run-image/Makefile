.PHONY: build
build:
	docker build -t custom-run -f ./run.Dockerfile .
	./gradlew bootBuildImage --runImage=custom-run --pullPolicy IF_NOT_PRESENT
