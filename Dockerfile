FROM openjdk:21
COPY ./build/libs/batch-server.jar batch-server.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Duser.timezone=Asia/Seoul", "-jar", "batch-server.jar"]
