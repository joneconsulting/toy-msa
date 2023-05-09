FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/service-discovery-0.0.1-SNAPSHOT.jar service-discovery.jar
ENTRYPOINT ["java", "-jar", "service-discovery.jar"]