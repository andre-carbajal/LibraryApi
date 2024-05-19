# LibraryApi

## Description
LibraryApi is a Java-based web application that provides a REST fulL API for managing a library system. It is built with Spring Boot and uses MySQL for data persistence.

## Technologies
- Java 21
- Spring Boot 3.2.5
- MySQL
- Maven
- Flyway for database migrations

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

4. Build the project:
```bash
mvn clean install
```

5. Run the project:
```bash
mvn spring-boot:run
```

## Documentation
The API documentation is available at http://localhost:8080/doc

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.