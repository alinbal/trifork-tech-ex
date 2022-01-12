#File to help out with the most common commands

build: build-java build-docker

build-push: build-java build-docker docker-push

build-java:
	./mvnw clean install

build-docker:
	docker build -t alinbal/trifork:employeebackend .

docker-push:
	docker push alinbal/trifork:employeebackend

start-db:
	docker run --name triforkdb -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres

up:
	docker-compose up

down:
	docker-compose down