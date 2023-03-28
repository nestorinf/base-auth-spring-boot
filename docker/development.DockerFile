FROM openjdk:17-jdk-alpine3.14

WORKDIR /app

COPY ./.mvn/ .mvn

COPY ./mvnw /app

COPY ./pom.xml /app

RUN ./mvnw dependency:go-offline

COPY ./src /app/src/

#CMD ["./mvnw", "spring-boot:run"]

EXPOSE 8080 5005 35729 8050