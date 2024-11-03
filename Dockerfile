FROM openjdk:8-jdk-alpine


# Copy the packaged jar file from the build stage
ADD target/first-service.jar first-service.jar

# Expose the port the application will run on
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "first-service.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]