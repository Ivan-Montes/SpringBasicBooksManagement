# BUILD STAGE
# Set main image
FROM maven:3.9.6-eclipse-temurin-17-alpine as builder

# Set the working directory using variables
WORKDIR /app

# Copy the application source code to the src directory
COPY pom.xml ./
COPY src ./src
RUN mvn clean install -DskipTests

# DEPLOY STAGE
# Set main image
FROM eclipse-temurin:17-jre-alpine

# Set the working directory using variables
WORKDIR /app

# Define meta info
LABEL ime.school-api-rest.version="1.0"
LABEL ime.school-api-rest.maintainer="IvanM"
LABEL ime.school-api-rest.description="Just a simple dockerfile"

# Copy the application source code to the src directory
COPY --from=builder /app/target/*.jar ./app.jar

# Port
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java","-jar","./app.jar"]
