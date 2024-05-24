# LibraryApi

## Description
LibraryApi is a Java-based web application that provides a REST fulL API for managing a library system. It is built with Spring Boot and uses MySQL for data persistence.

<details>
<summary>Index</summary>

- [Technologies](#technologies)
- [Spring Security](#spring-security)
   - [Roles](#roles)
- [Setup](#setup)
- [Docker Execution](#docker-execution)
   - [Prerequisites](#prerequisites)
   - [Steps](#steps)
- [API Documentation](#api-documentation)
- [License](#license)

</details>

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

4. Download the dependencies:
   ```bash
   mvn dependency:resolve
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

This application can be run using Docker and Docker Compose. Docker allows you to create a container for your application, ensuring it has all the dependencies it needs to run, and Docker Compose allows you to manage applications that require multiple containers, like this one which requires a separate container for the MySQL database.

### Prerequisites

Ensure you have Docker and Docker Compose installed on your machine. If not, you can download them from the official Docker website.

### Steps

1. **Build the Docker images:**

   Docker can build images automatically by reading the instructions from a Dockerfile. In the root directory of the project, you can find a `Dockerfile` which contains instructions on how to build the image for the application.

   Run the following command in the terminal to build the Docker image:

    ```bash
    docker build -t library-api .
    ```

   This command tells Docker to build an image using the Dockerfile in the current directory and tag the image as `library-api`.

2. **Run the Docker container:**

   After building the Docker image, you can run the Docker container with the following command:

    ```bash
    docker run -e 'DB_HOST=<your_database_host>' -e 'DB_PORT=<your_database_port>' -e 'DB_USERNAME=<your_database_username>' -e 'DB_PASSWORD=<your_database_password>' -p 8080:8080 library-api
    ```

   Replace `<your_database_host>`, `<your_database_port>`, `<your_database_username>`, and `<your_database_password>` with your actual database credentials. If you are using a local database, you can use `host.docker.internal` as the `DB_HOST`.

   This command tells Docker to run the `library-api` image as a container, with the specified environment variables and port mapping. The application will be accessible at `http://localhost:8080`.

3. **Run the Docker Compose:**

   Docker Compose uses a file named `docker-compose.yml` for defining and running multi-container Docker applications.

   Run the following command in the terminal to start the application and the database using Docker Compose:

    ```bash
    docker compose up
    ```

   This command tells Docker Compose to start the services defined in the `docker-compose.yml` file.

   The application will be accessible at `http://localhost:8080`.

4. **Stop the Docker Compose:**

   You can stop the services started by Docker Compose by running the following command in the terminal:

    ```bash
    docker compose down
    ```

   This command tells Docker Compose to stop the services defined in the `docker-compose.yml` file.

## API Documentation
The API documentation is generated using `Springdoc OpenAPI`. This allows for interactive exploration of the API via a web interface. The documentation is available at http://localhost:8080/doc after starting the application.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.