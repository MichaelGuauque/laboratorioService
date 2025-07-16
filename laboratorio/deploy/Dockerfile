FROM openjdk:21-jdk-slim
ARG JAR_FILE=laboratorio-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} laboratorio.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "laboratorio.jar", "server-address=0.0.0.0"]
