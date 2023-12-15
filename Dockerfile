FROM openjdk:21
WORKDIR /app
COPY out/artifacts/MainProject_jar/MainProject.jar .

EXPOSE 5000

CMD ["java", "-jar", "/app/MainProject.jar"]

