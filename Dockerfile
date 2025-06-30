FROM openjdk:21

COPY notes-*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]