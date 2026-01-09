FROM openjdk:17-jdk-slim
ADD target/analyticsService-0.0.1-SNAPSHOT.jar analyticsService.jar
ENTRYPOINT [ "java", "-jar","analyticsService.jar" ]