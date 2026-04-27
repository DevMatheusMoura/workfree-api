FROM maven:3.8.8-jdk-17 AS builder
WORKDIR /build

COPY pom.xml .
COPY src ./src


RUN mvn -B -DskipTests package


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

ARG JAR_FILE=target/workfree-api-0.0.1-SNAPSHOT.jar
COPY --from=builder /build/${JAR_FILE} app.jar


EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]

