FROM maven:3.6.1-jdk-11-slim AS build
WORKDIR /src
COPY . .
RUN mvn -Dmaven.test.skip=true clean package

FROM openjdk:11.0.4-jre
WORKDIR /app
COPY --from=build /src/target/library-spring-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar app.jar
