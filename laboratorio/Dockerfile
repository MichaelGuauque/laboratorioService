FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/laboratorio-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} laboratorio.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "laboratorio.jar", "--server.address=0.0.0.0"]