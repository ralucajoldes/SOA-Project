FROM openjdk:17-jdk-slim
ADD target/emailService-0.0.1-SNAPSHOT.jar emailService.jar
ENTRYPOINT [ "java", "-jar","emailService.jar" ]