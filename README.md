# LibraryApi

## Description
LibraryApi is a Java-based web application that provides a REST fulL API for managing a library system. It is built with Spring Boot and uses MySQL for data persistence.

## Technologies
- Java 21
- Spring Boot 3.2.5
- MySQL
- Maven
- Flyway for database migrations
- Spring Security for authentication and authorization

## Spring Security
In this project, Spring Security is used to handle user authentication and authorization. It has been configured to authenticate users through a custom user details service that loads user details by their username. User roles are used to authorize requests to different API endpoints.

### Roles
Roles in Spring Security are used to authorize users to access different parts of the application(POST, PUT, DELETE). In this project, two roles are used: `ROLE_USER` and `ROLE_ADMIN`. These roles must be added to the database with the prefix `ROLE_` for Spring Security to recognize them.
```sql
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
```

## Setup
To run this project, you need to have Java and Maven installed on your machine.

1. Clone the repository:
```bash
git clone https://github.com/andre-carbajal/LibraryApi.git
```

2. Navigate to the project directory:
```bash
cd LibraryApi
```

3. Create a MySQL database:
```sql
CREATE DATABASE libraryApi;
```

5. Build the project:
```bash
mvn clean install
```
6. Run the Flyway migrations to set up the database schema:
```bash
mvn flyway:migrate
```

7. Run the project:
```bash
mvn spring-boot:run
```

## Docker Execution

To run this application using Docker, follow these steps:

1. Build the Docker image:
```bash
docker build -t library-api .
```

2. Run the Docker container:
```bash
docker run -e 'DB_HOST=<your_database_host>' -e 'DB_PORT=<your_database_port>' -e 'DB_USERNAME=<your_database_username>' -e 'DB_PASSWORD=<your_database_password>' -p 8080:8080 libraryapi
```
Replace `<your_database_host>`, `<your_database_port>`, `<your_database_username>`, and `<your_database_password>` with your actual database credentials, or if you are using a local database, you can use `host.docker.internal` as the DB_HOST.

The application will be accessible at `http://localhost:8080`.

## API Documentation
The API documentation is generated using `Springdoc OpenAPI`. This allows for interactive exploration of the API via a web interface. The documentation is available at http://localhost:8080/doc after starting the application.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.