# Start with a base image containing Java runtime and Maven
FROM eclipse-temurin:21-jre

# Add Maintainer Info
LABEL maintainer="andre-carbajal"

# Add a volume pointing to /tmp
VOLUME /tmp

# Add the application's jar to the container
ADD ./target/LibraryApi.jar LibraryApi.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

ENV DB_HOST=''
ENV DB_PORT=''
ENV DB_USERNAME=''
ENV DB_PASSWORD=''

# Run the jar file with the prod profile
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","./LibraryApi.jar"]