FROM amazoncorretto:17

# COPY ./build/libs/<프로젝트명>-<버전>-SNAPSHOT.jar app.jar
COPY ./build/libs/real-time-trip-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]