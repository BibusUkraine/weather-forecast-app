FROM maven:3.9-eclipse-temurin-17-alpine as builder
WORKDIR /app
COPY . .
RUN mvn -f /app/pom.xml clean package -D maven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/weather-forecast-api-0.0.1.jar .
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "weather-forecast-api-0.0.1.jar"]