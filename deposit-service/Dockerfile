FROM openjdk:21-jdk-slim
EXPOSE 8083
COPY build/libs/deposit-service-1.0-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "deposit-service-1.0-SNAPSHOT.jar"]
