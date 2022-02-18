FROM adoptopenjdk/openjdk11
ARG JAR_FILE_PATH=api-module/build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar
EXPOSE 8185
ENTRYPOINT ["java", "-jar", "/app.jar"]