FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /opt/app
COPY gradlew gradlew.bat ./
COPY gradle/ gradle/
COPY build.gradle ./
COPY settings.gradle ./
RUN ./gradlew dependencies --write-locks
COPY src src
RUN ./gradlew build

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /opt/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]