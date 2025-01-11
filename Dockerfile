# Use a base image with JDK
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the target folder to the container
COPY target/docker_example-0.0.1-SNAPSHOT.jar /app/docker_example-0.0.1-SNAPSHOT.jar

# Expose the port that your app will run on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/docker_example-0.0.1-SNAPSHOT.jar"]
