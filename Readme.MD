# Project Configuration

## Profiles
This project supports two profiles:

- **local**: Uses an H2 in-memory database (no external setup required).
- **dev**: Runs on PostgreSQL (requires environment variables).

## Database Configuration

### Local (H2)
No setup needed. The application will automatically use H2.

### Development (PostgreSQL)
Set the following environment variables before running:

```sh
export SPRING_DATASOURCE_HOST=your_postgres_host
export SPRING_DATASOURCE_PORT=5432
export SPRING_DATASOURCE_NAME=your_database_name
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
```

## Liquibase Migrations
- Enabled for `dev` profile.
- Migration scripts are in `src/main/resources/db/changelog/`.
- The changelog file is `db.changelog-master.xml`.
- To run manually:
  ```sh
  mvn liquibase:update
  ```

## Running the Application

### Locally (H2)
```sh
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### With PostgreSQL (Dev Profile)
```sh
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Running with Docker

### H2 (Local)
```sh
docker run -e SPRING_PROFILES_ACTIVE=local -p 8080:8080 my-spring-app
```

### PostgreSQL (Dev)
```sh
docker run -e SPRING_PROFILES_ACTIVE=dev \
    -e SPRING_DATASOURCE_HOST=your-db-host \
    -e SPRING_DATASOURCE_PORT=5432 \
    -e SPRING_DATASOURCE_NAME=your-db-name \
    -e SPRING_DATASOURCE_USERNAME=your-db-user \
    -e SPRING_DATASOURCE_PASSWORD=your-db-password \
    -p 8080:8080 my-spring-app
```


---

