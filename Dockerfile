FROM adoptopenjdk/openjdk8:alpine-jre

EXPOSE 8080

COPY target/NetologySpringBootTask4-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app."]