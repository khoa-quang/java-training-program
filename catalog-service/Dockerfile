# Use the Eclipse Temurin (formerly AdoptOpenJDK) Java 8 JRE Alpine base image
FROM eclipse-temurin:8-jre-alpine

# Create and set the working directory inside the container
WORKDIR /app

# Copy microservice’s JAR from the target folder to the container
COPY target/catalog-service-1.0.0.jar app.jar

# Expose the port that the microservice listens on
EXPOSE 5003

# Set the command to run app.war
ENTRYPOINT ["java", "-jar", "app.jar"]
