FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the jar file to the working directory
COPY target/surveys-0.0.1-SNAPSHOT.jar /app/surveys.jar

# Expose the port
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "surveys.jar"]

