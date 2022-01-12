# This cam be improved by potentially adding the build step inside the Docker file.
FROM gcr.io/distroless/java11-debian11
COPY target/*.jar /app/main.jar
WORKDIR /app
CMD ["main.jar"]