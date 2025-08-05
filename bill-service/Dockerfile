FROM openjdk:21-jdk-slim
EXPOSE 8082
COPY build/libs/bill-service-1.0-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "bill-service-1.0-SNAPSHOT.jar"]
