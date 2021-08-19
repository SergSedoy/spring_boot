FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD build/libs/spring_boot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
