# I could not get the flyway to run in the container. Accuses error of not being able to connect with the database;
# Commenting on the dependency of the flyway on the pom, the container with the application usually goes up;

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]