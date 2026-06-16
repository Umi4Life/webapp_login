# syntax=docker/dockerfile:1

FROM maven:3.9-eclipse-temurin-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B package -DskipTests

FROM eclipse-temurin:8-jre-jammy
WORKDIR /app

COPY --from=build /app/target/login-webapp-1.0-SNAPSHOT.jar ./app.jar
COPY --from=build /app/src/main/webapp ./src/main/webapp

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
