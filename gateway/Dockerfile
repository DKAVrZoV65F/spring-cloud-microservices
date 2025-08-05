FROM openjdk:21-jdk-slim
EXPOSE 8989
COPY build/libs/gateway-1.0-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "gateway-1.0-SNAPSHOT.jar"]
