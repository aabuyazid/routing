FROM maven:3.9.3-eclipse-temurin-17
COPY . /app/routing
WORKDIR /app/routing
RUN ls -la
RUN ./mvnw clean install
ENTRYPOINT ["java", "-jar", "./target/routing-1.0.2.RELEASE.jar"]

#FROM openjdk:17
#
#COPY target/routing-1.0.2.RELEASE.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]