FROM openjdk:11-jre-slim
COPY build/libs/*.jar app.jar
RUN docker image tag sulsul:latest zeze1004/sulsul:latest
ENTRYPOINT ["java", "-jar", "/app.jar"]