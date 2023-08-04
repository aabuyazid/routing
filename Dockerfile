FROM eclipse-temurin:17-jdk-alpine
COPY . /app/routing
WORKDIR /app/routing
RUN ls -la
RUN ls /root
RUN ./mvnw clean install
ENTRYPOINT ["java", "-jar", "./target/routing-1.0.2.RELEASE.jar"]

#FROM openjdk:17
#
#COPY target/routing-1.0.2.RELEASE.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]