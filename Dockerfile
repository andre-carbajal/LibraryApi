# Start with a base image containing Java runtime and Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Add Maintainer Info
LABEL maintainer="andre-carbajal"

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file
COPY ./pom.xml ./pom.xml

# Download all required dependencies into one layer
RUN mvn dependency:go-offline -B

# Copy the project source
COPY ./src ./src

# Package the application
RUN mvn package -DskipTests

# Start a new stage from scratch
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/LibraryApi.jar LibraryApi.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

ENV DB_HOST=''
ENV DB_PORT=''
ENV DB_DATABASE = ''
ENV DB_USERNAME=''
ENV DB_PASSWORD=''

# Run the jar file with the prod profile
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","./LibraryApi.jar"]