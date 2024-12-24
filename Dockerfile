# Use Maven image to build the application
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set the working directory for the build
WORKDIR /app

# Copy the pom.xml and dependency files
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use OpenJDK image to run the application
FROM openjdk:17-jdk-alpine

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR file from the Maven build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
