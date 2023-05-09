FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/service-discovery-1.0.jar service-discovery.jar
ENTRYPOINT ["java", "-jar", "service-discovery.jar"]