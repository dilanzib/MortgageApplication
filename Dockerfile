FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY prospects /app/prospects
COPY target/mortgage-0.0.1-SNAPSHOT.jar /app/mortgage.jar
ENTRYPOINT ["java", "-jar", "/app/mortgage.jar"]
