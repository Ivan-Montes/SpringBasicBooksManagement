# Set main image
FROM eclipse-temurin:17-jre-alpine

# Define meta info
LABEL ime.school-api-rest.version="1.0"
LABEL ime.school-api-rest.maintainer="IvanM"
LABEL ime.school-api-rest.description="Just a simple dockerfile"

# Define environment variables
ENV DIRPATH=/app

# Set the working directory using variables
WORKDIR ${DIRPATH}

# Copy the application source code to the src directory
COPY target/*.jar app.jar

# Port
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java","-jar","./app.jar"]