# Trifork Technical Exercise

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://trifork.com/">
    <img src="https://trifork.com/wp-content/themes/trifork-main/dw-img/logo-trifork-white.svg" alt="Logo" width="200" height="80">
  </a>
</div>

### Get Started

Easiest way to get check out the project and play with the API is to execute the `make up` command. This will spin up an instance of the backend and a database to play with.
Swagger documentation exposed at the following link http://localhost:8080/swagger-ui/index.html.

### Built With

* [Java Spring Boot](https://spring.io/projects/spring-boot)
* [PostgreSQL](https://www.postgresql.org/)
* [Docker](https://www.docker.com/)

### Prerequisites

You need to have `docker` and `docker-compose` installed. 
Also make sure that you can execute `Makfile` commands (if you can't, just have a look in the `Makfile` file). It will make life easier when executing repetitive and verbose commands.

### Useful commands

1. Build docker image and push to repo.
    ```sh
   make build-push
   ```
2. Start and instance of a postgreSQL docker db instance. It will expose port :5432
   ```sh
   make start-db
   ```
3. Bring up the docker-compose file.
   ```sh
   make up
   ```
4. Bring down the docker-compose file.
   ```sh
   make down
   ```
