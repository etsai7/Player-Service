# Use OpenJDK 11 as base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY ../target/CraftDemoApplication.jar /app/CraftDemoApplication.jar

# Expose port 8081
EXPOSE 8081

# Command to run the Spring Boot application
CMD ["java", "-jar", "CraftDemoApplication.jar"]