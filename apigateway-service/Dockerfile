FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/apigateway-service-1.0.jar apigateway-service.jar
ENTRYPOINT ["java","-jar","apigateway-service.jar"]