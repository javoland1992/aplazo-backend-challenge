FROM gradle:8.5-jdk17 AS builder
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build -x test

FROM openjdk:17
COPY --from=builder /home/gradle/src/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]